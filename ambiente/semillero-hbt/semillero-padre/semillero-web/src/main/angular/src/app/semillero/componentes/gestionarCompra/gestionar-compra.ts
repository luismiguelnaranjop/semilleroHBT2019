import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { GestionarComprasService } from '../../services/GestionarCompra.service';
import { ComicDTO } from '../../dto/comic.dto';
import { PersonaDTO } from '../../dto/persona.dto';

/**
 * @description Componenete gestionar compra, el cual contiene la logica para 
 * compra un comic
 */
 @Component({
    selector: 'gestionar-compra',
    templateUrl: './gestionar-compra.html',
})

export class GestionarCompraComponent implements OnInit {

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics: Array<ComicDTO>;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaPersonas: Array<PersonaDTO>;

    /**
     * Constructor de la clase GestionarCompraComponent
     * @param fb 
     * @param router 
     * @param toastr 
     * @param GestionarComprasService 
     */
    constructor(private fb: FormBuilder, 
        private toastr: ToastrService, 
        private GestionarComprasService: GestionarComprasService) {                
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
     */
    ngOnInit(): void {
        this.consultarComics();
        this.consultarPersonas();
    } 

    private consultarComics(){
        // parametro res es el response emitido del REST, debería contener la lista de comics
        this.GestionarComprasService.consultarComics().subscribe(res => {
            // Se asigna la lista de comics a la variable global del componente que almacena la lista
            this.listaComics = res;
            console.log(this.listaComics);            
        }, err => {
            this.toastr.warning("Se ha presentado un error al consumir el servicio Consultar Persona");
        });
    }

    private consultarPersonas(){
        // parametro res es el response emitido del REST, debería contener la lista de comics
        this.GestionarComprasService.consultarComics().subscribe(res => {
            // Se asigna la lista de comics a la variable global del componente que almacena la lista
            this.listaPersonas = res;
            console.log(this.listaPersonas);            
        }, err => {
            this.toastr.warning("Se ha presentado un error al consumir el servicio Consultar Persona");
        });
    }
}