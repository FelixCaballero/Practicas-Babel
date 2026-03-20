import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, RouterLink, NgIf],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout {
  //clave-valor, se guarda en memoria
  user = localStorage.getItem('user');
}
