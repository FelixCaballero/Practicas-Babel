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
  mensajeError = '';
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
    this.mensajeError = '';
    const  error = this.validarFormulario();
    if(error){
      this.mensajeError = error;
      return;
    }
    this.service.create(this.menu).subscribe({
      next: () => {
        this.router.navigateByUrl('/layout/consultar-menu');
      },
      error: (err) => {
        this.mensajeError = "Error al crear el menú cinsulta los datos";
        console.error('Error al crear menú', err);
      },
    });
  }
  validarFormulario(): string | null {
    const nomMenu = this.menu.nomMenu?.trim() || '';
    const hrefMenu = this.menu.hrefMenu?.trim() || '';

    if (!nomMenu) return 'El nombre es obligatorio.';
    if (nomMenu.length > 20) return 'El nombre no puede superar los 20 caracteres.';

    if (!hrefMenu) return 'El href es obligatorio.';
    if (hrefMenu.length > 50) return 'El href no puede superar los 50 caracteres.';

    if (!this.menu.idLang) return 'El idioma es obligatorio.';

    if (this.menu.nivel == null || this.menu.nivel < 1) {
      return 'El nivel debe ser mayor o igual que 1.';
    }

    if (this.menu.posicionRaiz == null || this.menu.posicionRaiz < 1) {
      return 'La posición raíz debe ser mayor o igual que 1.';
    }

    if (this.menu.posicion == null || this.menu.posicion < 1) {
      return 'La posición debe ser mayor o igual que 1.';
    }

    if (this.menu.nivel > 1 && (!this.menu.idPadre || this.menu.idPadre < 1)) {
      return 'Si el menú no es de nivel 1, debe tener Id Padre.';
    }

    if (this.menu.nivel === 1 && this.menu.idPadre && this.menu.idPadre > 0) {
      return 'Un menú de nivel 1 no debe tener padre.';
    }

    return null;
  }
}
