package base_datos.alquiler_carros.Service.Interfaces;

import java.util.List;

import base_datos.alquiler_carros.Models.Dto.Request.SucursalRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.SucursalResponseDTO;

public interface ISucursalService {

    SucursalResponseDTO crear(SucursalRequestDTO dto);
    SucursalResponseDTO buscarPorId(Long id);
    List<SucursalResponseDTO> listarTodas();
    List<SucursalResponseDTO> listarPorCiudad(String ciudad);
    SucursalResponseDTO actualizar(Long id, SucursalRequestDTO dto);
    void eliminar(Long id);
}