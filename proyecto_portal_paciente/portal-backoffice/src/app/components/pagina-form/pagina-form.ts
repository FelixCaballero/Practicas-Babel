import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Pagina, PortalPagina } from '../../services/pagina';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-pagina-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, RouterLink, QuillModule],
  templateUrl: './pagina-form.html',
  styleUrl: './pagina-form.css',
})
export class PaginaForm implements OnInit {
  mensajeError = '';
  paginaForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private service: Pagina,
    private router: Router,
    private fb: FormBuilder,
  ) {
    this.paginaForm = this.fb.group({
      id: [0],
      idLang: ['E', Validators.required],
      titulo: ['', [Validators.required, Validators.maxLength(100)]],
      descripcion: ['', [Validators.maxLength(255)]],
      migasPan: ['', [Validators.maxLength(150)]],
      pagina: ['', Validators.required],
      idCompleto: [0],
    });
  }

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
        this.paginaForm.patchValue({
          id: p.id ?? 0,
          idLang: p.idLang ?? 'E',
          titulo: p.titulo ?? '',
          descripcion: p.descripcion ?? '',
          migasPan: p.migasPan ?? '',
          pagina: p.pagina ?? '',
          idCompleto: p.idCompleto ?? 0,
        });
      },
      error: (err) => {
        console.error('Error al cargar la página', err);
        this.router.navigateByUrl('/layout/consultar-pagina');
      },
    });
  }

  onSubmit() {
    this.mensajeError = '';

    if (this.paginaForm.invalid) {
      this.paginaForm.markAllAsTouched();
      this.mensajeError = 'Revisa los campos obligatorios y sus tamaños.';
      return;
    }

    const pagina = this.paginaForm.getRawValue();

    if (pagina.id != null) {
      this.service.update(pagina.id, pagina).subscribe({
        next: () => {
          this.router.navigateByUrl('/layout/consultar-pagina');
        },
        error: (err) => {
          this.mensajeError = 'Error al actualizar la página. Revisa los datos.';
          console.error('Error al actualizar página', err);
        },
      });
    }
  }
}
