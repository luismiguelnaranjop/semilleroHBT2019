/**
 * 
 */
package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que determina la entidad que permite representar la tabla
 * "DB_SEMILLERO"."PERSONAS"
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
@Entity
@Table(name = "PERSONAS")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private TipoDocEnum tipo_doc;
	private String numero_doc;
	private LocalDate fecha_nac;

	/**
	 * Constructor de la clase
	 */
	public Persona() {
	}

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PER_ID_GENERATOR", sequenceName = "SEQ_PERSONA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PER_ID_GENERATOR")
	@Column(name = "PER_ID")
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
	 * @return el nombre de la persona
	 */
	@Column(name = "PER_NOMBRE")
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
	 * Metodo encargado de retornar el valor del atributo tipo_doc
	 * 
	 * @return el tipo de documento de la persona
	 */
	@Column(name = "PER_TIPO_DOCUMENTO")
	@Enumerated(value = EnumType.STRING)
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
	 * Metodo encargado de retornar el valor del atributo numero_doc
	 * 
	 * @return el numero de documento de la persona
	 */
	@Column(name = "PER_NUM_DOCUMENTO")
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
	 * Metodo que retorna el valor del tributo fecha_nac
	 * 
	 * @return la fecha de nacimiento de la persona
	 */
	@Column(name = "PER_NACIMIENTO")
	public LocalDate getFecha_nac() {
		return fecha_nac;
	}

	/**
	 * @param fecha_nac the fecha_nac to set
	 */
	public void setFecha_nac(LocalDate fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fecha_nac, id, nombre, numero_doc, tipo_doc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Persona)) {
			return false;
		}
		Persona other = (Persona) obj;
		return Objects.equals(fecha_nac, other.fecha_nac) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(numero_doc, other.numero_doc)
				&& tipo_doc == other.tipo_doc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", tipo_doc=" + tipo_doc + ", numero_doc=" + numero_doc
				+ ", fecha_nac=" + fecha_nac + "]";
	}

}
