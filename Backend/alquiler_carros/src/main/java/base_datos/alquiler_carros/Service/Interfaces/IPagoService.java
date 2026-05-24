package base_datos.alquiler_carros.Service.Interfaces;

import java.util.List;

import base_datos.alquiler_carros.Models.Dto.Request.PagoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.PagoResponseDTO;

public interface IPagoService {

    // Cliente paga → activa el alquiler automáticamente
    PagoResponseDTO pagar(PagoRequestDTO dto);

    // Consultas que sí se usan
    PagoResponseDTO buscarPorId(Long id);
    List<PagoResponseDTO> listarPorAlquiler(Long idAlquiler);
}