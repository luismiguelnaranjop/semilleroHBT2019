/**
 * @description Clase PersonaDTO que contiene la informacion de una Persona
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
export class PersonaDTO {
   
    /**
    * Indicador de resultado.
    * Identificador unico en la tabla
    */
    public id: string;

    /**
    * Indicador de resultado.
    * Nombre de la persona
    */
    public nombre: string;

    /**
    * Indicador de resultado.
    * Tipo de documento de la persona
    */
    public tipo_doc: string;

    /**
    * Indicador de resultado.
    * Numero de documento de la persona
    */
    public numero_doc: string;

    /**
    * Indicador de resultado.
    * Fecha de nacimiento de la persona
    */
    public fecha_nac: Date;

  
}