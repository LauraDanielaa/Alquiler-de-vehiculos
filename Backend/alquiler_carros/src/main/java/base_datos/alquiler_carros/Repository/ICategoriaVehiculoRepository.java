package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.CategoriaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoriaVehiculoRepository extends JpaRepository<CategoriaVehiculo, Long> {

    Optional<CategoriaVehiculo> findByNombreCategoria(String nombreCategoria);
    boolean existsByNombreCategoria(String nombreCategoria);

}