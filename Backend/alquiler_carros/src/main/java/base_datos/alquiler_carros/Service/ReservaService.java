package base_datos.alquiler_carros.Service;

import base_datos.alquiler_carros.Models.Alquiler;
import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Models.Dto.Request.ReservaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.ReservaResponseDTO;
import base_datos.alquiler_carros.Models.Reserva;
import base_datos.alquiler_carros.Models.Vehiculo;
import base_datos.alquiler_carros.Repository.IAlquilerRepository;
import base_datos.alquiler_carros.Repository.IClienteRepository;
import base_datos.alquiler_carros.Repository.IReservaRepository;
import base_datos.alquiler_carros.Repository.IVehiculoRepository;
import base_datos.alquiler_carros.Service.Interfaces.IReservaService;
import base_datos.alquiler_carros.Util.AuthUtils;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;




import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Models.Alquiler;
import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Models.Reserva;
import base_datos.alquiler_carros.Models.Vehiculo;
import base_datos.alquiler_carros.Models.Dto.Request.ReservaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.ReservaResponseDTO;
import base_datos.alquiler_carros.Repository.IAlquilerRepository;
import base_datos.alquiler_carros.Repository.IClienteRepository;
import base_datos.alquiler_carros.Repository.IReservaRepository;
import base_datos.alquiler_carros.Repository.IVehiculoRepository;
import base_datos.alquiler_carros.Service.Interfaces.IReservaService;
import base_datos.alquiler_carros.Util.AuthUtils;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final IReservaRepository reservaRepository;
    private final IVehiculoRepository vehiculoRepository;
    private final IClienteRepository clienteRepository;
    private final IAlquilerRepository alquilerRepository;
    private final AuthUtils authUtils;


    // ─── Crear reserva ────────────────────────────────────────

    @Override
    @Transactional
    public ReservaResponseDTO crear(ReservaRequestDTO dto) { // ← ya no recibe idCliente

        Cliente cliente = authUtils.getClienteAutenticado(); // ← lo saca del token

        if (!dto.getFecha_fin().isAfter(dto.getFecha_inicio()))
            throw new RuntimeException("La fecha fin debe ser posterior a la fecha inicio");

        if (dto.getFecha_inicio().isBefore(LocalDate.now()))
            throw new RuntimeException("La fecha inicio no puede ser en el pasado");

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        if (!vehiculo.getEstado().equals("DISPONIBLE"))
            throw new RuntimeException("El vehículo no está disponible");

        List<Reserva> reservasEnRango = reservaRepository
                .findReservasActivasEnRango(dto.getIdVehiculo(),
                        dto.getFecha_inicio(), dto.getFecha_fin());

        if (!reservasEnRango.isEmpty())
            throw new RuntimeException("El vehículo ya tiene una reserva en esas fechas");

        // Validar también que no haya alquileres activos en esas fechas ← PUNTO 2
        List<Alquiler> alquileresEnRango = alquilerRepository
                .findAlquileresActivosEnRango(dto.getIdVehiculo(),
                        dto.getFecha_inicio(), dto.getFecha_fin());

        if (!alquileresEnRango.isEmpty())
            throw new RuntimeException("El vehículo ya tiene un alquiler en esas fechas");

        Reserva reserva = Mapper.toEntity(dto, cliente, vehiculo);
        vehiculo.setEstado("RESERVADO");
        vehiculoRepository.save(vehiculo);

        return Mapper.toDTO(reservaRepository.save(reserva));
    }
    // ─── Confirmar reserva → crea el alquiler ─────────────────

    @Override
    @Transactional
    public ReservaResponseDTO confirmar(Long idReserva) { // ← ya no recibe idCliente

        Cliente cliente = authUtils.getClienteAutenticado(); // ← lo saca del token

        Reserva reserva = findOrThrow(idReserva);

        if (!reserva.getCliente().getId().equals(cliente.getId()))
            throw new RuntimeException("No tienes permiso para confirmar esta reserva");

        if (!reserva.getEstado().equals("PENDIENTE"))
            throw new RuntimeException("Solo se pueden confirmar reservas en estado PENDIENTE");

        if (reserva.getFecha_inicio().isBefore(LocalDate.now()))
            throw new RuntimeException("La fecha de inicio ya pasó");

        reserva.setEstado("CONFIRMADA");
        reservaRepository.save(reserva);

        Alquiler alquiler = Alquiler.builder()
                .fecha_inicio(reserva.getFecha_inicio())
                .fecha_fin(reserva.getFecha_fin())
                .estado("PENDIENTE_PAGO")
                .vehiculo(reserva.getVehiculo())
                .cliente(reserva.getCliente())
                .build();
        alquilerRepository.save(alquiler);

        Vehiculo vehiculo = reserva.getVehiculo();
        vehiculo.setEstado("ALQUILADO");
        vehiculoRepository.save(vehiculo);

        return Mapper.toDTO(reserva);
    }
    // ─── Cancelar reserva manual ──────────────────────────────
    @Override
    @Transactional
    public ReservaResponseDTO cancelar(Long idReserva) { // ← ya no recibe idCliente

        Cliente cliente = authUtils.getClienteAutenticado(); // ← lo saca del token

        Reserva reserva = findOrThrow(idReserva);

        if (!reserva.getCliente().getId().equals(cliente.getId()))
            throw new RuntimeException("No tienes permiso para cancelar esta reserva");

        if (!reserva.getEstado().equals("PENDIENTE"))
            throw new RuntimeException("Solo se pueden cancelar reservas en estado PENDIENTE");

        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);

        Vehiculo vehiculo = reserva.getVehiculo();
        vehiculo.setEstado("DISPONIBLE");
        vehiculoRepository.save(vehiculo);

        return Mapper.toDTO(reserva);
    }



// ─── Cancelación automática a medianoche ──────────────────

@Override
@Transactional
public void cancelarReservasPendientesVencidas() {

    // Reservas PENDIENTES cuya fecha_inicio ya llegó
    List<Reserva> vencidas = reservaRepository
            .findReservasPendientesVencidas("PENDIENTE", LocalDate.now());

    vencidas.forEach(reserva -> {
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);

        Vehiculo vehiculo = reserva.getVehiculo();
        vehiculo.setEstado("DISPONIBLE");
        vehiculoRepository.save(vehiculo);
    });
}

// ─── Consultas ────────────────────────────────────────────

@Override
public ReservaResponseDTO buscarPorId(Long id) {
    return Mapper.toDTO(findOrThrow(id));
}

@Override
public List<ReservaResponseDTO> listarPorCliente(Long idCliente) {
    return reservaRepository.findByCliente_Id(idCliente)
            .stream()
            .map(Mapper::toDTO)
            .toList();
}

@Override
public List<ReservaResponseDTO> listarPorEstado(String estado) {
    return reservaRepository.findByEstado(estado)
            .stream()
            .map(Mapper::toDTO)
            .toList();
}

@Override
public List<ReservaResponseDTO> listarPorVehiculo(Long idVehiculo) {
    return reservaRepository.findByVehiculo_Id(idVehiculo)
            .stream()
            .map(Mapper::toDTO)
            .toList();
}

// ─── Helper ───────────────────────────────────────────────

private Reserva findOrThrow(Long id) {
    return reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
}
}