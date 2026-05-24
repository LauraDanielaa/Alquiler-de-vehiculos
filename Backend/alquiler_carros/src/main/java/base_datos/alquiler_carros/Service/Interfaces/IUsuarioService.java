package base_datos.alquiler_carros.Service.Interfaces;

import base_datos.alquiler_carros.Controller.ActualizarPerfilRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.PerfilResponseDTO;

import java.util.List;

public interface IUsuarioService {

    // Perfil del usuario autenticado
    PerfilResponseDTO verMiPerfil();
    PerfilResponseDTO actualizarMiPerfil(ActualizarPerfilRequestDTO dto);

    // Solo ADMIN
    List<PerfilResponseDTO> listarClientes();
    List<PerfilResponseDTO> listarEmpleados();
    PerfilResponseDTO buscarUsuarioPorId(Long id);
    PerfilResponseDTO cambiarEstadoUsuario(Long id, String estado);
}