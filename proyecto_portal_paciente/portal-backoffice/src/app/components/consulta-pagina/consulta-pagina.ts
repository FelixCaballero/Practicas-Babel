import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Pagina, PortalPagina } from '../../services/pagina';
import { Router } from '@angular/router';

@Component({
  selector: 'app-consulta-pagina',
  imports: [FormsModule],
  templateUrl: './consulta-pagina.html',
  styleUrl: './consulta-pagina.css',
})
export class ConsultaPagina {
  filtro = '';
  resultado: PortalPagina | null = null;

  constructor(
    private service: Pagina,
    private router: Router,
  ) {}

  find(): void {
    this.service.search(this.filtro).subscribe({
      next: (data) => (this.resultado = data ?? null),
      error: (err) => {
        console.error(err);
        this.resultado = null;
      },
    });
  }

  update(): void {
    if (this.resultado?.id) {
      this.router.navigateByUrl(`/layout/mod-pagina/${this.resultado.id}`);
    }
  }

  delete(): void {
    if (this.resultado?.id && confirm('¿Seguro que quieres eliminar esta página?')) {
      this.service.delete(this.resultado.id).subscribe({
        next: () => {
          this.resultado = null;
          this.filtro = '';
        },
        error: (err) => console.error(err),
      });
    }
  }
}
