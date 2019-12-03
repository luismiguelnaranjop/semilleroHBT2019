import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {EjemploService} from '../../services/ejemplo.service';
import { ComicDTO } from '../../dto/comic.dto';

/**
 * @description Componente bienvenida, el cual contiene la imagen de bienvenida al semillero
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
  selector: 'bienvenida',
  templateUrl: './bienvenida-component.html',
})
export class BienvenidaComponent implements OnInit {
  
  public urlImagen : string;
  public comicDTO : ComicDTO;
  
  constructor(private router : Router, private activatedRoute: ActivatedRoute, private ejemploService: EjemploService) {
    console.log("entro al constructor del componente bienvenida");
  }

  ngOnInit(): void {
    this.urlImagen = "https://www.elempleo.com/sitios-empresariales/colombia/heinsohn-business-technology/img/elempleo-02.jpg";
    let data = this.activatedRoute.snapshot.params;
    
    console.log("Parametros recibidos " + data);

    this.ejemploService.consultarComics().subscribe(respuesta => {
      console.log(respuesta);
    });


    this.comicDTO = new ComicDTO();    
    this.comicDTO.nombre = "BATAMAN";
    this.comicDTO.editorial = "perro";
    this.comicDTO.tematica = "no se";
    this.comicDTO.coleccion = "norma";
    this.comicDTO.numeroPaginas = 1;
    this.comicDTO.estado = "INACTIVO";
    this.comicDTO.precio = 855;
    this.comicDTO.cantidad = 1;
    this.comicDTO.tematica = "AVENTURAS"
    this.comicDTO.autores = "cindyDiego";
    this.comicDTO.color = true;

    
    this.ejemploService.crearComic(this.comicDTO).subscribe(respuesta => {
      console.log(respuesta);
    });

   
  }

  public ejecucionEventoClick( parametroEvento : any, numero : number ) : void {
    alert("Hola: " + parametroEvento + ' ' + numero);
    
  }

}