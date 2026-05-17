package base_datos.alquiler_carros.Models;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Pago")
@Getter @Setter @NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;
    private LocalDate fecha_pago;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    private String metodoPago;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alquiler", nullable = false)
    private Alquiler alquiler;
}