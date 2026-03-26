import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Menu } from '../../models/menu';
import { PortalService } from '../../services/portal.service';

@Component({
  selector: 'app-alta-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './alta-menu.html',

})
export class AltaMenuComponent implements OnInit {
  nuevoMenu: Menu = this.initMenu();
  isEditMenu: boolean = false;

  constructor(private portalService: PortalService, private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      if (navigation.extras.state['menu']) {
        this.nuevoMenu = { ...navigation.extras.state['menu'] };
        this.isEditMenu = navigation.extras.state['isEdit'] ?? true;
      }
      if (navigation.extras.state['lang']) {
        const lang = navigation.extras.state['lang'];
        const mapLang: { [key: string]: string } = { 'EN': '1', 'CA': '2', 'VA': '3', 'GL': '4' };
        this.nuevoMenu.idLang = mapLang[lang] || '';
        this.isEditMenu = false;
      }
    }
  }

  ngOnInit() {}

  initMenu(): Menu {
    return { idMenu: 0, idLang: '', nomMenu: '', hrefMenu: '', nivel: undefined as any, posicionRaiz: undefined as any, posicion: undefined as any, idPadre: undefined };
  }

  guardarMenu() {
    const ob$ = this.isEditMenu
      ? this.portalService.updateMenu(this.nuevoMenu.idMenu, this.nuevoMenu)
      : this.portalService.saveMenu(this.nuevoMenu);

    ob$.subscribe({
      next: () => {
        alert(this.isEditMenu ? 'Menú actualizado con éxito' : 'Menú creado con éxito');
        this.router.navigate(['/dashboard/mod-menu']);
      },
      error: (err) => {
        console.error('Error al guardar menú:', err);
        alert('Error al guardar menú. Verifique los campos o restricciones.');
      }
    });
  }

  cancelar() {
    this.router.navigate(['/dashboard/mod-menu']);
  }
}
