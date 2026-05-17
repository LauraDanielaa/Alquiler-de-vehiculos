package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findBySucursal_IdSucursal(Long idSucursal);
    List<Empleado> findByCargo(String cargo);
    Optional<Empleado> findByDocumento(Integer documento);
    boolean existsByDocumento(Integer documento);
}