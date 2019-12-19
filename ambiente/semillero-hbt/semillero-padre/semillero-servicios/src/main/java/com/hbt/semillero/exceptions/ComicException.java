/**
 * 
 */
package com.hbt.semillero.exceptions;

/**
 * Clase para manejar las Excepciones con la entidad Comic
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
public class ComicException extends Exception{

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String mensaje;

	/**
	 * Constructor sobrecargado de la clase
	 * 
	 * @param codigo de error
	 * @param mensaje texto de error
	 * @param causa de la excepción
	 */	
	public ComicException(String code, String message, Throwable cause) {
		super(message, cause);
		this.codigo = code;		
		this.mensaje = message;		
	}

	public ComicException(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}


	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}