package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IMultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByAlquiler_id(Long id_alquiler);

    // Total de multas de un alquiler
    @Query("SELECT SUM(m.monto) FROM Multa m WHERE m.alquiler.id = :id_alquiler")
    BigDecimal sumMontoByAlquiler(@Param("id_alquiler") Long id_alquiler);
}