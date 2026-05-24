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
public class MantenimientoResponseDTO {

    private Long idMantenimiento;
    private LocalDate fecha;
    private String descripcion;
    private BigDecimal costo;
    private Long idVehiculo;
    private String placaVehiculo;
    private String marcaVehiculo;
}