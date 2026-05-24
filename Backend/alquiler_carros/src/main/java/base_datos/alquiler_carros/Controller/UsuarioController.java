package base_datos.alquiler_carros.Controller;

import base_datos.alquiler_carros.Models.Dto.Response.PerfilResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    // ─── Cualquier usuario autenticado ────────────────────────

    @GetMapping("/mi-perfil")
    public ResponseEntity<PerfilResponseDTO> verMiPerfil() {
        return ResponseEntity.ok(usuarioService.verMiPerfil());
    }

    @PutMapping("/mi-perfil")
    public ResponseEntity<PerfilResponseDTO> actualizarMiPerfil(
            @RequestBody @Valid ActualizarPerfilRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizarMiPerfil(dto));
    }

    // ─── Solo ADMIN ───────────────────────────────────────────

    @GetMapping("/clientes")
    public ResponseEntity<List<PerfilResponseDTO>> listarClientes() {
        return ResponseEntity.ok(usuarioService.listarClientes());
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<PerfilResponseDTO>> listarEmpleados() {
        return ResponseEntity.ok(usuarioService.listarEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PerfilResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(usuarioService.cambiarEstadoUsuario(id, estado));
    }
}