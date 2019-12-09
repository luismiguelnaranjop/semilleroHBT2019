/**
 * Personaje.java
 */
package com.hbt.semillero.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que determina la entidad que permite representar la tabla
 * "DB_SEMILLERO"."PERSONAJE"
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 *
 */
@Entity
@Table(name = "PERSONAJE")
public class Personaje implements Serializable {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. mediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private Comic comic;
	private EstadoEnum estado;
	private String superPoder;

	/**
	 * Constructor de la clase
	 */
	public Personaje() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "COMIC_ID_GENERATOR", sequenceName = "SEQ_PERSONAJE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMIC_ID_GENERATOR")
	@Column(name = "SPID")
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
	 * Metodo encargado de retornar el valor del atributo nombre
	 * 
	 * @return El nombre asociado a la clase
	 */
	@Column(name = "SPNOMBRE")
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
	 * Metodo encargado de retornar comic al que pertenece el personaje
	 * 
	 * @return El nombre asociado a la clase
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPID_COMIC")
	public Comic getComic() {
		return comic;
	}

	/**
	 * @param comic the comic to set
	 */
	public void setComic(Comic comic) {
		this.comic = comic;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo estado
	 * 
	 * @return El estado asociado a la clase
	 */
	@Column(name = "SPESTADO")
	@Enumerated(value = EnumType.STRING)
	public EstadoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo superPoder
	 * 
	 * @return El superPoder asociado a la clase
	 */
	@Column(name = "SPSUPERPODER")
	public String getSuperPoder() {
		return superPoder;
	}

	/**
	 * @param superPoder the superPoder to set
	 */
	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() Metodo que permite asociar al objeto un
	 * texto representativo
	 */
	@Override
	public String toString() {
		return "Personaje [id=" + id + ", nombre=" + nombre + ", comic=" + comic + ", estado=" + estado
				+ ", superPoder=" + superPoder + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode() Este método viene a complementar al método
	 * equals y sirve para comparar objetos de una forma más rápida en estructuras
	 * Hash ya que únicamente nos devuelve un número entero. Cuando Java compara dos
	 * objetos en estructuras de tipo hash (HashMap, HashSet etc) primero invoca al
	 * método hashcode y luego el equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comic == null) ? 0 : comic.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((superPoder == null) ? 0 : superPoder.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object) Metodo que permite comparar
	 * objetos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		if (comic == null) {
			if (other.comic != null)
				return false;
		} else if (!comic.equals(other.comic))
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (superPoder == null) {
			if (other.superPoder != null)
				return false;
		} else if (!superPoder.equals(other.superPoder))
			return false;
		return true;
	}
}
