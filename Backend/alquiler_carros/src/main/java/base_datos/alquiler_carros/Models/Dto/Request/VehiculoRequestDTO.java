package base_datos.alquiler_carros.Models.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoRequestDTO {

    @NotBlank
    private String placa;

    @NotBlank
    private String marca;

    @NotNull
    private Integer anio;

    @NotBlank
    private String modelo;

    @NotBlank
    private String color;

    @NotNull
    private Integer kilometraje;

    @NotNull
    private BigDecimal precioDia;

    @NotBlank
    private String estado;

    @NotNull
    private Long idCategoria;

    @NotNull
    private Long idSucursal;
}