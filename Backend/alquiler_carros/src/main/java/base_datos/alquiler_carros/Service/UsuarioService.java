package base_datos.alquiler_carros.Service;

import base_datos.alquiler_carros.Controller.ActualizarPerfilRequestDTO;
import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Models.Dto.Response.PerfilResponseDTO;
import base_datos.alquiler_carros.Models.Empleado;
import base_datos.alquiler_carros.Models.Usuario;
import base_datos.alquiler_carros.Repository.IClienteRepository;
import base_datos.alquiler_carros.Repository.IEmpleadoRepository;
import base_datos.alquiler_carros.Repository.IUsuarioRepository;
import base_datos.alquiler_carros.Service.Interfaces.IUsuarioService;
import base_datos.alquiler_carros.Util.AuthUtils;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IClienteRepository clienteRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final AuthUtils authUtils;

    // ─── Ver perfil ───────────────────────────────────────────

    @Override
    public PerfilResponseDTO verMiPerfil() {
        return Mapper.toPerfilDTO(authUtils.getUsuarioAutenticado());
    }

    // ─── Actualizar perfil ────────────────────────────────────

    @Override
    @Transactional
    public PerfilResponseDTO actualizarMiPerfil(ActualizarPerfilRequestDTO dto) {

        Usuario usuario = authUtils.getUsuarioAutenticado();

        // Campos comunes
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());

        // Campos específicos por rol
        switch (usuario) {
            case Cliente c -> {
                if (dto.getDireccion() != null)
                    c.setDireccion(dto.getDireccion());
                if (dto.getLicencia_conduccion() != null)
                    c.setLicencia_conduccion(dto.getLicencia_conduccion());
                clienteRepository.save(c);
            }
            case Empleado e -> {
                if (dto.getCargo() != null)
                    e.setCargo(dto.getCargo());
                empleadoRepository.save(e);
            }
            default -> usuarioRepository.save(usuario);
        }

        return Mapper.toPerfilDTO(usuario);
    }

    // ─── Solo ADMIN ───────────────────────────────────────────

    @Override
    public List<PerfilResponseDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(Mapper::toPerfilDTO)
                .toList();
    }

    @Override
    public List<PerfilResponseDTO> listarEmpleados() {
        return empleadoRepository.findAll()
                .stream()
                .map(Mapper::toPerfilDTO)
                .toList();
    }

    @Override
    public PerfilResponseDTO buscarUsuarioPorId(Long id) {
        return Mapper.toPerfilDTO(usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id)));
    }

    @Override
    @Transactional
    public PerfilResponseDTO cambiarEstadoUsuario(Long id, String estado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        if (!estado.equals("ACTIVO") && !estado.equals("INACTIVO"))
            throw new RuntimeException("Estado no válido, debe ser ACTIVO o INACTIVO");

        usuario.setEstado(estado);
        usuarioRepository.save(usuario);

        return Mapper.toPerfilDTO(usuario);
    }
}