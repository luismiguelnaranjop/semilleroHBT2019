/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los personajes
 * 
 * @author Luis Miguel Naranjo Pastrana
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestionarPersonajeLocal {
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * @see 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'crearPersonaje'");

		
		logger.debug("Fin del metodo 'crearPersonaje'");
	}

	/**
	 * @see 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeModificar) {
		// TODO Auto-generated method stub
		
		logger.debug("Inicio del metodo 'modificarPersonaje'");

		
		logger.debug("Fin del metodo 'modificarPersonaje'");
	}

	/**
	 * @see 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersonaje) {
		// TODO Auto-generated method stub
		
		logger.debug("Inicio del metodo 'eliminarPersonaje'");

		
		logger.debug("Fin del metodo 'eliminarPersonaje'");
	}

	/**
	 * @see 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PersonajeDTO consultarPersonaje(String idPersonaje) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'consultarPersonaje'");

		
		logger.debug("Fin del metodo 'consultarPersonaje'");
		return null;
	}

	/**
	 * @see 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes() {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'consultarPersonajes' ");

		
		logger.debug("Fin del metodo 'consultarPersonajes' ");
		return null;
	}	
}
