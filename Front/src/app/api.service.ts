import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ApiService {
  private apiUrl = "http://localhost:8080/clientes"; // Reemplaza con la URL correcta de tu API

  constructor(private http: HttpClient) {}

  getAllClientes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  createCliente(cliente: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, cliente);
  }

  updateCliente(cliente: any): Observable<any> {
    const url = `${this.apiUrl}/${cliente.id}`;
    return this.http.put<any>(url, cliente);
  }

  deleteCliente(clienteId: number): Observable<any> {
    const url = `${this.apiUrl}/${clienteId}`;
    return this.http.delete<any>(url);
  }
}
