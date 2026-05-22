package base_datos.alquiler_carros.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@PrimaryKeyJoinColumn(name = "id_usuario")
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente extends Usuario{
    private String direccion;
    private String licencia_conduccion;

}
