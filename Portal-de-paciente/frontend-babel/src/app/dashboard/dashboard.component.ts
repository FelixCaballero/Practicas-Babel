import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

/**
 * Componente principal del panel de control (Back Office).
 * Layout que contiene la cabecera, barra lateral y el outlet para las vistas.
 */
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule,
    RouterModule
  ],
  templateUrl: './dashboard.component.html',

})
export class DashboardComponent {

  constructor(private router: Router) {}

  /** Borra los credenciales locales (si los hubiera) y devuelve al usuario a la pantalla Login. */
  logout() {
    this.router.navigate(['/login']);
  }
}
