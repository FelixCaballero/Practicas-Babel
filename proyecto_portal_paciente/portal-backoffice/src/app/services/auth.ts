import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private url: string = 'http://localhost:8080/api/portal/usuario';
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<boolean> {
    return this.http.post<boolean>(
        `${this.url}/login`, { usuario: username, password }, { /*1*/ observe: 'response' }) //1: respuesta HTTP completa
      .pipe(
        //sirve para hacer una comprobacion sobre la respuesta HTTP
        map((res) => res.status === 200),
        tap((ok) => {
          if (ok) localStorage.setItem('user', username); //si devuelve ok(200) el usuario se loguea
        }),
      );
  }
  logout() {
    localStorage.removeItem('user');
  }
  isLogged():boolean{
    return !!localStorage.getItem('user');
  }

}
