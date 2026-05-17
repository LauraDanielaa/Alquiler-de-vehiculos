package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {

    List<Sucursal> findByCiudad(String ciudad);
    Optional<Sucursal> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}