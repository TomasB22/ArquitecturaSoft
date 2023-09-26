package cl.arqui.Repository;

import cl.arqui.Model.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClienteRepository extends CrudRepository<Cliente,Long> {
}
