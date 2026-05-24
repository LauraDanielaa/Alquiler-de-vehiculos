package base_datos.alquiler_carros.Service;

import java.time.LocalDate;
import java.util.List;

import base_datos.alquiler_carros.Util.AuthUtils;
import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Models.Alquiler;
import base_datos.alquiler_carros.Models.Reserva;
import base_datos.alquiler_carros.Models.Vehiculo;
import base_datos.alquiler_carros.Models.Dto.Response.AlquilerResponseDTO;
import base_datos.alquiler_carros.Repository.IAlquilerRepository;
import base_datos.alquiler_carros.Repository.IVehiculoRepository;
import base_datos.alquiler_carros.Service.Interfaces.IAlquilerService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlquilerService implements IAlquilerService {

    private final IAlquilerRepository alquilerRepository;
    private final IVehiculoRepository vehiculoRepository;
    private final AuthUtils authUtils;



    // ─── Activar cuando se paga ───────────────────────────────

    @Override
    @Transactional
    public AlquilerResponseDTO activar(Long idAlquiler) {

        Alquiler alquiler = findOrThrow(idAlquiler);

        if (!alquiler.getEstado().equals("PENDIENTE_PAGO"))
            throw new RuntimeException("El alquiler no está en estado PENDIENTE_PAGO");

        alquiler.setEstado("ACTIVO");
        return Mapper.toDTO(alquilerRepository.save(alquiler));
    }

    // ─── Finalizar — solo el empleado ─────────────────────────

    @Override
    @Transactional
    public AlquilerResponseDTO finalizar(Long idAlquiler) {

        // Valida que sea un empleado el que finaliza
        authUtils.getEmpleadoAutenticado();

        Alquiler alquiler = findOrThrow(idAlquiler);

        if (!alquiler.getEstado().equals("ACTIVO"))
            throw new RuntimeException(
                    "Solo se pueden finalizar alquileres en estado ACTIVO");

        alquiler.setEstado("FINALIZADO");
        alquilerRepository.save(alquiler);

        Vehiculo vehiculo = alquiler.getVehiculo();
        vehiculo.setEstado("DISPONIBLE");
        vehiculoRepository.save(vehiculo);

        return Mapper.toDTO(alquiler);
    }

    // ─── Cancelación automática a medianoche ──────────────────

    @Override
    @Transactional
    public void cancelarAlquileresPendientesPagoVencidos() {

        List<Alquiler> vencidos = alquilerRepository
                .findAlquileresPendientesPagoVencidos("PENDIENTE_PAGO", LocalDate.now());

        vencidos.forEach(alquiler -> {
            alquiler.setEstado("CANCELADO");
            alquilerRepository.save(alquiler);

            // Liberar vehículo
            Vehiculo vehiculo = alquiler.getVehiculo();
            vehiculo.setEstado("DISPONIBLE");
            vehiculoRepository.save(vehiculo);
        });
    }

    // ─── Consultas ────────────────────────────────────────────

    @Override
    public AlquilerResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(findOrThrow(id));
    }

    @Override
    public List<AlquilerResponseDTO> listarTodos() {
        return alquilerRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<AlquilerResponseDTO> listarPorCliente(Long idCliente) {
        return alquilerRepository.findByCliente_Id(idCliente)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<AlquilerResponseDTO> listarPorVehiculo(Long idVehiculo) {
        return alquilerRepository.findByVehiculo_Id(idVehiculo)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<AlquilerResponseDTO> listarPorEstado(String estado) {
        return alquilerRepository.findByEstado(estado)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    // ─── Helper ───────────────────────────────────────────────

    private Alquiler findOrThrow(Long id) {
        return alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado con id: " + id));
    }
}