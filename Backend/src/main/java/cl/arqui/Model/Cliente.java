package cl.arqui.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    private Long id;
    private String nombre;
    private String direccion;
    private int deuda;
}
