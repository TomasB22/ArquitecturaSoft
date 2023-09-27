package cl.arqui;

import cl.arqui.Model.Cliente;
import cl.arqui.Repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Clase para cargar datos de ejemplo en la base de datos al iniciar la aplicación.
 */
@Component
class Preload implements CommandLineRunner {
    private final ClienteRepository clienteRepository;

    /**
     * Constructor de la clase Preload.
     *
     * @param clienteRepository Repositorio de clientes.
     */
    public Preload(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Método que se ejecuta al iniciar la aplicación para cargar los datos de ejemplo.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     * @throws Exception Si ocurre un error al cargar los datos.
     */
    @Override
    public void run(String... args) throws Exception {
        cargarClientes();
    }

    /**
     * Carga clientes de ejemplo en la base de datos.
     */
    public void cargarClientes() {
        try {
            // Crear y guardar clientes de ejemplo
            Cliente cliente1 = new Cliente();
            cliente1.setId(1L);
            cliente1.setNombre("Cliente 1");
            cliente1.setDireccion("Direccion 1");
            cliente1.setDeuda(10000);
            clienteRepository.save(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setId(2L);
            cliente2.setNombre("Cliente 2");
            cliente2.setDireccion("Direccion 2");
            cliente2.setDeuda(20000);
            clienteRepository.save(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setId(3L);
            cliente3.setNombre("Cliente 3");
            cliente3.setDireccion("Direccion 3");
            cliente3.setDeuda(30000);
            clienteRepository.save(cliente3);

            Cliente cliente4 = new Cliente();
            cliente4.setId(4L);
            cliente4.setNombre("Cliente 4");
            cliente4.setDireccion("Direccion 4");
            cliente4.setDeuda(40000);
            clienteRepository.save(cliente4);

            Cliente cliente5 = new Cliente();
            cliente5.setId(5L);
            cliente5.setNombre("Cliente 5");
            cliente5.setDireccion("Direccion 5");
            cliente5.setDeuda(0);
            clienteRepository.save(cliente5);

            Cliente cliente6 = new Cliente();
            cliente6.setId(6L);
            cliente6.setNombre("Cliente 6");
            cliente6.setDireccion("Direccion 6");
            cliente6.setDeuda(0);
            clienteRepository.save(cliente6);

            Cliente cliente7 = new Cliente();
            cliente7.setId(7L);
            cliente7.setNombre("Cliente 7");
            cliente7.setDireccion("Direccion 7");
            cliente7.setDeuda(1221210);
            clienteRepository.save(cliente7);

            System.out.println("Clientes cargados con éxito.");
        } catch (Exception e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        }
    }
}