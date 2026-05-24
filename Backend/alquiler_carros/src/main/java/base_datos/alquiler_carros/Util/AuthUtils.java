package base_datos.alquiler_carros.Util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Models.Empleado;
import base_datos.alquiler_carros.Models.Usuario;
import base_datos.alquiler_carros.Repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUtils {

    private final IUsuarioRepository usuarioRepository;

    public Usuario getUsuarioAutenticado() {
        // Obtiene el username del token JWT que está en el SecurityContext
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Cliente getClienteAutenticado() {
        Usuario usuario = getUsuarioAutenticado();
        if (!(usuario instanceof Cliente cliente))
            throw new RuntimeException("El usuario autenticado no es un cliente");
        return cliente;
    }

    public Empleado getEmpleadoAutenticado() {
        Usuario usuario = getUsuarioAutenticado();
        if (!(usuario instanceof Empleado empleado))
            throw new RuntimeException("El usuario autenticado no es un empleado");
        return empleado;
    }
}