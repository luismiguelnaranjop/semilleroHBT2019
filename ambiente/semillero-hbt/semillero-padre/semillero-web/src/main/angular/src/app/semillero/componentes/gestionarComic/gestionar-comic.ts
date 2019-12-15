import { ComicDTO } from '../../dto/comic.dto';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

/**
 * @description Componenete gestionar comic, el cual contiene la logica CRUD
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    selector: 'gestionar-comic',
    templateUrl: './gestionar-comic.html',
    styleUrls: ['./gestionar-comic.css']
})

export class GestionarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarComicForm: FormGroup;

    /**
     * Atributo que contendra la informacion del comic
     */
    public comic: ComicDTO;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics: Array<ComicDTO>;

    /**
     * Atributo que indica el ID incrementable al crear un
     * nuevo Comic
     */
    public idComic: number = 0;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
     * Atributo que indica si debe mostrarse el boton para crear un comic
     */
    public showCreateBtn: boolean;

    /**
     * Atributo que indica si debe mostrarse el boton para confirmar 
     * los cambios al editar un comic
     */
    public showUpdateBtn: boolean;


    /**
     * 
     * @description Este es el constructor del componente GestionarComicComponent
     * 
     * @param fb 
     * @param router 
     * @param toastr 
     */
     constructor(private fb: FormBuilder, private router: Router, private toastr: ToastrService) {
        this.gestionarComicForm = this.fb.group({
            id: [null],
            nombre: [null, Validators.required],
            editorial: [null],
            tematica: [null],
            coleccion: [null],
            numeroPaginas: [null],
            precio: [null],
            autores: [null],
            color: [null]
        });
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
     */
    ngOnInit(): void {
        console.log("Ingreso al evento onInit");
        this.comic = new ComicDTO();
        this.listaComics = new Array<ComicDTO>();
        this.showCreateBtn = true;
    }


    /*******************************************************************************/
    /*                              INICIO CRUD DE COMICS                          */
    /*******************************************************************************/

    /**
     * @description Metodo que permite validar el formulario y crear un comic
     */
    public CreateComic(): void {
        this.submitted = true;

        if (this.gestionarComicForm.invalid) {
            return;
        }
        
        this.idComic++;
        this.comic = new ComicDTO();
        this.comic.id = this.idComic + "";
        this.comic.nombre = this.f.nombre.value;
        this.comic.editorial = this.f.editorial.value;
        this.comic.tematica = this.f.tematica.value;
        this.comic.coleccion = this.f.coleccion.value;
        this.comic.numeroPaginas = this.f.numeroPaginas.value;
        this.comic.precio = this.f.precio.value;
        this.comic.autores = this.f.autores.value;
        this.comic.color = this.f.color.value;
        this.listaComics.push(this.comic);

        this.limpiarFormulario();

        // Se muestra una notificación confirmando el registro del nuevo comic
        this.toastr.success('¡Se registró exitosamente el comic "'+ this.comic.nombre+'"!');
    }


    /**
     * @description Metodo que permite visualizar todos los detalles de un comic en
     *  un nuevo componente
     * 
     * @param comic 
     */
    public ReadComic(comic: ComicDTO): void {
        this.router.navigate(['consultar-comic', comic]);
    }


    /**
     * @description Metodo que permite validar el formulario y actualizar los 
     * detalles de un comic
     */
    public UpdateComic(): void {
        this.submitted = true;

        if (this.gestionarComicForm.invalid) { 
            return;
        };

        // Creación de un nuevo Comic para llenar con nuevos datos
        this.comic = new ComicDTO();
        
        this.comic.id = this.f.id.value;
        this.comic.nombre = this.f.nombre.value;
        this.comic.editorial = this.f.editorial.value;
        this.comic.tematica = this.f.tematica.value;
        this.comic.coleccion = this.f.coleccion.value;
        this.comic.numeroPaginas = this.f.numeroPaginas.value;
        this.comic.precio = this.f.precio.value;
        this.comic.autores = this.f.autores.value;
        this.comic.color = this.f.color.value;

        // Se ubica el nuevo comic en la posición del comic viejo
        this.listaComics.splice((this.f.id.value)-1, 1, this.comic);
        this.limpiarFormulario();

        // Mostramos el boton para registrar comic
        this.showCreateBtn = true;

        // Ocultamos el boton para actualizar comic
        this.showUpdateBtn = false;

        // Se muestra una notificación confirmando la actualización del comic
        this.toastr.info('¡Comic "'+ this.comic.nombre+'" actualizado exitosamente!');
    }


    /**
     * Metodo que consulta un comic en la lista y lo elimina
     * 
     * @param index del comic seleccionado en la lista 
     */
    public DeleteComic(index: number): void {
        // Eliminar elemento ubicado en la posición index de la lista
        let deleted = this.listaComics.splice(index, 1)[0];

        // Se muestra una notificación confirmando la eliminación del Comic
        this.toastr.warning('¡Comic "'+deleted.nombre+'" eliminado exitosamente!');
    }

    /*******************************************************************************/
    /*                              FIN CRUD DE COMICS                             */
    /*******************************************************************************/

    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles para 
     * llenar el formulario de edición
     *  
     * @param index en la lista del comic seleccionado
     */
    public EditComic(index: number): void {
        let comic = this.listaComics[index];
        this.f.id.setValue(comic.id);
        this.f.nombre.setValue(comic.nombre);
        this.f.editorial.setValue(comic.editorial);
        this.f.tematica.setValue(comic.tematica);
        this.f.coleccion.setValue(comic.coleccion);
        this.f.numeroPaginas.setValue(comic.numeroPaginas);
        this.f.precio.setValue(comic.precio);
        this.f.autores.setValue(comic.autores);
        this.f.color.setValue(comic.color);

        // Se oculta el boton para registrar nuevos comics
        this.showCreateBtn = false;

        // Se muestra el boton para actualizar comics
        this.showUpdateBtn = true;
    }


    /**
     * Metodo que formatea el formulario
     */
    private limpiarFormulario(): void {
        this.submitted = false;
        this.f.id.setValue(null);
        this.f.nombre.setValue(null);
        this.f.editorial.setValue(null);
        this.f.tematica.setValue(null);
        this.f.coleccion.setValue(null);
        this.f.numeroPaginas.setValue(null);
        this.f.precio.setValue(null);
        this.f.autores.setValue(null);
        this.f.color.setValue(null);
    }


    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() {
        return this.gestionarComicForm.controls;
    }
}