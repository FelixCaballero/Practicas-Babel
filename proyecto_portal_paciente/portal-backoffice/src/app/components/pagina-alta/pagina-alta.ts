import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { Pagina, PortalPagina } from '../../services/pagina';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-pagina-alta',
  standalone: true,
  imports: [FormsModule, NgIf, RouterLink, QuillModule],
  templateUrl: './pagina-alta.html',
  styleUrl: './pagina-alta.css',
})
export class PaginaAlta {
  pagina: PortalPagina = {
    id: 0,
    idLang: 'E',
    titulo: '',
    descripcion: '',
    migasPan: '',
    pagina: '',
    idCompleto: 0,
  };

  constructor(
    private service: Pagina,
    private router: Router,
  ) {}

  onSubmit() {
    this.service.create(this.pagina).subscribe({
      next: () => {
        this.router.navigateByUrl('/layout/consultar-pagina');
      },
      error: (err) => {
        console.error('Error al crear página', err);
      },
    });
  }
}
