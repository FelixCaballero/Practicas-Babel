import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Importar FormsModule
import { Router } from '@angular/router';
import { Menu } from '../../models/menu';
import { PortalService } from '../../services/portal.service';

/**
 * Componente que muestra la estructura de Menús en forma de Árbol Jerárquico.
 * Permite seleccionar nodos para modificarlos, borrarlos o añadir traducciones.
 */
@Component({
  selector: 'app-consulta-arbol',
  standalone: true,
  imports: [CommonModule, FormsModule], // Agregar FormsModule aquí
  templateUrl: './consulta-arbol.html',

})
export class ConsultaArbolComponent implements OnInit {
  arbolCompleto: any[] = [];
  nodoSeleccionado: any = null;
  idiomaSeleccionado: string = '1'; // Inglés por defecto

  constructor(private portalService: PortalService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cargarMenus();
  }

  /**
   * Carga los menús desde el backend y construye la estructura en árbol.
   */
  cargarMenus() {
    this.portalService.getMenus().subscribe({
      next: (res) => {
        // Filtrar por idioma
        const menusFiltrados = res.filter(m => String(m.idLang) === String(this.idiomaSeleccionado));
        this.arbolCompleto = this.construirArbol(menusFiltrados);
        this.cdr.detectChanges(); // Force rendering update
      },
      error: (err) => console.error('Error al cargar menús arbol:', err)
    });
  }

  /**
   * Transforma una lista plana de menús en una jerarquía padre-hijo basada en ID_PADRE.
   * @param menus Listado de menús plano.
   * @returns Listado de nodos raíz con su respectiva descendencia.
   */
  construirArbol(menus: Menu[]): any[] {
    const mapa = new Map<number, any>();
    menus.forEach(m => mapa.set(m.idMenu, { ...m, hijos: [] }));

    const raices: any[] = [];
    mapa.forEach(nodo => {
      if (nodo.idPadre && nodo.idPadre !== 0) {
        const padre = mapa.get(nodo.idPadre);
        if (padre) padre.hijos.push(nodo);
        else raices.push(nodo);
      } else {
        raices.push(nodo);
      }
    });
    return raices;
  }

  /**
   * Redirige al formulario de Alta/Edición de menú con los datos del nodo elegido.
   * @param m Información del menú.
   */
  editarMenu(m: any) {
    this.router.navigate(['/dashboard/alta-menu'], { state: { menu: m, isEdit: true } });
  }

  /**
   * Solicita el borrado de un menú proporcionado.
   * @param m Información del menú.
   */
  borrarMenu(m: any) {
    const isAll = confirm('¿Desea eliminar el menú en todos sus idiomas?\n\nAceptar = Eliminar todos (4 idiomas)\nCancelar = Elegir siguiente opción');
    if (isAll) {
      this.portalService.deleteMenu(m.idMenu).subscribe({
        next: () => {
          alert('Menú borrado (todos los idiomas) con éxito');
          this.cargarMenus();
          this.nodoSeleccionado = null;
        },
        error: (err) => {
          console.error('Error al borrar menú:', err);
          alert('No se pudo borrar el menú. Puede que tenga submenús asociados.');
        }
      });
    } else {
      const isSingle = confirm(`¿Desea eliminar SOLAMENTE el menú en el idioma seleccionado (${m.idLang})?`);
      if (isSingle) {
        this.portalService.deleteMenuLanguage(m.idMenu, String(m.idLang)).subscribe({
          next: () => {
            alert('Menú borrado (idioma seleccionado) con éxito');
            this.cargarMenus();
            this.nodoSeleccionado = null;
          },
          error: (err) => {
            console.error('Error al borrar menú (idioma):', err);
            alert('No se pudo borrar el menú en ese idioma. Puede que tenga submenús asociados.');
          }
        });
      }
    }
  }

  /**
   * Redirige para crear una traducción del menú seleccionado en el lenguaje facilitado.
   * @param m Menú base de referencia.
   * @param lang Identificador abreviado del idioma.
   */
  crearTraduccionMenu(m: any, lang: string) {
    this.router.navigate(['/dashboard/alta-menu'], { state: { menu: m, lang: lang } });
  }
}
