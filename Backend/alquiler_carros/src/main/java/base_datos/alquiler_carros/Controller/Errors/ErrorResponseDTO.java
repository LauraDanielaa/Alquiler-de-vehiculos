package base_datos.alquiler_carros.Controller.Errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO {

    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
}