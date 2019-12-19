/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ConsultarTotalPersonajesPorComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;
import com.hbt.semillero.exceptions.ComicException;

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
	public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO) {
		logger.debug("Inicio del metodo 'crearPersonaje'");

		try {
			Personaje personaje = convertirDTOEntidad(personajeDTO);
			em.persist(personaje);
			logger.info("Personaje creado correctamente");
		} catch (Exception e) {
			logger.error("Se ha producido en error al crear personaje: "+e);
		}

		logger.debug("Fin del metodo 'crearPersonaje'");		
		return personajeDTO;
	}

	@Override
	public PersonajeDTO modificarPersonaje(PersonajeDTO personajeModificar) throws ComicException {
		logger.debug("Inicio del metodo 'modificarPersonaje'");
		
			if (personajeModificar.getId() == null) {
				throw new ComicException("COD-0011", "El id del objeto es requerido");
			}
			
			Query query = em.createQuery("UPDATE Personaje personaje "
					+ "SET personaje.estado = :estado, "
					+ "personaje.comic.id = :idComic "
					+ "WHERE personaje.id = :idPersonaje");
			
			query.setParameter("estado", personajeModificar.getEstado());
			query.setParameter("idComic", personajeModificar.getIdComic());
			query.setParameter("idPersonaje", personajeModificar.getId());
			query.executeUpdate();
			
			logger.debug("Fin del metodo 'modificarPersonaje'");
			return convertirDTOEntidad(em.find(Personaje.class, personajeModificar.getId()));			
	}

	@Override
	public void eliminarPersonaje(Long idPersonaje) {
		logger.debug("Inicio del metodo 'eliminarPersonaje'");
		
		try {	
			// TODO: Implementar			
		} catch (Exception e) {
			// TODO: handle exception
		}

		logger.debug("Fin del metodo 'eliminarPersonaje'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes() {
		logger.debug("Inicio del metodo 'consultarPersonajes' ");

		// En listpersonajesDTO se almacenarán todos los elementos de listapersonajes
		// pero en DTO
		List<PersonajeDTO> listaPersonajesDTO = new ArrayList<>();

		List<Personaje> listaPersonajes = null;
		
		try {
			// Consulta todos los personajes en la Tabla Personaje
			String qlString = "SELECT p FROM Personaje p";

			// listapersonajes almacena todos los personaje obtenido en la consulta
			listaPersonajes = em.createQuery(qlString).getResultList();

			for (Personaje personaje : listaPersonajes) {
				listaPersonajesDTO.add(convertirDTOEntidad(personaje));
			}
		} catch (Exception e) {
			// TODO: handle exception			
		}
		
		logger.debug("Fin del metodo 'consultarPersonajes' ");
		return listaPersonajesDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes(int index, String cadena) {
		logger.debug("Inicio del metodo 'consultarPersonajes' ");
		
		// En listpersonajesDTO se almacenarán todos los elementos de listapersonajes
		// pero en DTO
		List<PersonajeDTO> listaPersonajesDTO = new ArrayList<>();

		try {
			// Consulta todos los personajes en la Tabla Personaje
			String qlString = "SELECT personaje FROM Personaje personaje";

			// listapersonajes almacena todos los personaje obtenido en la consulta
			List<Personaje> listaPersonajes = em.createQuery(qlString).getResultList();

			for (Personaje personaje : listaPersonajes) {
				listaPersonajesDTO.add(convertirDTOEntidad(personaje));
			}
			
			PersonajeDTO personajeDTO = listaPersonajesDTO.get(index);
			Long valorCadena = Long.parseLong(cadena);
			
			BigDecimal bigUno = new BigDecimal("10");
			BigDecimal bigDos = BigDecimal.ZERO;
			BigDecimal bigTotal = bigUno.divide(bigDos);
			

		} catch (IndexOutOfBoundsException e) {
			logger.error("Indice por fuera del limite");
		} catch (NumberFormatException e) {
			logger.error("Error al convertir a numero la cadena de texto");			
		} catch (ArithmeticException e) {
			logger.error("Error Divición por cero");			
		}

		logger.debug("Fin del metodo 'consultarPersonajes' ");
		return listaPersonajesDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonajeDTO> consultarPersonajes(Long idComic) {
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
	 * JOIN: Es una consulta que combina resultados de distintas entidades
	 * INNER JOIN: Devuelve todas las entidades del lado izquierdo que tienen una
	 * relacion con la entidad del lado derecho de la relación
	 * 
	 * Se define de forma explicita así:
	 * [SELECT EntidadA ea JOIN ea.EntidadB EB]
	 * 
	 *  Su traducción a SQL será:
	 *  [SELECT EA.* FROM EntidadA EA, EntidadB EB WHERE EA.id = EB.id]
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsultarTotalPersonajesPorComicDTO> ConsultarTotalPersonajesPorComicDTO()
			throws ComicException {
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT new com.hbt.semillero.dto.ConsultarTotalPersonajesPorComicDTO(COUNT(p.id), c.nombre) ");
			sb.append("FROM Personaje p ");
			sb.append("JOIN p.comic c ");
			sb.append("GROUP BY c.nombre");	
			
			return em.createQuery(sb.toString()).getResultList();
		} catch (Exception e) {
			throw new ComicException("COD-0013", "Error en la consulta de los totales: ", e);
		}
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
