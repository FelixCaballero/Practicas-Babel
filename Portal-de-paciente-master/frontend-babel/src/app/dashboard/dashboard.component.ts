import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PortalService } from '../services/portal.service';
import { Pagina } from '../models/pagina';
import { Menu } from '../models/menu';

/**
 * Componente principal del panel de control (Back Office).
 * Gestiona la navegación dinámica, así como las operaciones de creación,
 * edición, listado y borrado de Menús y Páginas de forma reactiva.
 */
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  view: string = 'home';
  isEditMenu: boolean = false;
  isEditPagina: boolean = false;

  // Listas para mostrar en las tablas
  listaPaginas: Pagina[] = [];
  listaMenus: Menu[] = [];

  // Navegación dinámica
  menuSeleccionado: Menu | null = null;
  paginasDelMenu: Pagina[] = [];
  paginaActual: Pagina | null = null;

  // Objetos para los formularios de Alta/Modificación
  nuevaPagina: Pagina = this.initPagina();
  nuevoMenu: Menu = this.initMenu();

  // Filtro
  filtroDescripcion: string = '';
  filtroMenu: string = '';

  get listaPaginasFiltrada(): Pagina[] {
    if (!this.filtroDescripcion) {
      return this.listaPaginas;
    }
    const filtro = this.filtroDescripcion.toLowerCase();
    return this.listaPaginas.filter(p => p.descripcion && p.descripcion.toLowerCase().includes(filtro));
  }

  get listaMenusFiltrada(): Menu[] {
    if (!this.filtroMenu) {
      return this.listaMenus;
    }
    const filtro = this.filtroMenu.toLowerCase();
    return this.listaMenus.filter(m => m.nomMenu && m.nomMenu.toLowerCase().includes(filtro));
  }

  constructor(private portalService: PortalService, private router: Router) {}

  /** 
   * Hook del ciclo de vida de Angular.
   * Se ejecuta al cargar el componente y desencadena la recuperación de los datos del servidor.
   */
  ngOnInit() {
    this.cargarDatos();
  }

  /**
   * Dispara simultáneamente la carga de páginas y menús desde la base de datos.
   */
  cargarDatos() {
    this.cargarPaginas();
    this.cargarMenus();
  }

  // --- Helpers Inicialización Formularios ---
  
  /** Retorna un objeto Pagina limpio con atributos por defecto para el formulario de Alta. */
  initPagina(): Pagina {
    return { idPagina: '', descripcion: '', pagina: '', migasPan: '', titulo: '', idLang: '' };
  }

  /** Retorna un objeto Menu limpio con atributos por defecto para el formulario de Alta. */
  initMenu(): Menu {
    return { idMenu: 0, idLang: '', nomMenu: '', hrefMenu: '', nivel: undefined as any, posicionRaiz: undefined as any, posicion: undefined as any, idPadre: undefined };
  }

  resetMenuForm() {
    this.nuevoMenu = this.initMenu();
    this.isEditMenu = false;
  }

  resetPaginaForm() {
    this.nuevaPagina = this.initPagina();
    this.isEditPagina = false;
  }

  // --- Navegación Dinámica ---
  
  /**
   * Transiciona la vista principal para mostrar el compendio de páginas 
   * asociadas al menú seleccionado en la barra lateral.
   * @param menu Referencia al menú clicado en el sidebar.
   */
  seleccionarMenu(menu: Menu) {
    this.menuSeleccionado = menu;
    this.paginaActual = null;
    this.paginasDelMenu = this.listaPaginas;
    this.view = 'menuPages';
  }

  /**
   * Transiciona la vista para renderizar el contenido HTML íntegro de una página.
   * @param pagina Objeto con los datos de la página a mostrar.
   */
  verContenidoPagina(pagina: Pagina) {
    // Usamos directamente el objeto ya cargado (ahorra una llamada HTTP)
    this.paginaActual = pagina;
    this.view = 'paginaContenido';
  }

  irAInicio() {
    this.menuSeleccionado = null;
    this.paginaActual = null;
    this.view = 'home';
  }

  // --- CRUD PÁGINAS ---
  cargarPaginas() {
    this.portalService.getPaginas().subscribe({
      next: (res) => this.listaPaginas = res,
      error: (err) => console.error('Error al cargar páginas:', err)
    });
  }

  guardarPagina() {
    const ob$ = this.isEditPagina 
      ? this.portalService.updatePagina(this.nuevaPagina.idPagina, this.nuevaPagina) 
      : this.portalService.savePagina(this.nuevaPagina);

    ob$.subscribe({
      next: () => {
        alert(this.isEditPagina ? 'Página actualizada con éxito' : 'Página guardada con éxito');
        this.cargarPaginas();
        this.resetPaginaForm();
        this.view = 'modPagina';
      },
      error: (err) => {
        console.error('Error al guardar página:', err);
        alert('Error al guardar página. Verifique los datos o consulte la consola.');
      }
    });
  }

  editarPagina(p: Pagina) {
    this.nuevaPagina = { ...p };
    this.isEditPagina = true;
    this.view = 'altaPagina';
  }

  borrarPagina(id: string) {
    if(confirm('¿Confirmas el borrado de la página en la Base de Datos?')) {
      this.portalService.deletePagina(id).subscribe({
        next: () => {
          alert('Página borrada con éxito');
          this.cargarPaginas();
        },
        error: (err) => {
          console.error('Error al borrar página:', err);
          alert('No se pudo borrar la página. Es posible que existan restricciones en base de datos.');
        }
      });
    }
  }

  // --- CRUD MENÚS ---
  cargarMenus() {
    this.portalService.getMenus().subscribe({
      next: (res) => this.listaMenus = res,
      error: (err) => console.error('Error al cargar menús:', err)
    });
  }

  guardarMenu() {
    const ob$ = this.isEditMenu
      ? this.portalService.updateMenu(this.nuevoMenu.idMenu, this.nuevoMenu)
      : this.portalService.saveMenu(this.nuevoMenu);

    ob$.subscribe({
      next: () => {
        alert(this.isEditMenu ? 'Menú actualizado con éxito' : 'Menú creado con éxito');
        this.cargarMenus();
        this.resetMenuForm();
        this.view = 'modMenu';
      },
      error: (err) => {
        console.error('Error al guardar menú:', err);
        alert('Error al guardar menú. Verifique los campos o restricciones.');
      }
    });
  }

  editarMenu(m: Menu) {
    this.nuevoMenu = { ...m };
    this.isEditMenu = true;
    this.view = 'altaMenu';
  }

  borrarMenu(id: number) {
    if(confirm('¿Confirmas el borrado del menú?')) {
      this.portalService.deleteMenu(id).subscribe({
        next: () => {
          alert('Menú borrado con éxito');
          this.cargarMenus();
        },
        error: (err) => {
          console.error('Error al borrar menú:', err);
          alert('No se pudo borrar el menú. Puede que tenga submenús asociados.');
        }
      });
    }
  }

  /** Borra los credenciales locales (si los hubiera) y devuelve al usuario a la pantalla Login. */
  logout() {
    this.router.navigate(['/login']);
  }
}
