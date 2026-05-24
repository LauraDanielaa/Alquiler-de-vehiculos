package base_datos.alquiler_carros.Service.Interfaces;

import java.util.List;
import base_datos.alquiler_carros.Models.Dto.Response.AlquilerResponseDTO;

public interface IAlquilerService {

    AlquilerResponseDTO activar(Long idAlquiler);
    AlquilerResponseDTO finalizar(Long idAlquiler);
    void cancelarAlquileresPendientesPagoVencidos();
    AlquilerResponseDTO buscarPorId(Long id);
    List<AlquilerResponseDTO> listarTodos();
    List<AlquilerResponseDTO> listarPorCliente(Long idCliente);
    List<AlquilerResponseDTO> listarPorVehiculo(Long idVehiculo);
    List<AlquilerResponseDTO> listarPorEstado(String estado);
}