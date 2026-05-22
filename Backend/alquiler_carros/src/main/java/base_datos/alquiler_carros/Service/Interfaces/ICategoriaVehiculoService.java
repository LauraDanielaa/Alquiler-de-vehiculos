package base_datos.alquiler_carros.Service.Interfaces;

import base_datos.alquiler_carros.Models.Dto.Request.CategoriaVehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.CategoriaVehiculoResponseDTO;

import java.util.List;

public interface ICategoriaVehiculoService {

    CategoriaVehiculoResponseDTO crear(CategoriaVehiculoRequestDTO dto);
    CategoriaVehiculoResponseDTO buscarPorId(Long id);
    List<CategoriaVehiculoResponseDTO> listarTodas();
    CategoriaVehiculoResponseDTO actualizar(Long id, CategoriaVehiculoRequestDTO dto);
    void eliminar(Long id);
}