package base_datos.alquiler_carros.Models.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String telefono;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    @NotNull
    private Integer documento;

    // Solo CLIENTE
    private String direccion;
    private String licenciaConduccion;

    // Solo EMPLEADO
    private BigDecimal salario;
    private String cargo;
    private Long idSucursal;

    // Solo ADMIN
    private String nivelAcceso;

    @NotBlank
    // "CLIENTE", "EMPLEADO", "ADMIN"
    private String rol;
}