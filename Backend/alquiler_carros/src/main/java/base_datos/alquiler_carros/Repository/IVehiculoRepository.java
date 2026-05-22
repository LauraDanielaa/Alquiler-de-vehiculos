package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Optional<Vehiculo> findByPlaca(String placa);
    boolean existsByPlaca(String placa);
    List<Vehiculo> findByEstado(String estado);
    List<Vehiculo> findByCategoria_Id(Long idCategoria);
    List<Vehiculo> findBySucursal_Id(Long idSucursal);
    List<Vehiculo> findByMarca(String marca);

    // Vehículos disponibles en una sucursal por categoría
    List<Vehiculo> findByEstadoAndSucursal_IdAndCategoria_Id(
            String estado, Long idSucursal, Integer idCategoria);
}
