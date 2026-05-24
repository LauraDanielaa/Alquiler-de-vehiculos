package base_datos.alquiler_carros.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Models.Mantenimiento;
import base_datos.alquiler_carros.Models.Vehiculo;
import base_datos.alquiler_carros.Models.Dto.Request.MantenimientoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.MantenimientoResponseDTO;
import base_datos.alquiler_carros.Repository.IMantenimientoRepository;
import base_datos.alquiler_carros.Repository.IVehiculoRepository;
import base_datos.alquiler_carros.Service.Interfaces.IMantenimientoService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MantenimientoService implements IMantenimientoService {

    private final IMantenimientoRepository mantenimientoRepository;
    private final IVehiculoRepository      vehiculoRepository;

    @Override
    @Transactional
    public MantenimientoResponseDTO crear(MantenimientoRequestDTO dto) {
        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        return Mapper.toDTO(mantenimientoRepository.save(Mapper.toEntity(dto, vehiculo)));
    }

    @Override
    public MantenimientoResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(findOrThrow(id));
    }

    @Override
    public List<MantenimientoResponseDTO> listarTodos() {
        return mantenimientoRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<MantenimientoResponseDTO> listarPorVehiculo(Long idVehiculo) {
        return mantenimientoRepository.findByVehiculo_Id(idVehiculo)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<MantenimientoResponseDTO> listarPorRangoFechas(LocalDate fechaInicio,
                                                                LocalDate fechaFin) {
        return mantenimientoRepository.findByFechaBetween(fechaInicio, fechaFin)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public BigDecimal calcularCostoTotalPorVehiculo(Long idVehiculo) {
        BigDecimal total = mantenimientoRepository.sumCostoByVehiculo(idVehiculo);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    @Transactional
    public MantenimientoResponseDTO actualizar(Long id, MantenimientoRequestDTO dto) {
        Mantenimiento mantenimiento = findOrThrow(id);

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        mantenimiento.setFecha(dto.getFecha());
        mantenimiento.setDescripcion(dto.getDescripcion());
        mantenimiento.setCosto(dto.getCosto());
        mantenimiento.setVehiculo(vehiculo);

        return Mapper.toDTO(mantenimientoRepository.save(mantenimiento));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        mantenimientoRepository.delete(findOrThrow(id));
    }

    private Mantenimiento findOrThrow(Long id) {
        return mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado con id: " + id));
    }
}