import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Menu, PortalMenu } from '../../services/menu';
import { Router } from '@angular/router';

interface MenuNode extends PortalMenu {
  children: MenuNode[];
}
@Component({
  selector: 'app-arbol-menus',
  imports: [CommonModule, FormsModule],
  templateUrl: './arbol-menus.html',
  styleUrl: './arbol-menus.css',
})
export class ArbolMenus implements OnInit {
  menus: MenuNode[] = [];
  cargando = true;

  constructor(
    private service: Menu,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.cargarMenusNivel1();
  }

  cargarMenusNivel1(): void {
    this.service.getByNivel(1).subscribe({
      next: (data) => {
        const soloNivel1 = data.filter(menu => menu.nivel === 1);
        this.menus = soloNivel1.map((menu) => ({
          ...menu,
          children: [],
        }));

        this.menus.forEach((menu) => this.cargarHijos(menu));
        this.cargando = false;
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
      },
    });
  }

  cargarHijos(menu: MenuNode): void {
    if (!menu.id) return;

    this.service.getByPadre(menu.id).subscribe({
      next: (hijos) => {
        menu.children = hijos.map((hijo) => ({
          ...hijo,
          children: [],
        }));

        menu.children.forEach((hijo) => this.cargarHijos(hijo));
      },
      error: (err) => console.error(err),
    });
  }

  esHoja(menu: MenuNode): boolean {
    return menu.children.length === 0;
  }

  modificar(menu: MenuNode): void {
    if (menu.id) {
      this.router.navigateByUrl(`/layout/mod-menu/${menu.id}`);
    }
  }
}
