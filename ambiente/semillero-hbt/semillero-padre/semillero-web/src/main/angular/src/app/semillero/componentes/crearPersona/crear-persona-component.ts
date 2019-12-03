import { Component, OnInit } from '@angular/core';
import { ComicDTO } from '../../dto/comic.dto';

/**
 * @description La clase CrearPersonaComponent permite crear personas
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    selector: 'crear-persona',
    templateUrl: './crear-persona-component.html'
})
export class CrearPersonaComponent implements OnInit{
    
    private nombreInstructorGlobalPrivado : string;
    public nombreInstructorGlobalPublic : string;

    public listaApellidos : Array<any> = new Array<any>();
    public listaNombres = null;

    public comicDTO : ComicDTO;

    ngOnInit() : void {
        this.nombreInstructorGlobalPrivado = "Semillero2019";
        this.inicializarComponente();


        let nombreInstructor = "Diego Alvarez";
        let nombreInstructorString : string = "Diego Alvarez string";
        console.log("nombreInstructor: " + nombreInstructor);
        console.log("nombreInstructorString: " + nombreInstructorString);


        let miVarible : any = {
            id : 1,
            nombre : "Carlos",
            direccion : "Carrera 21 XXX",
            colores : [1,2,3,4,5]
        };
        miVarible.genero = "Masculino";
        delete miVarible.genero;

        this.listaApellidos.push(miVarible);
        

        
        //alert("Longitud de la lista:" + this.listaNombres.length);

        let miVariable : number = 100.23;
        let variableString : string  = 'semillero2019';

        let miVariableBoolean : boolean = true;

        let miMapa : Map<string,string>;
        miMapa = new Map<string,string>();
        
        miMapa.set("1", "semillero");
        miMapa.get("1");
        let mifecha = new Date();
        
        console.log(mifecha);

        let lista = this.listaApellidos;
        for (let i = 0; i < lista.length; i++) {
            const element = lista[i];
            console.log(element);
            
        }

        lista.forEach(element => {
            console.log(element);
        });

        lista.map(objeto => {
            console.log(objeto);
        });

        

    }

    public inicializarComponente() : Array<string> {
        let retorno : any;
        let objeto = undefined;
        if(objeto !== null && objeto !== undefined ){
            console.log("No es nulo");
        } else {
            console.log("Si es nulo");
        }

        console.log(1 == 1);
//        console.log("1" == 1);
        console.log(1 === 1);
  //      console.log("1" === 1);

        this.comicDTO = new ComicDTO();
        this.comicDTO.autores = "Pablito";
        this.comicDTO.fechaVenta = new Date();
        console.log(this.comicDTO.autores);
        
        
        return retorno;
    }
}