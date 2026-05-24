package base_datos.alquiler_carros.Models.Dto.Response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResponseDTO {

    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String telefono;
    private String estado;
    private LocalDate fecha_registro;
    private String rol;

    // Solo Cliente
    private Integer documento;
    private String direccion;
    private String licencia_conduccion;

    // Solo Empleado
    private String cargo;
    private BigDecimal salario;
    private String nombreSucursal;

    // Solo Admin
    private String nivel_acceso;
}