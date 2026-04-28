import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { Menu, PortalMenu } from '../../services/menu';

@Component({
  selector: 'app-menu-form',
  standalone: true,
  imports: [FormsModule, NgIf, RouterLink],
  templateUrl: './menu-form.html',
  styleUrl: './menu-form.css',
})
export class MenuForm implements OnInit {
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
    private route: ActivatedRoute,
    private service: Menu,
    private router: Router,
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (idParam == null || isNaN(Number(idParam))) {
      console.error('ID inválido para modificar menú');
      this.router.navigateByUrl('/layout/consultar-menu');
      return;
    }

    const id = Number(idParam);

    this.service.getById(id).subscribe({
      next: (m) => {
        this.menu = m;
      },
      error: (err) => {
        console.error('Error al cargar el menú', err);
        this.router.navigateByUrl('/layout/consultar-menu');
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

  onSubmit() {
    this.mensajeError = '';
    const error = this.validarFormulario();
    if (error) {
      this.mensajeError = error;
      return;
    }
    if (!this.menu.id) {
      console.error('No hay id para actualizar');
      return;
    }

    this.service.update(this.menu.id, this.menu).subscribe({
      next: () => {
        this.router.navigateByUrl('/layout/consultar-menu');
      },
      error: (err) => {
        this.mensajeError = 'Error al actualizar menú. Revisa los datos.';
        console.error('Error al actualizar menú', err);
      },
    });
  }
}
