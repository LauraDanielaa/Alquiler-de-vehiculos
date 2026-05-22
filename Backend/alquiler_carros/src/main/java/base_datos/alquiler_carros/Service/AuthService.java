package base_datos.alquiler_carros.Service;

import base_datos.alquiler_carros.Models.*;
import base_datos.alquiler_carros.Models.Dto.Request.LoginRequest;
import base_datos.alquiler_carros.Models.Dto.Request.RegisterRequest;
import base_datos.alquiler_carros.Models.Dto.Response.AuthResponse;
import base_datos.alquiler_carros.Repository.*;
import base_datos.alquiler_carros.Util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuarioRepository usuarioRepository;
    private final IClienteRepository clienteRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final IAdministradorRepository adminRepository;
    private final ISucursalRepository sucursalRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (usuarioRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("El username ya está en uso");
        if (usuarioRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("El email ya está en uso");

        String rol = request.getRol().toUpperCase();
        String token = switch (rol) {
            case "CLIENTE"  -> registrarCliente(request);
            case "EMPLEADO" -> registrarEmpleado(request);
            case "ADMIN"    -> registrarAdmin(request);
            default -> throw new RuntimeException("Rol no válido: " + rol);
        };

        return new AuthResponse(token, rol, request.getUsername(),
                request.getNombre(), request.getApellido());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String rol = switch (usuario) {
            case Administrador a -> "ADMIN";
            case Empleado e      -> "EMPLEADO";
            case Cliente c       -> "CLIENTE";
            default -> throw new RuntimeException("Rol no reconocido");
        };

        String token = jwtUtil.generateToken(usuario.getUsername(), rol);
        return new AuthResponse(token, rol, usuario.getUsername(),
                usuario.getNombre(), usuario.getApellido());
    }

    // ── privados ──────────────────────────────────────────

    private String registrarCliente(RegisterRequest req) {
        Cliente cliente = new Cliente();
        llenarDatosBase(cliente, req);
        cliente.setDocumento(req.getDocumento());
        cliente.setDireccion(req.getDireccion());
        cliente.setLicencia_conduccion(req.getLicenciaConduccion());
        clienteRepository.save(cliente);
        return jwtUtil.generateToken(cliente.getUsername(), "CLIENTE");
    }


    protected String registrarEmpleado(RegisterRequest req) {
        Sucursal sucursal = sucursalRepository.findById(req.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Empleado empleado = new Empleado();
        llenarDatosBase(empleado, req);
        empleado.setDocumento(req.getDocumento());
        empleado.setSalario(req.getSalario());
        empleado.setCargo(req.getCargo());
        empleado.setSucursal(sucursal);
        empleadoRepository.save(empleado);
        return jwtUtil.generateToken(empleado.getUsername(), "EMPLEADO");
    }

    private String registrarAdmin(RegisterRequest req) {
        Administrador admin = new Administrador();
        llenarDatosBase(admin, req);
        admin.setDocumento(req.getDocumento());
        admin.setNivelAcceso(req.getNivelAcceso());
        adminRepository.save(admin);
        return jwtUtil.generateToken(admin.getUsername(), "ADMIN");
    }


    private void llenarDatosBase(Usuario u, RegisterRequest req) {
        u.setNombre(req.getNombre());
        u.setApellido(req.getApellido());
        u.setTelefono(req.getTelefono());
        u.setEmail(req.getEmail());
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setEstado("ACTIVO");
        u.setFecha_registro(LocalDate.now());
    }
}
