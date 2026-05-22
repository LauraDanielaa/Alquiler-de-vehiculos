package base_datos.alquiler_carros.Models.Dto.Response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {

    private Long idVehiculo;
    private String placa;
    private String marca;
    private Integer anio;
    private String modelo;
    private String color;
    private Integer kilometraje;
    private BigDecimal precioDia;
    private String estado;
    private CategoriaVehiculoResponseDTO categoria;
    private String nombreSucursal;
}