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
    List<Vehiculo> findByCategoria_IdCategoria(Integer idCategoria);
    List<Vehiculo> findBySucursal_IdSucursal(Integer idSucursal);
    List<Vehiculo> findByMarca(String marca);

    // Vehículos disponibles en una sucursal por categoría
    List<Vehiculo> findByEstadoAndSucursal_IdSucursalAndCategoria_IdCategoria(
            String estado, Integer idSucursal, Integer idCategoria);
}
