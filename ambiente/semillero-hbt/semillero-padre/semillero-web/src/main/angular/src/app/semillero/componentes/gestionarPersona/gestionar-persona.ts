import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PersonaDTO } from '../../dto/persona.dto';
import { GestionarPersonaService } from '../../services/GestionarPersona.service';

/**
 * @description Componenete gestionar Persona, el cual contiene la logica CRUD
 */
 @Component({
    selector: 'gestionar-persona',
    templateUrl: './gestionar-persona.html',
})

export class GestionarPersonaComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarPersonaForm: FormGroup;

    /**
     * Atributo que contendra la informacion de la persona
     */
    public persona: PersonaDTO;

    /**
     * Atributo que contendra la lista de personas creados
     */
    public listaPersonas: Array<PersonaDTO>;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
     * Atributo que indica si debe mostrarse el boton para crear un persona
     */
    public showCreateBtn: boolean;

    /**
     * Atributo que indica si debe mostrarse el boton para confirmar 
     * los cambios al editar una persona
     */
    public showUpdateBtn: boolean;


    /**
     * 
     * @description Este es el constructor del componente GestionarPersonaComponent
     * 
     * @param fb 
     * @param router 
     * @param toastr 
     * @param GestionarPersonaService 
     */
     constructor(private fb: FormBuilder, private router: Router, private toastr: ToastrService, private GestionarPersonaService: GestionarPersonaService) {
        this.gestionarPersonaForm = this.fb.group({
            id: [null],
            nombre: [null, Validators.required],
            fecha_nac: [null, Validators.required],
            tipo_doc: ["CC", Validators.required],
            numero_doc: [null, Validators.required]
        });
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
     */
    ngOnInit(): void {
        console.log("Ingreso al evento onInit");
        this.persona = new PersonaDTO();
        this.listaPersonas = new Array<PersonaDTO>();
        this.showCreateBtn = true;
        this.consultarPersonas();
    } 

    private consultarPersonas(){
        // parametro res es el response emitido del REST, debería contener la lista de personas
        this.GestionarPersonaService.consultarPersonas().subscribe(res => {
            // Se asigna la lista de personas a la variable globar del componente que almacena la lista
            this.listaPersonas = res;
        }, err => {
            this.toastr.warning("Se ha presentado un error al consumir el servicio Consultar Persona");
        });
    }


    /*******************************************************************************/
    /*                             INICIO CRUD DE PERSONAS                         */
    /*******************************************************************************/

    /**
     * @description Metodo que permite validar el formulario y crear una persona
     */
    public CreatePersona(): void {
        this.submitted = true;

        if (this.gestionarPersonaForm.invalid) {
            return;
        }
        
        this.persona = new PersonaDTO();
        this.persona.nombre = this.f.nombre.value;
        this.persona.tipo_doc = this.f.tipo_doc.value;
        this.persona.numero_doc = this.f.numero_doc.value;
        this.persona.fecha_nac = this.f.fecha_nac.value;

        this.GestionarPersonaService.crearPersona(this.persona).subscribe(res=>{
            this.toastr.success(res.mensajeEjecucion);
            this.consultarPersonas();
            this.limpiarFormulario();
        }, err =>{
            this.toastr.warning("Se ha presentado un error al consumir el servicio Crear Persona");
        });
    }

    /**
     * @description Metodo que permite visualizar todos los detalles de una persona en
     *  un nuevo componente
     * @param persona 
     */
    public ReadPersona(persona: PersonaDTO): void {
        this.router.navigate(['consultar-persona', persona]);
    }

    /**
     * @description Metodo que permite validar el formulario y actualizar los 
     * detalles de una persona
     */
    public UpdatePersona(): void {
        this.submitted = true;

        if (this.gestionarPersonaForm.invalid) { 
            return;
        };

        // Creación de un nuevo objeto Persona DTO para llenar con nuevos datos
        this.persona = new PersonaDTO();
        this.persona.nombre = this.f.nombre.value;
        this.persona.tipo_doc = this.f.tipo_doc.value;
        this.persona.numero_doc = this.f.numero_doc.value;
        this.persona.fecha_nac = this.f.fecha_nac.value;

        /**
        // Se ubica el nuevo comic en la posición del comic viejo
        this.listaPersonas.splice((this.f.id.value)-1, 1, this.comic);
        this.limpiarFormulario();

        // Mostramos el boton para registrar comic
        this.showCreateBtn = true;

        // Ocultamos el boton para actualizar comic
        this.showUpdateBtn = false;

        // Se muestra una notificación confirmando la actualización del comic
        this.toastr.info('¡Comic "'+ this.comic.nombre+'" actualizado exitosamente!');
        **/
    }


    /**
     * Metodo que consulta una persona en la lista y la elimina
     * 
     * @param index de la persona seleccionada en la lista 
     */
    public DeletePersona(index: number): void {
        /*
        // Eliminar elemento ubicado en la posición index de la lista
        let deleted = this.listaComics.splice(index, 1)[0];

        // Se muestra una notificación confirmando la eliminación del Comic
        this.toastr.warning('¡Comic "'+deleted.nombre+'" eliminado exitosamente!');
        */
    }

    /*******************************************************************************/
    /*                              FIN CRUD DE PERSONAS                           */
    /*******************************************************************************/

    /**
     * Metodo que permite consultar una persona de la tabla y sus detalles para 
     * llenar el formulario de edición
     *  
     * @param index en la lista de la persona seleccionada
     */
    public EditPersona(index: number): void {
        let persona = this.listaPersonas[index];
        this.f.id.setValue(persona.id);
        this.f.nombre.setValue(persona.nombre);
        this.f.tipo_doc.setValue(persona.tipo_doc);
        this.f.numero_doc.setValue(persona.numero_doc);
        this.f.fecha_nac.setValue(persona.fecha_nac);

        // Se oculta el boton para registrar nuevas personas
        this.showCreateBtn = false;

        // Se muestra el boton para actualizar personas
        this.showUpdateBtn = true;
    }



    /**
     * Metodo que formatea el formulario
     */
    private limpiarFormulario(): void {
        this.submitted = false;
        this.f.nombre.setValue(null);
        this.f.tipo_doc.setValue(null);
        this.f.num_doc.setValue(null);
        this.f.fecha_nac.setValue(null);
    }


    /**
     * @description Metodo que obtiene los controles y sus propiedades
     */
    get f() {
        return this.gestionarPersonaForm.controls;
    }
}