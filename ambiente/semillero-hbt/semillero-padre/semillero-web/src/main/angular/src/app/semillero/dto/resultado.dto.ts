/**
 * @description Clase ResultadoDTO que contiene la informacion de si un proceso
 * se ha ehecutado exitosamente o se ha generado error.
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
export class ResultadoDTO {

    /**
	 * Indicador de resultado.
	 */
    public exitoso: boolean;

	/**
	 * Mensaje de ejecución.
	 */
    public mensajeEjecucion: string;

    
}