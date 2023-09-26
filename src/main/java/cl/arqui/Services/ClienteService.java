package cl.arqui.Services;

import cl.arqui.Model.Cliente;
import cl.arqui.Repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public ResponseEntity<List<Cliente>> getAll(){
        return ResponseEntity.ok((List<Cliente>) clienteRepository.findAll());
    }

    public Cliente getById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }
    public ResponseEntity<Cliente> save(Cliente cliente){
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    public ResponseEntity<Cliente> update(Cliente cliente, Long id){
        Cliente cliente1 = clienteRepository.findById(id).orElse(null);
        if(cliente1 == null){
            return ResponseEntity.notFound().build();
        }
        cliente1.setNombre(cliente.getNombre());
        cliente1.setDireccion(cliente.getDireccion());
        cliente1.setDeuda(cliente.getDeuda());
        return ResponseEntity.ok(clienteRepository.save(cliente1));
    }

    public ResponseEntity<String> delete(Long id){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.delete(cliente);
        return ResponseEntity.ok("Cliente eliminado");
    }

    public ResponseEntity<?> crearCliente(Cliente cliente){
        try{
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error al agregar cliente");
        }
    }


}
