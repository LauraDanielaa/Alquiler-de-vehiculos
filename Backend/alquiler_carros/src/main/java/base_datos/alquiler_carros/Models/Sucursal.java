package base_datos.alquiler_carros.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long id;
    private String ciudad;
    private String telefono;
    private String direccion;
    private String nombre;
    @OneToMany(mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Empleado> empleados;
    @OneToMany(mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos;
}
