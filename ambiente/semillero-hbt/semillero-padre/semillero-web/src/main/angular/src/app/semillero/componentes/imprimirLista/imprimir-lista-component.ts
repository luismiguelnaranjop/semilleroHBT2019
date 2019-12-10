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
     * @description Variable privada que almacenará la lista de objetos
     *  
     * No es necesario que esta lista sea publica, puesto que en la vista
     * se mostrará una variable con el resultados del JSON.stringify();
     */
    private lista: Array<any>;


    /**
     * @description  Variable publica que será mostrada en el HTML, esta
     * contendrá un JSON los objetos creados
     */
    public listaJSON: string;


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

        //Instanciacion de la lista de objetos
        this.lista = new Array<any>();

        //Instanciacion de la variable booleana
        this.mostarMensaje = false;

        //Instanciacion de la variable booleana
        this.mensajeError = false;

        // Variable que controla el ciclo For
        let i: number;

        // Ciclo que llena automaticamente una lista con 5 objetos
        for (i = 1; i <= 5; i++) {
            this.lista.push(
                this.crearObjeto(
                    i,
                    "Objeto " + i,
                    "Editorial " + i,
                    "Tematica " + i,
                    i * 10,
                    i * 5000 + 0.00,
                    "Autor " + i,
                    true,
                    new Date("2019-12-0" + i),
                    'Disponible'
                )
            )
        }

        // Se convierte la lista de objetos en una cadena de texto JSON con un espacio en 
        // blanco entre cada objeto en el String de salida JSON para su mejor legibilidad
        this.listaJSON = JSON.stringify(this.lista, null, 1);
    }

    /**
     * Metodo para crear objetos, recibe atributos del objeto por paramtro
     * 
     * @param id numero entero
     * @param nombre alfanumerico
     * @param editorial alfanumerico
     * @param tematica alfanumerico
     * @param numeroPaginas numero entero 
     * @param precio decimal
     * @param autores alfanumerico
     * @param aColor operador de desicion
     * @param fechaVenta decha
     * @param estado alfanumerico
     * 
     * @returns Objeto
     */
    private crearObjeto(id: number, nombre: string, editorial: string, tematica: string, numeroPaginas: number, precio: number, autores: string, aColor: boolean, fechaVenta: Date, estado: string): any {
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
     * Metodo que elimina un objeto de la lista 
     * 
     * @param pos posicion a eliminar dentro de la lista
     */
    public eliminarObjeto(pos:number) :void{
        if (this.mostarMensaje === false) {

            // Aquí es donde se elimina. El elemento eliminado se guarda en la variable "eliminado"
            let eliminado = this.lista.splice(pos-1, 1);

            // Establesemos el mensaje de error
            this.mensaje = "Se ha eliminado el objeto: "+ eliminado[0].nombre;
           
            // Se confirma que se debe mostrar un mensaje de estado
            this.mostarMensaje = true;
        } else {

            // Se confirma que se debe mostrar un mensaje de estado error
            this.mensajeError = true;
        }
    }
}