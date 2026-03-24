import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { Pagina, PortalPagina } from '../../services/pagina';

@Component({
  selector: 'app-pagina-form',
  standalone: true,
  imports: [FormsModule, NgIf, RouterLink],
  templateUrl: './pagina-form.html',
  styleUrl: './pagina-form.css',
})
export class PaginaForm implements OnInit {
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
    private route: ActivatedRoute,
    private service: Pagina,
    private router: Router,
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (idParam === null || isNaN(Number(idParam))) {
      console.error('ID inválido para modificar página');
      this.router.navigateByUrl('/layout/consultar-pagina');
      return;
    }

    const id = Number(idParam);

    this.service.getById(id).subscribe({
      next: (p) => {
        this.pagina = p;
      },
      error: (err) => {
        console.error('Error al cargar la página', err);
        this.router.navigateByUrl('/layout/consultar-pagina');
      },
    });
  }

  onSubmit() {
    if (this.pagina.id != null) {
      this.service.update(this.pagina.id, this.pagina).subscribe({
        next: () => {
          this.router.navigateByUrl('/layout/consultar-pagina');
        },
        error: (err) => {
          console.error('Error al actualizar página', err);
        },
      });
    }
  }
}
