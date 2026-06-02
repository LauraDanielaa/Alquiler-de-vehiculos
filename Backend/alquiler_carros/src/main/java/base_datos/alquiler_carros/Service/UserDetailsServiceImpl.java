package base_datos.alquiler_carros.Service;

import base_datos.alquiler_carros.Models.Administrador;
import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Models.Empleado;
import base_datos.alquiler_carros.Models.Usuario;
import base_datos.alquiler_carros.Repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

       String rol;

        if (usuario instanceof Administrador) {
            rol = "ADMIN";
        } else if (usuario instanceof Empleado) {
            rol = "EMPLEADO";
        } else if (usuario instanceof Cliente) {
            rol = "CLIENTE";
        } else {
            throw new RuntimeException("Rol no válido");
        }

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(rol)
                .build();
    }
}