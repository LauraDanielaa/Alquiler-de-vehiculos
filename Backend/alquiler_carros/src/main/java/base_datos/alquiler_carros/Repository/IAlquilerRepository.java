package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByCliente_Id(Long idCliente);
    List<Alquiler> findByVehiculo_Id(Long idVehiculo);
    List<Alquiler> findByEstado(String estado);

    // Cancelación automática
    @Query("""
        SELECT a FROM Alquiler a
        WHERE a.estado = :estado
        AND a.fecha_inicio <= :fecha
    """)
    List<Alquiler> findAlquileresPendientesPagoVencidos(
            @Param("estado") String estado,
            @Param("fecha") LocalDate fecha);

    // Alquileres activos de un vehículo en un rango de fechas
    @Query("""
        SELECT a FROM Alquiler a
        WHERE a.vehiculo.id = :id_vehiculo
        AND a.estado NOT IN ('CANCELADO', 'FINALIZADO')
        AND a.fecha_inicio <= :fecha_fin
        AND a.fecha_fin >= :fecha_inicio
    """)
    List<Alquiler> findAlquileresActivosEnRango(
            @Param("id_vehiculo") Long id_vehiculo,
            @Param("fecha_inicio") LocalDate fecha_inicio,
            @Param("fecha_fin") LocalDate fecha_fin);
}