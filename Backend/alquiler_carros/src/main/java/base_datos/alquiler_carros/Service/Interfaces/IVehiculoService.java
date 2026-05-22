package base_datos.alquiler_carros.Service.Interfaces;

import base_datos.alquiler_carros.Models.Dto.Request.VehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.VehiculoResponseDTO;

import java.util.List;

public interface IVehiculoService {

    VehiculoResponseDTO crear(VehiculoRequestDTO dto);
    VehiculoResponseDTO buscarPorId(Integer id);
    VehiculoResponseDTO buscarPorPlaca(String placa);
    List<VehiculoResponseDTO> listarTodos();
    List<VehiculoResponseDTO> listarPorEstado(String estado);
    List<VehiculoResponseDTO> listarPorCategoria(Long idCategoria);
    VehiculoResponseDTO actualizar(Integer id, VehiculoRequestDTO dto);
    void eliminar(Integer id);
}