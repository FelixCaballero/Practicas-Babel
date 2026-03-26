import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Menu } from '../../models/menu';
import { PortalService } from '../../services/portal.service';

@Component({
  selector: 'app-mod-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './mod-menu.html',

})
export class ModMenuComponent implements OnInit {
  listaMenus: Menu[] = [];
  idiomaSeleccionado: string = '1'; // Inglés por defecto

  filtroMenu: string = '';
  menuSeleccionado: Menu | null = null;

  constructor(private portalService: PortalService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cargarMenus();
  }

  cargarMenus() {
    this.portalService.getMenus().subscribe({
      next: (res) => {
        this.listaMenus = res;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar menús:', err)
    });
  }

  get listaMenusFiltrada(): Menu[] {
    const menus = this.listaMenus.filter(m => String(m.idLang) === String(this.idiomaSeleccionado));
    if (!this.filtroMenu) return menus;
    const filtro = this.filtroMenu.toLowerCase();
    return menus.filter(m => m.nomMenu && m.nomMenu.toLowerCase().includes(filtro));
  }

  editarMenu(m: Menu) {
    this.router.navigate(['/dashboard/alta-menu'], { state: { menu: m, isEdit: true } });
  }

  borrarMenu(m: Menu) {
    const isAll = confirm('¿Desea eliminar el menú en todos sus idiomas?\n\nAceptar = Eliminar todos (4 idiomas)\nCancelar = Elegir siguiente opción');
    if (isAll) {
      this.portalService.deleteMenu(m.idMenu).subscribe({
        next: () => {
          alert('Menú borrado (todos los idiomas) con éxito');
          this.cargarMenus();
          this.menuSeleccionado = null;
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
            this.menuSeleccionado = null;
          },
          error: (err) => {
            console.error('Error al borrar menú (idioma):', err);
            alert('No se pudo borrar el menú en ese idioma. Puede que tenga submenús asociados.');
          }
        });
      }
    }
  }

  crearTraduccionMenu(m: Menu, lang: string) {
    this.router.navigate(['/dashboard/alta-menu'], { state: { menu: m, lang: lang } });
  }

  tieneTraduccion(lang: string): boolean {
    if (!this.menuSeleccionado) return false;
    const mapLang: { [key: string]: string } = { 'EN': '1', 'CA': '2', 'VA': '3', 'GL': '4' };
    const langId = mapLang[lang];
    return this.listaMenus.some(m => m.idMenu === this.menuSeleccionado!.idMenu && String(m.idLang) === String(langId));
  }
}
