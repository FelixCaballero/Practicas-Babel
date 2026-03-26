import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Pagina } from '../../models/pagina';
import { PortalService } from '../../services/portal.service';
import { QuillModule } from 'ngx-quill';

@Component({
  selector: 'app-alta-pagina',
  standalone: true,
  imports: [CommonModule, FormsModule, QuillModule],
  templateUrl: './alta-pagina.html',

})
export class AltaPaginaComponent implements OnInit {
  nuevaPagina: Pagina = this.initPagina();
  isEditPagina: boolean = false;

  quillModules = {
    toolbar: [
      ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
      ['blockquote', 'code-block'],
      [{ 'header': 1 }, { 'header': 2 }],               // custom button values
      [{ 'list': 'ordered'}, { 'list': 'bullet' }],
      [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
      [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
      [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
      [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
      [{ 'align': [] }],
      ['clean'],                                         // remove formatting button
      ['link', 'image', 'video']
    ]
  };

  constructor(private portalService: PortalService, private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      if (navigation.extras.state['pagina']) {
        this.nuevaPagina = { ...navigation.extras.state['pagina'] };
        this.isEditPagina = navigation.extras.state['isEdit'] ?? true;
      }
      if (navigation.extras.state['lang']) {
        const lang = navigation.extras.state['lang'];
        const mapLang: { [key: string]: string } = { 'EN': '1', 'CA': '2', 'VA': '3', 'GL': '4' };
        this.nuevaPagina.idLang = mapLang[lang] || '';
        this.isEditPagina = false;
      }
    }
  }

  ngOnInit() {}

  initPagina(): Pagina {
    return { idPagina: '', descripcion: '', pagina: '', migasPan: '', titulo: '', idLang: '' };
  }

  guardarPagina() {
    const ob$ = this.isEditPagina 
      ? this.portalService.updatePagina(this.nuevaPagina.idPagina, this.nuevaPagina) 
      : this.portalService.savePagina(this.nuevaPagina);

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
