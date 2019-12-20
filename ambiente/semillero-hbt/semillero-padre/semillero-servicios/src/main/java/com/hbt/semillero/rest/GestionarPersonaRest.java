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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarPersonaLocal;
import com.hbt.semillero.exceptions.ComicException;

/**
 * Clase que determina el servicio rest que permite gestionar la entidad persona 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 *
 */
@Path("/GestionarPersona")
public class GestionarPersonaRest {

	final static Logger logger = Logger.getLogger(GestionarPersonaRest.class);

	/**
	 * Atriburo que permite gestionar una persona
	 */
	@EJB
	private IGestionarPersonaLocal gestionarPersonaEJB;

	/**
	 * Say Hello World!
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/saludo
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response primerRest() {
		logger.debug("Log enviado con la librería logger");
		String saludo = "Prueba inicial servicios rest en el semillero java hbt";
		return Response.status(Response.Status.OK).entity(saludo).type(MediaType.APPLICATION_JSON).build();
	}

	/**
	 * Crea persona.
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/crearPersona
	 * 
	 * @param personaDTO
	 * @return
	 */
	@POST
	@Path("/crearPersona")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearPersona(PersonaDTO personaDTO) {

		try {	
			
			gestionarPersonaEJB.crearPersona(personaDTO);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Persona creada exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();

		} catch (ComicException e) {
			logger.error("Se ha capturado una excepcion al crear una persona. CODIGO: " + e.getCodigo() + ". INFO: "
					+ e.getMessage());

			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Se presentó un fallo en la invocación del servicio").build();
		}
	}
	
	/**
	 * 
	 * Metodo encargado de modificar una persona
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/modificarPersona
	 * 
	 * @param personaDTO
	 * @return
	 */
	@POST
	@Path("/modificarPersona")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarComic(PersonaDTO personaDTO) {

		try {
			gestionarPersonaEJB.modificarPersona(personaDTO);
			return Response.status(Response.Status.OK).entity(personaDTO).build();

		} catch (ComicException e) {

			logger.error("Se ha capturado una excepcion al actualizar una persona. CODIGO: " + e.getCodigo() + ". INFO: "
					+ e.getMensaje());
			return Response.status(Response.Status.OK).entity("Error al actualizar una persona").build();

		}

	}

	/**
	 * Metodo encargado de eliminar una persona a partir de su id
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/eliminarPersona?idPersona=6
	 * 
	 * @param idPersona identificador de la persona
	 */
	@POST
	@Path("/eliminarPersona")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarComic(@QueryParam("idPersona") Long idPersona) {

		try {
			
			ResultadoDTO resultadoDTO = null;
			if (idPersona != null) {
				gestionarPersonaEJB.eliminarPersona(idPersona);
				resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic eliminado exitosamente");
			} else {
				resultadoDTO = new ResultadoDTO(Boolean.FALSE, "No se pudo eliminar el comic");
			}

			return Response.status(Response.Status.OK).entity(resultadoDTO).build();

		} catch (ComicException e) {

			logger.debug("Se capturó la excepción. y la información es: Codigo " + e.getCodigo() + " - Mensaje: "
					+ e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo eliminar la persona").build();
		}
	}

	
	/**
	 * 
	 * Metodo encargado de traer la informacion de todos las personas
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarPersonas
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Path("/consultarPersonas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarComic() {

		try {
			
			List<PersonaDTO> listPersonaDTO = gestionarPersonaEJB.consultarPersonas();
			return Response.status(Response.Status.OK).entity(listPersonaDTO).build();

		} catch (ComicException e) {
			
			logger.debug("Se capturó la excepción y la información es: Codigo " + e.getCodigo() + " - Mensaje: "
					+ e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo obtener la lista de personas").build();
			
		}

	}

}