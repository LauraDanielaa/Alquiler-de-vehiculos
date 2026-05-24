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
public class PagoResponseDTO {

    private Long idPago;
    private LocalDate fecha_pago;
    private BigDecimal monto;
    private String metodoPago;
    private String estado;
    private Long idAlquiler;
    private String nombreCliente;
    private String apellidoCliente;
}