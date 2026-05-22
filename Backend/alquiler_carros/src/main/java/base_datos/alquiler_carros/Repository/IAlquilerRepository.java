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

    List<Alquiler> findByCliente_Id(Long id_usuario);
    List<Alquiler> findByVehiculo_Id(Long id_vehiculo);
    List<Alquiler> findByEstado(String estado);

    // Alquileres activos de un vehículo en un rango de fechas
    @Query("""
        SELECT a FROM Alquiler a
        WHERE a.vehiculo.id = :id_vehiculo
        AND a.estado != 'CANCELADO'
        AND a.fecha_inicio <= :fecha_fin
        AND a.fecha_fin >= :fecha_inicio
    """)
    List<Alquiler> findAlquileresActivosEnRango(
            @Param("idVehiculo") Integer id_vehiculo,
            @Param("fechaInicio") LocalDate fecha_inicio,
            @Param("fechaFin") LocalDate fecha_fin);
}