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
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.exceptions.ComicException;

/**
 * Clase que determina el servicio rest que permite gestionar un comic
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 *
 */
@Path("/GestionarComic")
public class GestionarComicRest {
	
	final static Logger logger = Logger.getLogger(GestionarComicRest.class);


	/**
	 * Atriburo que permite gestionar un comic
	 */
	@EJB
	private IGestionarComicLocal gestionarComicEJB;

	/**
	 * Say Hello World!
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/saludo
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
//		return Response.ok().entity(saludo).build();	
		return Response.status(Response.Status.OK)
				.entity(saludo)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}


	/**
	 * Crea comics.
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/crearComic
	 * @param comicDTO
	 * @return
	 */
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearComic(ComicDTO comicDTO) {

		ResultadoDTO resultadoDTO = null;
		
		try {

			gestionarComicEJB.crearComic(comicDTO);
			resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic creado exitosamente");

			return Response.status(Response.Status.CREATED)
					.entity(resultadoDTO)
					.build();

		} catch (ComicException e) {
			logger.error("Se ha capturado una excepcion al crear un comic. CODIGO: "+e.getCodigo()+". INFO: "+e.getMessage());
			
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Se presentó un fallo en la invocación del servicio")
					.build();
		}
	
	}

	/**
	 * 
	 * Metodo encargado de modificar el nombre de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/modificar?idComic=1&nombre=nuevonombre
	 * 
	 * @param idComic identificador del comic a buscar
	 * @param nombre nombre nuevo del comic
	 */
	@POST
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO modificarComic(@QueryParam("idComic") Long idComic, @QueryParam("nombre") String nombre) {

		ResultadoDTO resultadoDTO = null;
		try {
			gestionarComicEJB.modificarComic(idComic, nombre, null);
			resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic actualizado exitosamente");
		} catch (ComicException e) {
			resultadoDTO = new ResultadoDTO(Boolean.FALSE, "No se pudo actualizar el comic");
			logger.error("Se ha capturado una excepcion al actualizar un comic. CODIGO: "+e.getCodigo()+". INFO: "+e.getMessage());
		}
		
		return resultadoDTO;		
	}

	/**
	 * Metodo encargado de eliminar un comic dado el id, siempre y cuando éste no tenga
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/eliminar?idComic=3
	 * 
	 * @param idComic identificador del comic
	 */
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO eliminarComic(@QueryParam("idComic") Long idComic) {
		
		ResultadoDTO resultadoDTO = null;
		try {
			if (idComic != null) {
				gestionarComicEJB.eliminarComic(idComic);
				resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic eliminado exitosamente");
			}else {
				resultadoDTO = new ResultadoDTO(Boolean.FALSE, "No se pudo eliminar el comic");
			}		
		} catch (ComicException e) {
			resultadoDTO = new ResultadoDTO(Boolean.FALSE, "No se pudo eliminar el comic");
			logger.debug("Se capturó la excepción. y la información es: Codigo "+e.getCodigo()+" - Mensaje: "+e.getMessage());
		}
		return resultadoDTO;
	}
	
	
	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComic() {
		
		List<ComicDTO> listComicDTO = null;
		
		try {
			listComicDTO =  gestionarComicEJB.consultarComics();			
		} catch (ComicException e) {
			logger.debug("Se capturó la excepción y la información es: Codigo "+e.getCodigo()+" - Mensaje: "+e.getMessage());
		}
		
		return listComicDTO;
	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComic?idComic=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO consultarComic(@QueryParam("idComic") Long idComic) {

		ComicDTO comicDTO = null;

		try {
			if (idComic != null) {
				comicDTO = gestionarComicEJB.consultarComic(idComic);
			} 
		}catch (ComicException e) {
			logger.error("Se capturó la excepcion. Codigo: "+e.getCodigo()+" - INFO: "+e.getMessage());
		}

		return comicDTO;
	}

}
