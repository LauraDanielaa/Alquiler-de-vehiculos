package base_datos.alquiler_carros.Controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPerfilRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String telefono;

    @Email
    @NotBlank
    private String email;

    // Solo Cliente
    private String direccion;
    private String licencia_conduccion;

    // Solo Empleado
    private String cargo;
}