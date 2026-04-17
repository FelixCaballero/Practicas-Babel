import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Menu } from '../models/menu';
import { Pagina } from '../models/pagina';

/**
 * Servicio encargado de gestionar la comunicación HTTP 
 * con la API REST del backend para las entidades Menu y Pagina.
 */
@Injectable({ providedIn: 'root' })
export class PortalService {
  private urlMenus = 'http://localhost:8080/api/menus';
  private urlPaginas = 'http://localhost:8080/api/paginas';

  constructor(private http: HttpClient) {}

  // Métodos para Menús
  
  /** Obtiene la lista completa de menús desde el servidor. */
  getMenus(): Observable<Menu[]> { return this.http.get<Menu[]>(this.urlMenus); }
  
  /** Envía una petición para guardar un nuevo menú. */
  saveMenu(menu: Menu): Observable<Menu> { return this.http.post<Menu>(this.urlMenus, menu); }
  
  /** Actualiza un menú existente enviando sus nuevos datos. */
  updateMenu(id: number, menu: Menu): Observable<Menu> { return this.http.put<Menu>(`${this.urlMenus}/${id}`, menu); }
  
  /** Solicita la eliminación de un menú por su identificador. */
  deleteMenu(id: number): Observable<void> { return this.http.delete<void>(`${this.urlMenus}/${id}`); }

  /** Solicita la eliminación de un menú por su identificador y lenguaje. */
  deleteMenuLanguage(id: number, langId: string): Observable<void> { return this.http.delete<void>(`${this.urlMenus}/${id}/lang/${langId}`); }

  // Métodos para Páginas
  
  /** Obtiene la lista completa de páginas. */
  getPaginas(): Observable<Pagina[]> { return this.http.get<Pagina[]>(this.urlPaginas); }
  
  /** Obtiene una página específica por su ID. */
  getPaginaById(id: string): Observable<Pagina> { return this.http.get<Pagina>(`${this.urlPaginas}/${id}`); }
  
  /** Crea una nueva página en el sistema. */
  savePagina(pag: Pagina): Observable<Pagina> { return this.http.post<Pagina>(this.urlPaginas, pag); }
  
  /** Actualiza el contenido y metadatos de una página existente. */
  updatePagina(id: string, pag: Pagina): Observable<Pagina> { return this.http.put<Pagina>(`${this.urlPaginas}/${id}`, pag); }
  
  /** Elimina una página permanentemente. */
  deletePagina(id: string): Observable<void> { return this.http.delete<void>(`${this.urlPaginas}/${id}`); }
}
