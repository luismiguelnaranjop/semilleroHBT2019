import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { ConsultarComicComponent } from './semillero/componentes/consultarComic/consultar-comic';
import { GestionarPersonaComponent } from './semillero/componentes/gestionarPersona/gestionar-persona';
import { GestionarCompraComponent } from './semillero/componentes/gestionarCompra/gestionar-compra';

const routes: Routes = [
  { path: 'gestionar-compras', component: GestionarCompraComponent },
  { path: 'gestionar-persona', component: GestionarPersonaComponent },
  { path: 'gestionar-comic', component: GestionarComicComponent },
  { path: 'consultar-comic', component: ConsultarComicComponent },
  { path: 'bienvenida', component: BienvenidaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
