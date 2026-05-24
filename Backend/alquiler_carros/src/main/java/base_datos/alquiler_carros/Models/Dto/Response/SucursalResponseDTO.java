package base_datos.alquiler_carros.Models.Dto.Response;

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
public class SucursalResponseDTO {

    private Long idSucursal;
    private String nombre;
    private String ciudad;
    private String telefono;
    private String direccion;
    
}