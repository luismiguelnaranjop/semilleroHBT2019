import { Component, OnInit } from '@angular/core';

/**
 * @description Componente que imprime en pantalla nombre y ciudad de origen.
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
@Component({
    selector: 'imprimir-nombre',
    templateUrl: './imprimir-nombre-component.html',
})

export class NombreComponent implements OnInit {

    /**
     * @description Variable que almacenará nombre completo
     */
    public nombre: string;

    /**
     * @description Variable que almacenará ciudad de origen.
     */
    public ciudad: string;

    /**
     * @description Constructor que nos permite inyectar e inicializar las dependencias
     */
    constructor() {
    }

    /**
     * @description Evento angular que se inicia para inicializar nuestras variables 
     */
    ngOnInit(): void {
        this.nombre = "Luis Miguel Naranjo Pastrana"
        this.ciudad = "Cienaga de Oro, Córdoba"
    }
}