import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ComicDTO } from '../../dto/comic.dto';

/**
 * @description Componenete consultar Comic, muestra la información de un
 * comic registrado
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
@Component({
    selector: 'consultar-comic',
    templateUrl: './consultar-comic.html',
})

export class ConsultarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public consultarComicForm : FormGroup;

    /**
     * 
     * @description Este es el constructor del componente ConsultarComicComponent
     * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
     * 
     * @param fb 
     * @param activatedRoute 
     * @param router 
     */
    constructor(private fb : FormBuilder, private activatedRoute: ActivatedRoute, private router : Router) {

        this.consultarComicForm = this.fb.group({
            nombre : [null],
            editorial : [null],
            tematica : [null],
            coleccion : [null],
            numeroPaginas : [null],
            precio : [null],
            autores : [null],
            color : [null]
        });
               
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
     */
    ngOnInit(): void {

        // Asignación de parametros a los campos del formulario

        this.f.nombre.setValue(this.p.nombre);
        this.f.editorial.setValue(this.p.editorial);
        this.f.tematica.setValue(this.p.tematica);
        this.f.coleccion.setValue(this.p.coleccion);
        this.f.numeroPaginas.setValue(this.p.numeroPaginas);
        this.f.precio.setValue(this.p.precio);
        this.f.autores.setValue(this.p.autores);
        this.f.color.setValue(this.p.color);
        this.f.nombre.disable();
        this.f.editorial.disable();
        this.f.tematica.disable();
        this.f.coleccion.disable();
        this.f.numeroPaginas.disable();
        this.f.precio.disable();
        this.f.autores.disable();
        this.f.color.disable();
    }

    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() { 
        return this.consultarComicForm.controls;
    }

    /**
     * @description Metodo que obtiene los datos recibidos por paramtro
     * @author luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmaol.com>
     */
    get p(){
        return this.activatedRoute.snapshot.params;
    }
}