import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pagina } from '../../models/pagina';
import { PortalService } from '../../services/portal.service';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-alta-pagina',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, QuillModule],
  templateUrl: './alta-pagina.html',

})
export class AltaPaginaComponent implements OnInit {
  paginaForm: FormGroup;
  isEditPagina: boolean = false;
  idPaginaEdit: string = '';

  quillModules = {
    toolbar: [
      ['bold', 'italic', 'underline', 'strike'],
      ['blockquote', 'code-block'],
      [{ 'header': 1 }, { 'header': 2 }],
      [{ 'list': 'ordered'}, { 'list': 'bullet' }],
      [{ 'script': 'sub'}, { 'script': 'super' }],
      [{ 'indent': '-1'}, { 'indent': '+1' }],
      [{ 'size': ['small', false, 'large', 'huge'] }],
      [{ 'color': [] }, { 'background': [] }],
      [{ 'align': [] }],
      ['clean'],
      ['link', 'image', 'video']
    ]
  };

  constructor(private fb: FormBuilder, private portalService: PortalService, private router: Router) {
    this.paginaForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(120)]],
      descripcion: ['', [Validators.required, Validators.maxLength(120)]],
      idLang: ['', Validators.required],
      migasPan: [''],
      pagina: ['', Validators.required]
    });

    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      if (navigation.extras.state['pagina']) {
        const pag = navigation.extras.state['pagina'];
        this.idPaginaEdit = pag.idPagina;
        this.paginaForm.patchValue(pag);
        this.isEditPagina = navigation.extras.state['isEdit'] ?? true;
      }
      if (navigation.extras.state['lang']) {
        const lang = navigation.extras.state['lang'];
        const mapLang: { [key: string]: string } = { 'EN': '1', 'CA': '2', 'VA': '3', 'GL': '4' };
        this.paginaForm.patchValue({ idLang: mapLang[lang] || '' });
        this.isEditPagina = false;
      }
    }
  }

  ngOnInit() {}

  guardarPagina() {
    if (this.paginaForm.invalid) {
      this.paginaForm.markAllAsTouched();
      return;
    }

    const valueStr = this.paginaForm.value;
    const saveObj: Pagina = {
      idPagina: this.idPaginaEdit,
      ...valueStr
    };

    const ob$ = this.isEditPagina 
      ? this.portalService.updatePagina(this.idPaginaEdit, saveObj) 
      : this.portalService.savePagina(saveObj);

    ob$.subscribe({
      next: () => {
        alert(this.isEditPagina ? 'Página actualizada con éxito' : 'Página guardada con éxito');
        this.router.navigate(['/dashboard/mod-pagina']);
      },
      error: (err) => {
        console.error('Error al guardar página:', err);
        alert('Error al guardar página. Verifique los datos o consulte la consola.');
      }
    });
  }

  cancelar() {
    this.router.navigate(['/dashboard/mod-pagina']);
  }
}
