/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;

/**
 * Clase que determina el bean para realizar las gestion de los personajes
 * 
 * @author Luis Miguel Naranjo Pastrana
 * @version
 */
@Stateless
public class GestionarPersonajeBean implements IGestionarPersonajeLocal {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	public void crearPersonaje(PersonajeDTO personajeDTO) {
		// TODO Auto-generated method stub
		logger.debug("Inicio del metodo 'crearPersonaje'");
		logger.debug("Data recibida: "+personajeDTO);
		

		Personaje personaje = convertirDTOEntidad(personajeDTO);
		em.persist(personaje);

		logger.debug("Fin del metodo 'crearPersonaje'");
	}

	@Override
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeModificar) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'modificarPersonaje'");

		logger.debug("Fin del metodo 'modificarPersonaje'");
	}

	@Override
	public void eliminarPersonaje(Long idPersonaje) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'eliminarPersonaje'");

		logger.debug("Fin del metodo 'eliminarPersonaje'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes() {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'consultarPersonajes' ");

		// Consulta todos los personajes en la Tabla Personaje
		String qlString = "SELECT personaje FROM Personaje personaje";

		// listapersonajes almacena todos los personaje obtenido en la consulta
		List<Personaje> listaPersonajes = em.createQuery(qlString).getResultList();

		// En listpersonajesDTO se almacenarán todos los elementos de listapersonajes
		// pero en DTO
		List<PersonajeDTO> listaPersonajesDTO = new ArrayList<>();

		for (Personaje personaje : listaPersonajes) {
			listaPersonajesDTO.add(convertirDTOEntidad(personaje));
		}

		logger.debug("Fin del metodo 'consultarPersonajes' ");
		return listaPersonajesDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes(Long idComic) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'consultarPersonajes?idComic' ");

		/**
		 * SQL para consultar todos los personajes en la Tabla Personaje que pertenezcan
		 * a un comic específico
		 */
		String qlString = "SELECT p FROM Personaje p WHERE p.comic.id = :idComic";

		// listapersonajes almacena todos los personaje obtenido en la consulta
		List<Personaje> listaPersonajes = em.createQuery(qlString).setParameter("idComic", idComic).getResultList();

		// En listpersonajesDTO se almacenarán todos los elementos de listapersonajes
		// pero en DTO
		List<PersonajeDTO> listaPersonajesDTO = new ArrayList<>();

		for (Personaje personaje : listaPersonajes) {
			listaPersonajesDTO.add(convertirDTOEntidad(personaje));
		}

		logger.debug("Fin del metodo 'consultarPersonajes?idComic' ");
		return listaPersonajesDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un PersonajeDTO a una entidad personaje
	 * 
	 * @param personajeDTO
	 * @return personaje
	 */
	private Personaje convertirDTOEntidad(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();

		personaje.setId(personajeDTO.getId());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setComic(new Comic());
		personaje.getComic().setId(personajeDTO.getIdComic());
		personaje.setRol(new Rol());
		personaje.getRol().setId(personajeDTO.getIdRol());;
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());

		return personaje;
	}

	/**
	 * 
	 * Metodo encargado de transformar la entidad Personaje a un PersonajeDTO
	 * 
	 * @param personaje
	 * @return personajeDTO
	 */
	private PersonajeDTO convertirDTOEntidad(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();

		personajeDTO.setId(personaje.getId());
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setIdComic(personaje.getComic().getId());
		personajeDTO.setIdRol(personaje.getRol().getId());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setSuperPoder(personaje.getSuperPoder());

		return personajeDTO;
	}
}
