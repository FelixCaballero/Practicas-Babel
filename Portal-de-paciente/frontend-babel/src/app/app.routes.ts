import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './components/home/home';
import { AltaMenuComponent } from './components/alta-menu/alta-menu';
import { ModMenuComponent } from './components/mod-menu/mod-menu';
import { AltaPaginaComponent } from './components/alta-pagina/alta-pagina';
import { ModPaginaComponent } from './components/mod-pagina/mod-pagina';
import { ConsultaArbolComponent } from './components/consulta-arbol/consulta-arbol';
import { ConsultaMenuComponent } from './components/consulta-menu/consulta-menu';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'alta-menu', component: AltaMenuComponent },
      { path: 'mod-menu', component: ModMenuComponent }, // Keeps current mod search
      { path: 'consulta-menu', component: ConsultaMenuComponent }, // New Sequential Selectors
      { path: 'alta-pagina', component: AltaPaginaComponent },
      { path: 'mod-pagina', component: ModPaginaComponent },
      { path: 'consulta-arbol', component: ConsultaArbolComponent },
    ]
  }
];
