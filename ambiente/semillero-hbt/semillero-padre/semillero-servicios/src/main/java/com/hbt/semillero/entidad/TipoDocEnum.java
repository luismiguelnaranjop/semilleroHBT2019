/**
 * EstadoEnum.java
 */
package com.hbt.semillero.entidad;

/**
 * Clase que determina la enumeracion para representar los tipos de 
 * documento que puede tener una persona
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
public enum TipoDocEnum {

	AS("enum.tipoDoc.Adulto_sin_identidad"),
	CC("enum.tipoDoc.Cedula_de_ciudadanía"),
	CE("enum.tipoDoc.Cedula_de_extranjería"),
	MS("enum.tipoDoc.Menor_sin_identificación"),
	PA("enum.tipoDoc.Pasaporte"),
	RC("enum.tipoDoc.Registro_Civil"),
	TI("enum.tipoDoc.Tarjeta_de_identidad");

	/**
	 * Atributo que contiene la clave del mensaje para la internacionalizacion
	 */
	private String codigoMensaje;

	/**
	 * Constructor que recibe como parametro el codigo del mensaje
	 * 
	 * @param codigoMensaje, Clave del mensaje para para internacionalizacion
	 */
	TipoDocEnum(String codigoMensaje) {
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
