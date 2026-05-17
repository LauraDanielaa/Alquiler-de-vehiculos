package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByDocumento(Integer documento);
    List<Administrador> findByNivelAcceso(String nivelAcceso);
    boolean existsByDocumento(Integer documento);
}