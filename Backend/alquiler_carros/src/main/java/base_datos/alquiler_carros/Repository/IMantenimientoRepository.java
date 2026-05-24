package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMantenimientoRepository extends JpaRepository<Mantenimiento, Long> {

    List<Mantenimiento> findByVehiculo_Id(Long id_vehiculo);
    List<Mantenimiento> findByFechaBetween(LocalDate fecha_inicio, LocalDate fecha_fin);

    // Costo total de mantenimientos de un vehículo
    @Query("SELECT SUM(m.costo) FROM Mantenimiento m WHERE m.vehiculo.id = :id_vehiculo")
    BigDecimal sumCostoByVehiculo(@Param("id_vehiculo") Long id_vehiculo);
}