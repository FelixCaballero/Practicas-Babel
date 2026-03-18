import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PortalMenu } from './menu';

export interface PortalPagina {
  id?: number;
  descripcion: string;
  pagina: string;
  migasPan: string;
  titulo: string;
  idLang: string;
}
@Injectable({ providedIn: 'root' })
export class Pagina {
  private url: string = 'http://localhost:8080/api/portal/pagina';
  constructor(private http: HttpClient) {}

  create(pagina: PortalPagina): Observable<PortalPagina> {
    return this.http.post<PortalPagina>(this.url + '/create', pagina);
  }
  getAll() {
    return this.http.get<PortalPagina>(this.url);
  }
  update(id: number, menu: PortalPagina) {
    return this.http.put<PortalPagina>(this.url + '/update/' + id, menu);
  }
  delete(id: number) {
    return this.http.delete<PortalPagina>(this.url + '/delete/' + id);
  }
  getById(id: number): Observable<PortalPagina> {
    return this.http.get<PortalPagina>(this.url + '/' + id);
  }
  search(filtro: string) {
    return this.http.get<PortalPagina>(`http://localhost:8080/api/portal/pagina/search/${filtro}`);
  }
}
