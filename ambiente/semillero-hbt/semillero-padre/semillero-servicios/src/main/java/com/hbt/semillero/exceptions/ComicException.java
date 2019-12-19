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

	/**
	 * Constructor sobrecargado de la clase
	 * 
	 * @param codigo de error
	 * @param mensaje texto de error
	 * @param causa de la excepción
	 */
	public ComicException(String codigo, String mensaje, Throwable causa) {
		super(mensaje, causa);
		this.codigo = codigo;		
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
	
}