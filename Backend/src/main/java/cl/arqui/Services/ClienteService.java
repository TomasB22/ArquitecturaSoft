package cl.arqui.Services;

import cl.arqui.Model.Cliente;
import cl.arqui.Repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que gestiona operaciones relacionadas con clientes.
 */
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    /**
     * Constructor del servicio ClienteService.
     *
     * @param clienteRepository Repositorio de clientes.
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Obtiene todos los clientes almacenados.
     *
     * @return ResponseEntity con la lista de clientes.
     */
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Obtiene un cliente por su identificador (ID).
     *
     * @param id Identificador del cliente.
     * @return El cliente encontrado o null si no existe.
     */
    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param cliente Cliente a guardar.
     * @return ResponseEntity con el cliente guardado.
     */
    public ResponseEntity<Cliente> save(Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    /**
     * Actualiza la información de un cliente existente.
     *
     * @param cliente Cliente con la información actualizada.
     * @param id      Identificador del cliente a actualizar.
     * @return ResponseEntity con el cliente actualizado o not found si no existe.
     */
    public ResponseEntity<Cliente> update(Cliente cliente, Long id) {
        Cliente cliente1 = clienteRepository.findById(id).orElse(null);
        if (cliente1 == null) {
            return ResponseEntity.notFound().build();
        }
        cliente1.setNombre(cliente.getNombre());
        cliente1.setDireccion(cliente.getDireccion());
        cliente1.setDeuda(cliente.getDeuda());
        return ResponseEntity.ok(clienteRepository.save(cliente1));
    }

    /**
     * Elimina un cliente por su identificador (ID).
     *
     * @param id Identificador del cliente a eliminar.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    public ResponseEntity<String> delete(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.delete(cliente);
        return ResponseEntity.ok("Cliente eliminado");
    }

    /**
     * Crea un nuevo cliente y lo guarda en la base de datos.
     *
     * @param cliente Cliente a crear y guardar.
     * @return ResponseEntity con el cliente creado o un mensaje de error en caso de fallo.
     */
    public ResponseEntity<?> crearCliente(Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteRepository.save(cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar cliente");
        }
    }
}
