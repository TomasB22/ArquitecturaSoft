package cl.arqui.Controller;

import cl.arqui.Model.Cliente;
import cl.arqui.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class MostrarClienteController {
    private final ClienteService clienteService;

    @Autowired
    public MostrarClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        return clienteService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
    @PostMapping
    public ResponseEntity<ResponseEntity<?>> createCliente(@RequestBody Cliente cliente) {
        ResponseEntity<?> nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        ResponseEntity<Cliente> response = clienteService.update(cliente, id);
        if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        ResponseEntity<String> response = clienteService.delete(id);
        if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.getBody());
    }



}
