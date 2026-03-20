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
  menu: PortalMenu = {
    idPadre: 0,
    idLang: 'E',
    nomMenu: '',
    hrefMenu: '',
    nivel: 1,
    posicionRaiz: 1,
    posicion: 1,
  };

  constructor(
    private route: ActivatedRoute,
    private service: Menu,
    private router: Router,
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (idParam === null || isNaN(Number(idParam))) {
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

  onSubmit() {
    if (!this.menu.id) {
      console.error('No hay id para actualizar');
      return;
    }

    this.service.update(this.menu.id, this.menu).subscribe({
      next: () => {
        this.router.navigateByUrl('/layout/consultar-menu');
      },
      error: (err) => {
        console.error('Error al actualizar menú', err);
      },
    });
  }
}
