import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule} from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { APP_BASE_HREF } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './semillero/componentes/menu/menu-component';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { CrearPersonaComponent } from './semillero/componentes/crearPersona/crear-persona-component';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';

// Modulos para el Toastr (Notificaciones)
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

// Componentes creados durante el desarrollo del Semillero
import { NombreComponent } from './semillero/componentes/imprimirNombre/imprimir-nombre-component';
import { ImprimirListaComponent } from './semillero/componentes/imprimirLista/imprimir-lista-component';
import { ConsultarComicComponent } from './semillero/componentes/consultarComic/consultar-comic';
import { GestionarPersonaComponent } from './semillero/componentes/gestionarPersona/gestionar-persona';
import { GestionarCompraComponent } from './semillero/componentes/gestionarCompra/gestionar-compra';

// DTOs
export { ComicDTO } from './semillero/dto/comic.dto';
export { ResultadoDTO } from './semillero/dto/resultado.dto';

//Manejo de servicios
import {AbstractService} from './semillero/services/template.service';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    BienvenidaComponent,
    CrearPersonaComponent,
    GestionarComicComponent,
    NombreComponent,
    ImprimirListaComponent,
    ConsultarComicComponent,
    GestionarPersonaComponent,
    GestionarCompraComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot() // ToastrModule added
  ],
  providers: [
  	{ provide: APP_BASE_HREF, useValue: '/SemilleroHBT' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}
