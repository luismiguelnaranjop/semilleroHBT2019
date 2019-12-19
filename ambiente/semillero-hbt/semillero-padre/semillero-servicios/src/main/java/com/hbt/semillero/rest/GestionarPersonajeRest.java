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
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ConsultarTotalPersonajesPorComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarPersonajeLocal;
import com.hbt.semillero.exceptions.ComicException;

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
	public Response crearPersonaje(PersonajeDTO personajeDTO) {
		
		try {
			gestionarPersonajeEJB.crearPersonaje(personajeDTO);
			return Response.status(Response.Status.CREATED)
					.entity(personajeDTO)
					.build();			
		} catch (Exception e) {
			logger.error("Se ha producido un error al crear personaje: "+e);
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(e)
					.build();
		}
	}
	

	/**
	 * Crea personajes
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/modificarPersonaje
	 * 
	 * @param personajeDTO
	 * @return
	 */
	@POST
	@Path("/modificarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarPersonaje(PersonajeDTO personajeDTO) {
		
		try {
			gestionarPersonajeEJB.modificarPersonaje(personajeDTO);
			return Response.status(Response.Status.OK)
					.entity(personajeDTO)
					.build();			
		} catch (ComicException e) {
			logger.error("Se ha producido un error al modificar personaje: "+e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(e)
					.build();
		}
	}
	
	
	/**
	 * Metodo encargado de eliminar un comic dado el id, siempre y cuando éste no
	 * tenga
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/eliminarPersonaje?idPersonaje=46
	 * 
	 * @param idComic identificador del comic
	 */
	@POST
	@Path("/eliminarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPersonaje(@QueryParam("idPersonaje") Long idPersonaje) {

		try {
			
			ResultadoDTO resultadoDTO = null;
			if (idPersonaje != null) {
				gestionarPersonajeEJB.eliminarPersonaje(idPersonaje);;
				resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje eliminado exitosamente");
			} else {
				resultadoDTO = new ResultadoDTO(Boolean.FALSE, "No se pudo eliminar el personaje");
			}

			return Response.status(Response.Status.OK).entity(resultadoDTO).build();

		} catch (ComicException e) {

			logger.debug("Se capturó la excepción. y la información es: Codigo " + e.getCodigo() + " - Mensaje: "
					+ e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo eliminar el Personaje").build();
		}
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
	public Response consultarPersonajes() {

		try {
			List<PersonajeDTO> listPersonajeDTO = gestionarPersonajeEJB.consultarPersonajes();
			return Response.status(Response.Status.OK).entity(listPersonajeDTO).build();
		} catch (ComicException e) {
			logger.error("Se ha producido un error al consultar personajes: " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo obtener la lista de personajes")
					.build();
		}

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
	public Response consultarPersonajes(@QueryParam("idComic") Long idComic) {

		List<PersonajeDTO> listPersonajeDTO = null;

		try {

			if (idComic != null) {
				listPersonajeDTO = gestionarPersonajeEJB.consultarPersonajes(idComic);
			}

			return Response.status(Response.Status.OK).entity(listPersonajeDTO).build();
		} catch (ComicException e) {
			logger.error("Se ha producido un error al consultar personajes: " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron obtener los personajes de comic")
					.build();
		}

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

	/**
	 * 
	 * Metodo que permite consultar el total de personajes por Comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarTotalPersonajesPorComic
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarTotalPersonajesPorComic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarTotalPersonajesPorComicDTO() {

		try {
			List<ConsultarTotalPersonajesPorComicDTO> listTotales = gestionarPersonajeEJB
					.ConsultarTotalPersonajesPorComicDTO();
			return Response.status(Response.Status.OK)
					.entity(listTotales)
					.build();
		} catch (ComicException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(e)
					.build();
		}

	}

}