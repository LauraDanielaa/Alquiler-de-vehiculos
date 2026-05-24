package base_datos.alquiler_carros.Service.Interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import base_datos.alquiler_carros.Models.Dto.Request.MantenimientoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.MantenimientoResponseDTO;

public interface IMantenimientoService {

    MantenimientoResponseDTO crear(MantenimientoRequestDTO dto);
    MantenimientoResponseDTO buscarPorId(Long id);
    List<MantenimientoResponseDTO> listarTodos();
    List<MantenimientoResponseDTO> listarPorVehiculo(Long idVehiculo);
    List<MantenimientoResponseDTO> listarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    BigDecimal calcularCostoTotalPorVehiculo(Long idVehiculo);
    MantenimientoResponseDTO actualizar(Long id, MantenimientoRequestDTO dto);
    void eliminar(Long id);
}