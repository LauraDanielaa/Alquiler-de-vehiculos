package base_datos.alquiler_carros.Service.Interfaces;

import java.util.List;

import base_datos.alquiler_carros.Models.Dto.Request.MultaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.MultaResponseDTO;

public interface IMultaService {

    // Empleado registra la multa al finalizar el alquiler
    MultaResponseDTO crear(MultaRequestDTO dto);

    // Consultas
    MultaResponseDTO buscarPorId(Long id);
    List<MultaResponseDTO> listarPorAlquiler(Long idAlquiler);
}