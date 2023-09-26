import { Component, OnInit } from "@angular/core";
import { ApiService } from "./api.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit {
  clientes: any[] = []; // Esta variable almacenará los datos de la API

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    // Llama al servicio para obtener los datos de la API y asignarlos a la variable clientes
    this.apiService.getAllClientes().subscribe((data: any) => {
      this.clientes = data;
    });
  }
}
