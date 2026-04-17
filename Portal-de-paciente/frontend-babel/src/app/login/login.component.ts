import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

/**
 * Componente que gestiona la vista del panel de Inicio de Sesión.
 * Recopila y procesa las credenciales introducidas por el gestor antes de enviarlas al servicio.
 */
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',

})
export class LoginComponent {
  usuario = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  /**
   * Método disparado al hacer submit en el formulario de login.
   * Solicita la validación al AuthService y navega hacia el Dashboard si el acceso es correcto.
   * En caso contrario muestra un mensaje de error visual para el usuario.
   */
  onLogin() {
    this.authService.login({ usuario: this.usuario, password: this.password }).subscribe({
      next: (isValid) => {
        console.log('¿vlido?:', isValid);
        if (isValid) {
          this.router.navigate(['/dashboard']);
        } else {
          this.errorMessage = 'DNI o contraseña incorrectos';
        }
      },
      error: (err) => {
        console.error('Error de conexión:', err);
        this.errorMessage = 'Error de conexión con el servidor. ¿Está el backend encendido?';
      }
    });
  }
}
