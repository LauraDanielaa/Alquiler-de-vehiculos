package base_datos.alquiler_carros.Models.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaVehiculoRequestDTO {

    @NotBlank
    private String nombreCategoria;

    @NotBlank
    private String descripcion;
}