package base_datos.alquiler_carros.Models.Dto.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDTO {

    @NotNull
    private Long idVehiculo;

    @NotNull
    private LocalDate fecha_inicio;

    @NotNull
    private LocalDate fecha_fin;
}