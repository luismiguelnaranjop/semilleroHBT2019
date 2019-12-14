/**
 * TematicaEnum.java
 */
package com.hbt.semillero.entidad;

/**
 * <b>Descripción:<b> Clase que determina la enumeracion para representar los
 * tipos de tematica aceptados por un comic
 * 
 * @author ccastano
 * @version
 */
public enum TematicaEnum {

	AVENTURAS("enum.tematica.aventuras"), 				// Iva del 5%
	BELICO("enum.tematica.belico"),						// Iva del 16%
	DEPORTIVO("enum.tematica.deportivo"), 				// Iva del 10%
	FANTASTICO("enum.tematica.fantastico"),  			// Iva del 5%
	CIENCIA_FICCION("enum.tematica.cienciaficcion"),  	// Iva del 16%
	HISTORICO("enum.tematica.historico"),  				// Iva del 5%
	HORROR("enum.tematica.horror");						// Iva del 165%
	
	/**
	 * Atributo que contiene la clave del mensaje para la internacionalizacion
	 */
	private String codigoMensaje;

	/**
	 * Constructor que recibe como parametro el codigo del mensaje
	 * 
	 * @param codigoMensaje, Clave del mensaje para para internacionalizacion
	 */
	TematicaEnum(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Metodo que retorna el valor del atributo
	 * 
	 * @return cadena con el codigo del mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * Metodo que establece el valor del atributo
	 *
	 * @param codigoMensaje
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
}
