import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface PortalMenu {
  id?: number;
  idPadre?: number;
  idLang: string;
  nomMenu: string;
  hrefMenu: string;
  nivel?: number;
  posicionRaiz?: number;
  posicion?: number;
}
@Injectable({ providedIn: 'root' })
export class Menu {
  private url = 'http://localhost:8080/api/portal/menu';
  constructor(private http: HttpClient) {}

  create(menu: PortalMenu): Observable<PortalMenu> {
    return this.http.post<PortalMenu>(this.url + '/create', menu);
  }
  update(id: number, menu: PortalMenu): Observable<PortalMenu> {
    return this.http.put<PortalMenu>(this.url + `/update/` + id, menu);
  }
  getById(id: number): Observable<PortalMenu> {
    return this.http.get<PortalMenu>(this.url + '/' + id);
  }
  getAll() {
    return this.http.get<PortalMenu[]>(this.url);
  }
  delete(id: number) {
    return this.http.delete<PortalMenu>(this.url + '/delete/' + id);
  }
  search(filtro: string) {
    return this.http.get<PortalMenu>(this.url + `/search?filtro=${filtro}`);
  }
  getByNivel(nivel: number) {
    return this.http.get<PortalMenu[]>(`${this.url}?nivel=${nivel}`);
  }
  getByPadre(idPadre: number) {
    return this.http.get<PortalMenu[]>(this.url + '/padre/' + idPadre);
  }
}
