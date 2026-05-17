package base_datos.alquiler_carros.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Vehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long id;
    private String placa;
    private String marca;
    @Column(name = "año", nullable = false)
    private Integer anio;
    private String modelo;
    private String color;
    private Integer kilometraje;
    private BigDecimal precio_dia;
    @Column(nullable = false, length = 15)
    private String estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaVehiculo categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;
    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<Alquiler> alquileres;
    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<Reserva> reservas;
    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<Mantenimiento> mantenimientos;
}