/**
 * EstadoEnum.java
 */
package com.hbt.semillero.entidad;

/**
 * <b>Descripción:<b>Clase que determina la enumeracion para representar los
 * tipos de estados aceptados por un comic
 * 
 * @author ccastano
 * @version
 */
public enum EstadoEnum {

	ACTIVO("enum.estado.activo"), 
	INACTIVO("enum.estado.inactivo");
	/**
	 * Atributo que contiene la clave del mensaje para la internacionalizacion
	 */
	private String codigoMensaje;

	/**
	 * Constructor que recibe como parametro el codigo del mensaje
	 * 
	 * @param codigoMensaje, Clave del mensaje para para internacionalizacion
	 */
	EstadoEnum(String codigoMensaje) {
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
