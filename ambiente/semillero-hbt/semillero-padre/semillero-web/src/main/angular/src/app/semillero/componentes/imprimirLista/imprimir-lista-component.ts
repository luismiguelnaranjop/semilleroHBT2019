import { Component, OnInit } from '@angular/core';

/**
 * @description Componente que imprime en pantalla una lista de objetos creados.
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
@Component({
    selector: 'imprimir-lista',
    templateUrl: './imprimir-lista-component.html',
})

export class ImprimirListaComponent implements OnInit {

    /**
     * @description Variable que almacenará la lista de objetos
     *  
     */
    public lista: Array<any>;

    /**
     * @description Si esta variable es TRUE, deberá mostrarse un mensaje
     * diciendo que se elimino el objeto 3 de la lista 
     */
    public mostarMensaje: boolean;

    /**
     * @description Si esta variable es true, debera mostrarse un error  
     * diciendo que el objeto que se quizo eliminar ya no existe
     */
    public mensajeError: boolean;

    /**
     * @description Variable que almacena el mensaje que se muestra al 
     * eliminar el objeto de la posicion 3
     * 
     */
    public mensaje: string;


    /**
     * @description Constructor que nos permite inyectar e inicializar las dependencias
     */
    constructor() {
    }

    /**
     * @description Evento angular que se inicia para inicializar nuestras variables 
     */
    ngOnInit(): void {

        //Inicializar la lista de objetos
        this.lista = new Array<any>();

        //Inicializar la variable que controla si se muestra o no un mensaje
        this.mostarMensaje = false;

        //Inicializar la variable que controla si se muestra o no un error
        this.mensajeError = false;

        // Se guarda el objeto 1 en una variable temporal
        let obj1 : string; 
        obj1 = this.crearObjetoComic(1, "The Superior Spider-Man", "Panini Comics", "Acción", 144, 15.20, "Dan Slott, Giuseppe Camuncoli, Humberto Ramos", true, new Date("2019-12-12"), "Activo");

        // Se guarda el objeto 2 en una variable temporal
        let obj2 : string; 
        obj2 = this.crearObjetoComic(2, "Venom: Space Knight", "Panini Comics", "Acción", 160, 13.78, "Ariel Olivetti, Robbie Thompson", true, new Date("2017-03-03"), "Activo");

        // Se guarda el objeto 3 en una variable temporal
        let obj3 : string; 
        obj3 = this.crearObjetoComic(3, "Daredevil v5", "Panini Comics", "Acción", 136, 11.88, "Charles Soule, Ron Garney", true, new Date("2019-04-10"), "Activo");

        // Se guarda el objeto 4 en una variable temporal
        let obj4 : string; 
        obj4 = this.crearObjetoComic(4, "Captain America Corps", "Panini Comics", "Acción", 128, 12.83, "Phillippe Briones, Roger Stern", true, new Date("2019-03-32"), "Activo");

        // Se guarda el objeto 5 en una variable temporal
        let obj5 : string; 
        obj5 = this.crearObjetoComic(5, "Conan The Barbarian", "Panini Comics", "Acción", 856, 47.45, "John Buscema, Neal Adams, Rich Buckler, Roy Thomas", true, new Date("2019-12-12"), "Activo");

        // Se agregan a la lista los objetos creados pero ya convertidos en una cadena de texto JSON 
        this.lista.push(JSON.stringify(obj1));
        this.lista.push(JSON.stringify(obj2));
        this.lista.push(JSON.stringify(obj3));
        this.lista.push(JSON.stringify(obj4));
        this.lista.push(JSON.stringify(obj5));
    }

    /**
     * Metodo para crear un Comic, los atributos para crear el objeto se reciben por parametro
     * 
     * @param id numero entero
     * @param nombre alfanumerico
     * @param editorial alfanumerico
     * @param tematica alfanumerico
     * @param numeroPaginas numero entero 
     * @param precio decimal - valor en dolares
     * @param autores alfanumerico
     * @param aColor operador de desicion
     * @param fechaVenta decha
     * @param estado alfanumerico
     * 
     * @returns Objeto
     */
    private crearObjetoComic(id: number, nombre: string, editorial: string, tematica: string, numeroPaginas: number, precio: number, autores: string, aColor: boolean, fechaVenta: Date, estado: string): any {
        return {
            id: id,
            nombre: nombre,
            editorial: editorial,
            tematica: tematica,
            numeroPaginas: numeroPaginas,
            precio: precio,
            autores: autores,
            aColor: aColor,
            fechaVenta: fechaVenta,
            estado: estado
        };
    };

    /**
     * Metodo que elimina un objeto Comic JSON de la lista 
     * 
     * @param pos posicion a eliminar dentro de la lista
     */
    public eliminarObjetoComic(pos: number): void {
        if (this.mostarMensaje === false) {

            // Se elimina el objeto y este objeto eliminado se guarda en la variable "eliminadoJSON" cono string JSON
            let eliminadoJSON = this.lista.splice(pos - 1, 1)[0];

            // Se convierte el JSON string a un objeto, esto para poder acceder al nombre del comic eliminado
            let eliminadoObject = JSON.parse(eliminadoJSON); 
            
            // Establesemos el mensaje de error
            this.mensaje = "Se ha eliminado el comic: "+eliminadoObject.nombre;

            // Se confirma que se debe mostrar un mensaje de estado
            this.mostarMensaje = true;
        } else {
            // Se confirma que se debe mostrar un mensaje de estado error
            this.mensajeError = true;
        }
    }
}