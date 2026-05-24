package base_datos.alquiler_carros.Service.Interfaces;

import java.util.List;

import base_datos.alquiler_carros.Models.Dto.Request.ReservaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.ReservaResponseDTO;

public interface IReservaService {

    // Cliente crea la reserva
    ReservaResponseDTO crear(ReservaRequestDTO dto);

    // Cliente confirma → se crea el alquiler automáticamente
    ReservaResponseDTO confirmar(Long idReserva);

    // Cliente cancela manualmente
    ReservaResponseDTO cancelar(Long idReserva);

    // Consultas
    ReservaResponseDTO buscarPorId(Long id);
    List<ReservaResponseDTO> listarPorCliente(Long idCliente);
    List<ReservaResponseDTO> listarPorEstado(String estado);
    List<ReservaResponseDTO> listarPorVehiculo(Long idVehiculo);

    // Cancelación automática — la llama el ScheduledService
    void cancelarReservasPendientesVencidas();
}