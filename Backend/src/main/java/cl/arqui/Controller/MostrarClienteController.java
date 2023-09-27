package cl.arqui.Controller;

import cl.arqui.Model.Cliente;
import cl.arqui.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la gestión de clientes.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class MostrarClienteController {
    private final ClienteService clienteService;

    @Autowired
    public MostrarClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return ResponseEntity con la lista de clientes.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return clienteService.getAll();
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id ID del cliente a obtener.
     * @return ResponseEntity con el cliente encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente Cliente a crear.
     * @return ResponseEntity con el nuevo cliente creado.
     */
    @PostMapping
    public ResponseEntity<ResponseEntity<?>> createCliente(@RequestBody Cliente cliente) {
        ResponseEntity<?> nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    /**
     * Actualiza un cliente existente por su ID.
     *
     * @param id      ID del cliente a actualizar.
     * @param cliente Cliente con los datos actualizados.
     * @return ResponseEntity con el cliente actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        ResponseEntity<Cliente> response = clienteService.update(cliente, id);
        if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.getBody());
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id ID del cliente a eliminar.
     * @return ResponseEntity con un mensaje de confirmación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        ResponseEntity<String> response = clienteService.delete(id);
        if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.getBody());
    }
}