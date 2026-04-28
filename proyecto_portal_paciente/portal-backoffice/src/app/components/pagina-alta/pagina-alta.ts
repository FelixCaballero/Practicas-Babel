import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Pagina } from '../../services/pagina';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-pagina-alta',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, RouterLink, QuillModule],
  templateUrl: './pagina-alta.html',
  styleUrl: './pagina-alta.css',
})
export class PaginaAlta {
  mensajeError = '';
  paginaForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: Pagina,
    private router: Router,
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

  onSubmit() {
    this.mensajeError = '';
    if (this.paginaForm.invalid) {
      this.paginaForm.markAllAsTouched();
      this.mensajeError = 'Revisa los campos obligatorios y sus tamaños.';
      return;
    }
    const pagina = this.paginaForm.getRawValue();

    this.service.create(pagina).subscribe({
      next: () => {
        this.router.navigateByUrl('/layout/consultar-pagina');
      },
      error: (err) => {
        console.error('Error al crear página', err);
      },
    });
  }
}
