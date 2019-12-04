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
     * @description Variable que almacenará nombre completo y ciudad de origen.
     */
    public nombreCiudad: string;

    constructor() {
    }

    ngOnInit(): void {
        this.nombreCiudad = "Luis Miguel Naranjo Pastrana - Cienaga de Oro, Córdoba"
    }
}

