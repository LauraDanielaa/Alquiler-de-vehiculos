package base_datos.alquiler_carros.Models.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String rol;
    private String username;
    private String nombre;
    private String apellido;
}