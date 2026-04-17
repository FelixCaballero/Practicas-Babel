import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Pagina } from '../../models/pagina';
import { PortalService } from '../../services/portal.service';

@Component({
  selector: 'app-mod-pagina',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './mod-pagina.html',

})
export class ModPaginaComponent implements OnInit {
  listaPaginas: Pagina[] = [];
  idiomaSeleccionado: string = '1'; // Inglés por defecto

  filtroDescripcion: string = '';
  paginaSeleccionada: Pagina | null = null;

  constructor(private portalService: PortalService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cargarPaginas();
  }

  cargarPaginas() {
    this.portalService.getPaginas().subscribe({
      next: (res) => {
        this.listaPaginas = res;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar páginas:', err)
    });
  }

  get listaPaginasFiltrada(): Pagina[] {
    const paginas = this.listaPaginas.filter(p => String(p.idLang) === String(this.idiomaSeleccionado));
    if (!this.filtroDescripcion) return paginas;
    const filtro = this.filtroDescripcion.toLowerCase();
    return paginas.filter(p => p.descripcion && p.descripcion.toLowerCase().includes(filtro));
  }

  editarPagina(p: Pagina) {
    this.router.navigate(['/dashboard/alta-pagina'], { state: { pagina: p, isEdit: true } });
  }

  borrarPagina(id: string) {
    if (confirm('¿Confirmas el borrado de la página en la Base de Datos?')) {
      this.portalService.deletePagina(id).subscribe({
        next: () => {
          alert('Página borrada con éxito');
          this.cargarPaginas();
          this.paginaSeleccionada = null;
        },
        error: (err) => {
          console.error('Error al borrar página:', err);
          alert('No se pudo borrar la página. Es posible que existan restricciones en base de datos.');
        }
      });
    }
  }

  crearTraduccionPagina(p: Pagina, lang: string) {
    this.router.navigate(['/dashboard/alta-pagina'], { state: { pagina: p, lang: lang } });
  }
}
