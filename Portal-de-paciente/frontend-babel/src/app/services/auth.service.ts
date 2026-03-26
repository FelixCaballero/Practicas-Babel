import { Usuario } from '../models/usuario';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Servicio encargado de gestionar el proceso de autenticación de los usuarios en el sistema.
 */
@Injectable({ providedIn: 'root' })
export class AuthService {
  private urlAuth = 'http://localhost:8080/api/usuarios/login';

  constructor(private http: HttpClient) {}

  /**
   * Envía la solicitud de inicio de sesión con las credenciales introducidas.
   * @param user Objeto con el DNI (usuario) y contraseña.
   * @returns Un Observable que emite verdadero (true) si las credenciales son correctas, o falso (false) en caso contrario.
   */
  login(user: Usuario): Observable<boolean> {
    return this.http.post<boolean>(this.urlAuth, user);
  }
}
