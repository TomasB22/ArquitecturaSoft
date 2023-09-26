import { Component, OnInit } from "@angular/core";
import { ApiService } from "./api.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit {
  clientes: any[] = [];
  clientesMorosos: any[] = [];
  clienteParaEditar: any = null;
  clienteParaEliminar: any = null;
  clientesToDisplay: any[] = [];
  mostrarMorososFlag: boolean = false;
  mostrandoMorosos: boolean = false;
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getAllClientes().subscribe((data: any) => {
      this.clientes = data;
      this.clientesMorosos = this.clientes.filter(
        (cliente) => cliente.deuda > 0
      );
      this.clientesToDisplay = [...this.clientes];

      // Llama a la función 'autoActualizarTabla' cada 10 segundos (10000 milisegundos)
      /* No funciona :() */
      setInterval(() => {
        this.autoActualizarTabla();
      }, 10000);
    });
  }

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

  editarCliente(cliente: any) {
    this.clienteParaEditar = { ...cliente };
  }

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

  cancelarEdicion() {
    this.clienteParaEditar = null;
  }

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

  mostrarTodos() {
    this.clientesToDisplay = [...this.clientes];
    this.mostrarMorososFlag = false;
  }

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
