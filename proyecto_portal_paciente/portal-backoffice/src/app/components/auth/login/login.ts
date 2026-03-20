import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Auth } from '../../../services/auth';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, NgIf, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  protected usuario: string = '';
  protected password: string = '';
  protected error: string = '';

  constructor(
    private auth: Auth,
    private router: Router,
  ) {}

  onSubmit() {
    this.error = '';
    this.auth.login(this.usuario, this.password).subscribe({
      //.subscribe() sirve para escuchar o consumir un Observable
      next: (ok) => {
        if (ok) {
          this.router.navigateByUrl('/layout');
        } else {
          this.error = 'Usuario o contrasña incorrecta';
        }
      },
      error: () => (this.error = 'Error de comunicación con el servidor'),
    });
  }
}
