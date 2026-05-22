package base_datos.alquiler_carros.Models.Dto.Response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaVehiculoResponseDTO {

    private Long idCategoria;
    private String nombreCategoria;
    private String descripcion;
}