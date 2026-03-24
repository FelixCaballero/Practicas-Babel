import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { Menu, PortalMenu } from '../../services/menu';

@Component({
  selector: 'app-menu-alta',
  standalone: true,
  imports: [FormsModule, NgIf, RouterLink],
  templateUrl: './menu-alta.html',
  styleUrl: './menu-alta.css',
})
export class MenuAlta {
  menu: PortalMenu = {
    idPadre: 0,
    idLang: 'E',
    nomMenu: '',
    hrefMenu: '',
    nivel: 1,
    posicionRaiz: 1,
    posicion: 1,
    idCompleto: 0,
  };

  constructor(
    private service: Menu,
    private router: Router,
  ) {}

  onSubmit() {
    this.service.create(this.menu).subscribe({
      next: () => {
        this.router.navigateByUrl('/layout/consultar-menu');
      },
      error: (err) => {
        console.error('Error al crear menú', err);
      },
    });
  }
}
