/**
 * GestionarComicRest.java
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;

/**
 * @descripcion Clase que determina el servicio rest que permite gestionar un personaje
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjopq@gmail.com>
 * @version
 */

@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {

	final static Logger logger = Logger.getLogger(GestionarPersonajeRest.class);

	/**
	 * Atriburo que permite gestionar un personaje
	 */
	@EJB
	private IGestionarPersonajeLocal gestionarPersonajeEJB;

	/**
	 * 
	 * Say Hello!
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/saludo
	 * 
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Gestionar personajes - Prueba inicial servicios rest en el semillero java hbt";
	}
	
	/**
	 * Crea personajes
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/crearPersonaje
	 * 
	 * @param personajeDTO
	 * @return
	 */
	@POST
	@Path("/crearPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearPersonaje(PersonajeDTO personajeDTO) {
		
		ResultadoDTO resultadoDTO = null;
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeDTO);
			resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje creado exitosamente");
		} catch (Exception e) {
			logger.error("Se ha producido un error al crear personaje: "+e.getMessage());
		}
		return resultadoDTO;			
	}
	

	/**
	 * 
	 * Metodo encargado de traer todos los personajes
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajes
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarPersonajes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO> consultarPersonajes() {

		List<PersonajeDTO> listPersonajeDTO = null;
		try {
			listPersonajeDTO =  gestionarPersonajeEJB.consultarPersonajes();
		} catch (Exception e) {
			logger.error("Se ha producido un error al consultar personajes: "+e.getMessage());
		}

		return listPersonajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de traer los personajes de un comic determinado
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajesPorId?idComic=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarPersonajesPorId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO> consultarPersonajes(@QueryParam("idComic") Long idComic){
		
		List<PersonajeDTO> listPersonajeDTO = null;
		try {
			if (idComic != null) {
				listPersonajeDTO = gestionarPersonajeEJB.consultarPersonajes(idComic);
			}
		} catch (Exception e) {
			logger.error("Se ha producido un error al consultar personajes: "+e.getMessage());
		}
		return listPersonajeDTO;
		
	}
	
	/**
	 * 
	 * Metodo para hacer pruebas de excepciones
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajes?index=100&cadena=jgjhgj
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarPersonajesPorParametros")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonajeDTO> consultarPersonajes(@QueryParam("index") int index, @QueryParam("cadena") String cadena) {
		return gestionarPersonajeEJB.consultarPersonajes(index, cadena);
	}

}