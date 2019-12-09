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

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;

/**
 * @descripcion Clase que determina el servicio rest que permite gestionar un personaje
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjopq@gmail.com>
 * @version
 */

@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {

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
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/crear
	 * 
	 * @param personajeDTO
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearPersonaje(PersonajeDTO personajeDTO) {
		gestionarPersonajeEJB.crearPersonaje(personajeDTO);
		ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje creado exitosamente");
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
		return gestionarPersonajeEJB.consultarPersonajes();
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
		if (idComic != null) {
			return gestionarPersonajeEJB.consultarPersonajes(idComic);
		}
		return null;		
	}
}