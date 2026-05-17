package base_datos.alquiler_carros.Repository;

import base_datos.alquiler_carros.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByUsername(String username);
    Optional<Cliente> findByDocumento(Integer documento);
    boolean existsByDocumento(Integer documento);
    List<Cliente> findByEstado(String estado);
}