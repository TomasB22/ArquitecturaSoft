import { Component, OnInit } from "@angular/core";
import { ApiService } from "./api.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit {
  /* Lista de clientes recuperados desde la API */
  clientes: any[] = [];
  /* Lista de clientes morosos filtrados */
  clientesMorosos: any[] = [];
  /* Cliente seleccionado para edición */
  clienteParaEditar: any = null;
  /* Cliente seleccionado para eliminar */
  clienteParaEliminar: any = null;
  /* Lista de clientes a mostrar en la tabla */
  clientesToDisplay: any[] = [];
  /* Bandera para mostrar u ocultar clientes morosos. */
  mostrarMorososFlag: boolean = false;
  /* "Bandera" que indica si se están mostrando clientes morosos */
  mostrandoMorosos: boolean = false;
  constructor(private apiService: ApiService) {}

  /* Método que se ejecuta al iniciar el componente */
  /* Recupera la lista de clientes desde la API y configura la actualización automática de la tabla. */
  ngOnInit() {
    this.apiService.getAllClientes().subscribe((data: any) => {
      this.clientes = data;
      this.clientesMorosos = this.clientes.filter(
        (cliente) => cliente.deuda > 0
      );
      this.clientesToDisplay = [...this.clientes];

      // Llama a la función 'autoActualizarTabla' cada 10 segundos (10000 milisegundos)
      setInterval(() => {
        this.autoActualizarTabla();
      }, 10000);
    });
  }

  /* Actualiza automáticamente la tabla de clientes. */
  autoActualizarTabla() {
    this.apiService.getAllClientes().subscribe(
      (data: any) => {
        this.clientes = data;
        this.clientesMorosos = this.clientes.filter(
          (cliente) => cliente.deuda > 0
        );

        if (this.mostrarMorososFlag) {
          this.clientesToDisplay = this.clientesMorosos;
        } else {
          this.clientesToDisplay = [...this.clientes];
        }
      },
      (error) => {
        console.error("Error al autoactualizar la lista de clientes:", error);
      }
    );
  }

  /* Prepara un cliente para la edición */
  editarCliente(cliente: any) {
    this.clienteParaEditar = { ...cliente };
  }

  /* Guarda los cambios realizados en la edición del cliente. */
  guardarEdicion() {
    if (this.clienteParaEditar) {
      this.apiService.updateCliente(this.clienteParaEditar).subscribe(
        (response: any) => {
          this.clienteParaEditar = null;
        },
        (error) => {
          console.error("Error al actualizar el cliente:", error);
        }
      );
    }
  }

  /* Cancela la edición del cliente. */
  cancelarEdicion() {
    this.clienteParaEditar = null;
  }

  /* Confirma la eliminación de un cliente */
  confirmarEliminarCliente(cliente: any) {
    const confirmacion = window.confirm(
      `¿Estás seguro de que deseas eliminar al cliente ${cliente.nombre}?`
    );

    if (confirmacion) {
      this.apiService.deleteCliente(cliente.id).subscribe(
        (response: any) => {
          this.clienteParaEliminar = null;
          // Llama a reloadClientes() dentro del subscribe
          this.reloadClientes(); // Recargar la lista de clientes después de eliminar
        },
        (error) => {
          console.error("Error al eliminar el cliente:", error);
        }
      );
    }
  }
  /* Elimina un cliente por su ID */
  eliminarCliente(clienteId: number) {
    this.apiService.deleteCliente(clienteId).subscribe(
      (response: any) => {
        this.clienteParaEliminar = null;
        this.reloadClientes();
      },
      (error) => {
        console.error("Error al eliminar el cliente:", error);
      }
    );
  }
  /* Filtra y muestra los clientes morosos. */
  mostrarMorosos() {
    if (this.mostrandoMorosos) {
      this.clientesToDisplay = [...this.clientes];
      this.mostrandoMorosos = false;
    } else {
      this.clientesToDisplay = this.clientes.filter(
        (cliente) => cliente.deuda > 0
      );
      this.mostrandoMorosos = true;
    }
  }
  /* Muestra todos los clientes, incluidos los no morosos. */
  mostrarTodos() {
    this.clientesToDisplay = [...this.clientes];
    this.mostrarMorososFlag = false;
  }

  /* Recarga la lista de clientes desde la API. */
  reloadClientes() {
    this.apiService.getAllClientes().subscribe(
      (data: any) => {
        this.clientes = data;
        this.clientesMorosos = this.clientes.filter(
          (cliente) => cliente.deuda > 0
        );

        if (this.mostrarMorososFlag) {
          this.clientesToDisplay = this.clientesMorosos;
        } else {
          this.clientesToDisplay = [...this.clientes];
        }
      },
      (error) => {
        console.error("Error al recargar la lista de clientes:", error);
      }
    );
  }
}
