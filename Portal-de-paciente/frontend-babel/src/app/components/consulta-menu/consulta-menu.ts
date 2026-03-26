import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Menu } from '../../models/menu';
import { PortalService } from '../../services/portal.service';

/**
 * Componente que muestra la estructura de Menús en cascada (selectores secuenciales).
 * Recrea la vista anterior de Consulta Menú antes del árbol.
 */
@Component({
  selector: 'app-consulta-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './consulta-menu.html',

})
export class ConsultaMenuComponent implements OnInit {
  listaMenus: Menu[] = [];
  idiomaSeleccionado: string = '1'; // Inglés por defecto

  menuLevel1: Menu[] = [];
  menuLevel2: Menu[] = [];
  menuLevel3: Menu[] = [];

  selectedL1: Menu | null = null;
  selectedL2: Menu | null = null;
  selectedL3: Menu | null = null;

  constructor(private portalService: PortalService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cargarMenus();
  }

  cargarMenus() {
    this.portalService.getMenus().subscribe({
      next: (res) => {
        this.listaMenus = res;
        this.refrescarNiveles();
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar menús:', err)
    });
  }

  refrescarNiveles() {
    // Filtrar raíces (idPadre null o 0) y el idioma seleccionado
    this.menuLevel1 = this.listaMenus.filter(m => (!m.idPadre || m.idPadre === 0) && String(m.idLang) === String(this.idiomaSeleccionado));
    this.onL1Change(); // Propagate change downwards to level 2 and 3
  }

  onL1Change() {
    this.selectedL2 = null;
    this.selectedL3 = null;
    this.menuLevel3 = [];
    if (this.selectedL1) {
      this.menuLevel2 = this.listaMenus.filter(m => m.idPadre === this.selectedL1!.idMenu && String(m.idLang) === String(this.idiomaSeleccionado));
    } else {
      this.menuLevel2 = [];
    }
  }

  onL2Change() {
    this.selectedL3 = null;
    if (this.selectedL2) {
      this.menuLevel3 = this.listaMenus.filter(m => m.idPadre === this.selectedL2!.idMenu && String(m.idLang) === String(this.idiomaSeleccionado));
    } else {
      this.menuLevel3 = [];
    }
  }

  get actualSeleccionado(): Menu | null {
    return this.selectedL3 || this.selectedL2 || this.selectedL1;
  }

  editarMenu() {
    const m = this.actualSeleccionado;
    if (m) this.router.navigate(['/dashboard/alta-menu'], { state: { menu: m, isEdit: true } });
  }

  borrarMenu() {
    const m = this.actualSeleccionado;
    if (!m) return;
    
    const isAll = confirm('¿Desea eliminar el menú en todos sus idiomas?\n\nAceptar = Eliminar todos (4 idiomas)\nCancelar = Elegir siguiente opción');
    if (isAll) {
      this.portalService.deleteMenu(m.idMenu).subscribe({
        next: () => {
          alert('Menú borrado (todos los idiomas) con éxito');
          this.cargarMenus();
          this.selectedL1 = null;
          this.onL1Change();
        }
      });
    } else {
      const isSingle = confirm(`¿Desea eliminar SOLAMENTE el menú en el idioma seleccionado (${m.idLang})?`);
      if (isSingle) {
        this.portalService.deleteMenuLanguage(m.idMenu, String(m.idLang)).subscribe({
          next: () => {
            alert('Menú borrado (idioma) con éxito');
            this.cargarMenus();
            this.selectedL1 = null;
            this.onL1Change();
          }
        });
      }
    }
  }
}
