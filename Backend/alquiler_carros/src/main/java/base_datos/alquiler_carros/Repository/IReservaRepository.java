package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByCliente_Id(Long id_usuario);
    List<Reserva> findByVehiculo_Id(Long id_vehiculo);
    List<Reserva> findByEstado(String estado);

    // Cancelación automática — reemplaza el método derivado por @Query
    @Query("""
        SELECT r FROM Reserva r
        WHERE r.estado = :estado
        AND r.fecha_inicio <= :fecha
    """)
    List<Reserva> findReservasPendientesVencidas(
            @Param("estado") String estado,
            @Param("fecha") LocalDate fecha);

    // Validar disponibilidad — queda solo una versión
    @Query("""
        SELECT r FROM Reserva r
        WHERE r.vehiculo.id = :id_vehiculo
        AND r.estado NOT IN ('CANCELADA')
        AND r.fecha_inicio <= :fecha_fin
        AND r.fecha_fin >= :fecha_inicio
    """)
    List<Reserva> findReservasActivasEnRango(
            @Param("id_vehiculo") Long id_vehiculo,
            @Param("fecha_inicio") LocalDate fecha_inicio,
            @Param("fecha_fin") LocalDate fecha_fin);
}