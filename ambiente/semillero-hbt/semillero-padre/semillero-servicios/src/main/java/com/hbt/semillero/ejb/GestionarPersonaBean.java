/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.entidad.Persona;
import com.hbt.semillero.exceptions.ComicException;

/**
 * Clase que determina el bean para realizar las gestion de las personas
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestionarPersonaLocal{

	final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/*************************************************************************************************/
	/* 									METODOS PARA HACER CRUD 									 */
	/*************************************************************************************************/

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PersonaDTO crearPersona(PersonaDTO nuevaPersonaDTO) throws ComicException {
		logger.debug("Se ejecuta el metodo 'crearPersona'");

		try {
			Persona persona = convertirPersonaDTOToPersona(nuevaPersonaDTO);
			em.persist(persona);
		} catch (Exception e) {
			logger.error("Se produjo un error al crear persona: " + e);
			throw new ComicException("COD-0001", "Error al crear persona", e);
		}

		logger.debug("Finaliza el metodo 'crearPersona'");
		return nuevaPersonaDTO;	
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PersonaDTO modificarPersona(PersonaDTO personaDTO) throws ComicException {
		logger.debug("Se ejecuta el metodo 'modificarPersona'");

		try {

			if (personaDTO.getId() == null) {
				throw new ComicException("COD-0010", "El id del objeto es requerido");
			}

			StringBuilder sbJPQL = new StringBuilder();
			sbJPQL.append("UPDATE Persona p ");
			sbJPQL.append("SET p.nombre = :nombre ");
			sbJPQL.append("WHERE p.id = :idPersona");

			Query query = em.createQuery(sbJPQL.toString());
			query.setParameter("nombre", personaDTO.getNombre());
			query.setParameter("idPersona", personaDTO.getId());
			query.executeUpdate();

			logger.debug("Termina el metodo 'modificarPersona'");
			return convertirPersonaToPersonaDTO(em.find(Persona.class, personaDTO.getId()));

		} catch (Exception e) {
			logger.error("Se produjo un error al actualizar persona: " + e);
			throw new ComicException("COD-0002", "Error al actualizar persona", e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersona(Long idPersona) throws ComicException {
		logger.debug("Se ejecuta el metodo 'eliminarPersona'");

		try {
			
			StringBuilder sbJPQL = new StringBuilder();
			sbJPQL.append("DELETE FROM Persona P ");
			sbJPQL.append("WHERE p.id = :idPersona");

			Query query = em.createQuery(sbJPQL.toString());
			query.setParameter("idPersona", idPersona);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Error al eliminar persona " + e);
			throw new ComicException("COD-0003", "Error al ejecutar la eliminación de la persona", e);
		}

		logger.debug("Finaliza el metodo 'eliminarPersona'");
	}

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersonas() throws ComicException {
		logger.debug("Se ejecuta el metodo 'consultarPersonas'");

		List<PersonaDTO> listaPersonasDTO = null;

		try {

			String jpql = "SELECT p FROM Persona p";
			Query query = em.createQuery(jpql);
			List<Persona> resultados = query.getResultList();

			listaPersonasDTO = new ArrayList<PersonaDTO>();
			for (Persona persona : resultados) {
				listaPersonasDTO.add(convertirPersonaToPersonaDTO(persona));
			}

		} catch (Exception e) {
			logger.error("Error al consultar personas " + e);
			throw new ComicException("COD-0004", "Error al obtener la lista de personas: ", e);
		}

		logger.debug("Finaliza el metodo 'consultarPersonas'");
		return listaPersonasDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PersonaDTO consultarPersona(Long idPersona) throws ComicException {
		logger.debug("Se ejecuta el metodo 'consultarPersona' por idPersona");

		try {

			Persona persona = new Persona();

			StringBuilder sbJPQL = new StringBuilder();
			sbJPQL.append("SELECT p ");
			sbJPQL.append("FROM Persona p ");
			sbJPQL.append("WHERE p.id = :idPersona");

			Query query = em.createQuery(sbJPQL.toString());
			query.setParameter("idPersona", idPersona);

			persona = (Persona) query.getSingleResult();

			logger.debug("Finaliza el metodo 'consultarPersona' por idPersona");
			return convertirPersonaToPersonaDTO(persona);
		} catch (Exception e) {
			logger.error("Error al consultar persona: " + idPersona);
			throw new ComicException("COD-0004", "Error al consultar el id de la persona: ", e);
		}

	}
	
	/*************************************************************************************************/
	/* 					METODOS PARA COMPLEMENTAR LAS OPERACIONES DEL CRUD 							 */
	/*************************************************************************************************/

	/**
	 * Metodo encargado de transformar persona a un personaDTO
	 * @param Persona
	 * @return PersonaDTO
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	private PersonaDTO convertirPersonaToPersonaDTO(Persona persona) {
		PersonaDTO personaDTO = new PersonaDTO();

		if (persona.getId() != null) {
			personaDTO.setId(persona.getId());
		}
		
		personaDTO.setNombre(persona.getNombre());
		personaDTO.setTipo_doc(persona.getTipo_doc());
		personaDTO.setNumero_doc(persona.getNumero_doc());
		personaDTO.setFecha_nac(persona.getFecha_nac());

		return personaDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar PersonaDTO a un Persona
	 * @param PersonaDTO
	 * @return Persona
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	private Persona convertirPersonaDTOToPersona(PersonaDTO personaDTO) {

		Persona persona = new Persona();

		if (personaDTO.getId() != null) {
			persona.setId(personaDTO.getId());
		}
		
		persona.setNombre(personaDTO.getNombre());
		persona.setTipo_doc(personaDTO.getTipo_doc());
		persona.setNumero_doc(personaDTO.getNumero_doc());
		persona.setFecha_nac(personaDTO.getFecha_nac());

		return persona;
	}
}


