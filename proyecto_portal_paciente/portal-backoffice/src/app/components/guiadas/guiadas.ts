import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';
import { Menu, PortalMenu } from '../../services/menu';

@Component({
  selector: 'app-guiadas',
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './guiadas.html',
  styleUrl: './guiadas.css',
})
export class Guiadas implements OnInit {
  niveles: { opciones: PortalMenu[]; seleccionado: number | null }[] = [];
  menuFinal: PortalMenu | null = null;

  constructor(private service: Menu) {}

  ngOnInit(): void {
    this.cargarNivel1();
  }

  cargarNivel1(): void {
    this.service.getByNivel(1).subscribe({
      next: (data) => {
        console.log('Nivel 1:', data);
        this.niveles = [{ opciones: data, seleccionado: null }];
      },
      error: (err) => console.error('Error nivel 1:', err),
    });
  }

  onSeleccionarNivel(index: number): void {
    const idSeleccionado = this.niveles[index].seleccionado;

    if (!idSeleccionado) return;

    this.menuFinal = this.niveles[index].opciones.find((m) => m.id === idSeleccionado) || null;
    this.niveles = this.niveles.slice(0, index + 1);

    this.service.getByPadre(idSeleccionado).subscribe({
      next: (hijos) => {
        if (hijos.length > 0) {
          this.niveles.push({ opciones: hijos, seleccionado: null });
        }
      },
      error: (err) => console.error('Error hijos:', err),
    });
  }
}
