package base_datos.alquiler_carros.Models.Dto.Response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultaResponseDTO {

    private Long idMulta;
    private String descripcion;
    private BigDecimal monto;
    private LocalDate fecha;
    private Long idAlquiler;
    private String nombreCliente;
    private String apellidoCliente;
    private String placaVehiculo;
}