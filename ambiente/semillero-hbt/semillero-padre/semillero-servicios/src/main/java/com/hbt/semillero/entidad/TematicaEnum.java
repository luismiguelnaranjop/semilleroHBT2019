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

	AVENTURAS("enum.tematica.aventuras"), 
	BELICO("enum.tematica.belico"),
	DEPORTIVO("enum.tematica.deportivo"), 
	FANTASTICO("enum.tematica.fantastico"),  
	CIENCIA_FICCION("enum.tematica.cienciaficcion"),  
	HISTORICO("enum.tematica.historico"),  
	HORROR("enum.tematica.horror");
	
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
