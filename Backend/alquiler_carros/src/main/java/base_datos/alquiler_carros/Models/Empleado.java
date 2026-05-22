package base_datos.alquiler_carros.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@PrimaryKeyJoinColumn(name = "id_usuario")
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Empleado extends Usuario{
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;
    private String cargo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;

}
