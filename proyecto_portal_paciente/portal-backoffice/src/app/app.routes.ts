import { Routes } from '@angular/router';
import { Login } from './components/auth/login/login';
import { Layout } from './components/layout/layout';
import { MenuAlta } from './components/menu-alta/menu-alta';
import { MenuForm } from './components/menu-form/menu-form';
import { PaginaForm } from './components/pagina-form/pagina-form';
import { ConsultaPagina } from './components/consulta-pagina/consulta-pagina';
import { ConsultaMenu } from './components/consulta-menu/consulta-menu';
import { PaginaAlta } from './components/pagina-alta/pagina-alta';
import { Guiadas } from './components/guiadas/guiadas';
import { ArbolMenus } from './components/arbol-menus/arbol-menus';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },

  {
    path: 'layout',
    component: Layout,
    children: [
      { path: '', redirectTo: 'consultar-menu', pathMatch: 'full' },
      //MENU
      { path: 'alta-menu', component: MenuAlta },
      { path: 'mod-menu/:id', component: MenuForm },
      //PAGINA
      { path: 'alta-pagina', component: PaginaAlta },
      { path: 'mod-pagina/:id', component: PaginaForm },
      //CONSULTAS
      { path: 'consultar-pagina', component: ConsultaPagina },
      { path: 'consultar-menu', component: ConsultaMenu },
      //GUIADA
      {path: 'guiadas', component: Guiadas},
      {path:'arbol-menu',component : ArbolMenus}
    ],
  },
  //Cualquier URL que no coincida con rutas anteriores redirige a /login
  { path: '**', redirectTo: 'login' },
];
