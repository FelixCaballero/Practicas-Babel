import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Menu, PortalMenu } from '../../services/menu';
import { Router } from '@angular/router';

@Component({
  selector: 'app-consulta-menu',
  imports: [FormsModule],
  templateUrl: './consulta-menu.html',
  styleUrl: './consulta-menu.css',
})
export class ConsultaMenu {
  filtro = '';
  resultado: PortalMenu | null = null;

  constructor(
    private service: Menu,
    private router: Router,
  ) {}

  find(): void {
    this.service.search(this.filtro).subscribe({
      next: (data) => (
        this.resultado = data ?? null),
      error: (err) =>{
        (console.error(err),
          (this.resultado = null));
      }
    });
  }
  update(): void {
    if (this.resultado?.id) {
      this.router.navigateByUrl(`/layout/mod-menu/${this.resultado.id}`);
    } else {
      console.error('idPagina undefined');
    }
  }
  delete(): void {
    if (this.resultado?.id && confirm('¿Seguro que quieres eliminar este menú?')) {
      this.service.delete(this.resultado.id).subscribe({
        next: () => {
          this.resultado = null;
          this.filtro = '';
        },
        error: (err) => console.error(err),
      });
    } else {
      console.error('idPagina undefined o cancelado');
    }
  }
}
