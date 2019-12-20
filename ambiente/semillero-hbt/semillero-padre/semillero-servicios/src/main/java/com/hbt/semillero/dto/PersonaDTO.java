/**
 * 
 */
package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.hbt.semillero.entidad.TipoDocEnum;

/**
 * Clase que determina el dto a usar para modificar, consultar y posteriormente
 * eliminar la información de una persona
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 *
 */
public class PersonaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private TipoDocEnum tipo_doc;
	private String numero_doc;
	private LocalDate fecha_nac;

	/**
	 * Contructor de la clase
	 */
	public PersonaDTO() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipo_doc
	 */
	public TipoDocEnum getTipo_doc() {
		return tipo_doc;
	}

	/**
	 * @param tipo_doc the tipo_doc to set
	 */
	public void setTipo_doc(TipoDocEnum tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	/**
	 * @return the numero_doc
	 */
	public String getNumero_doc() {
		return numero_doc;
	}

	/**
	 * @param numero_doc the numero_doc to set
	 */
	public void setNumero_doc(String numero_doc) {
		this.numero_doc = numero_doc;
	}

	/**
	 * @return the fecha_nac
	 */
	public LocalDate getFecha_nac() {
		return fecha_nac;
	}

	/**
	 * @param fecha_nac the fecha_nac to set
	 */
	public void setFecha_nac(LocalDate fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo PersonaDTO.
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static PersonaDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, PersonaDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en ComicDTO al JSON
	 * esperado
	 * 
	 * @param dto DTO
	 * 
	 * @return Json
	 */
	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}

}
