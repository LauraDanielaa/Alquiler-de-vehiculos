package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IPagoRepository extends JpaRepository<Pago,Long> {

    List<Pago> findByAlquiler_Id(Long id_alquiler);
    List<Pago> findByEstado(String estado);
    List<Pago> findByMetodoPago(String metodoPago);

    // Total pagado por un alquiler
    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.alquiler.id = :id_alquiler")
    BigDecimal sumMontoByAlquiler(@Param("id_alquiler") Integer id_alquiler);
}