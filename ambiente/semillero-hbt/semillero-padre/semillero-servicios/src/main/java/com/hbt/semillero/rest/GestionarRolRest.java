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
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.ejb.IGestionarRolLocal;

/**
 * @descripcion Clase que determina el servicio rest que permite gestionar un rol
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjopq@gmail.com>
 * @version
 */

@Path("/GestionarRol")
public class GestionarRolRest {

	/**
	 * Atriburo que permite gestionar un rol
	 */
	@EJB
	private IGestionarRolLocal gestionarRolEJB;

	/**
	 * 
	 * Say Hello!
	 * http://localhost:8085/semillero-servicios/rest/GestionarRol/saludo
	 * 
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		return "Gestionar roles - Prueba inicial servicios rest en el semillero java hbt";
	}
	
	/**
	 * Crea roles
	 * http://localhost:8085/semillero-servicios/rest/GestionarRol/crear
	 * 
	 * @param rolDTO
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearRol(RolDTO rolDTO) {
		gestionarRolEJB.crearRol(rolDTO);
		ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Rol creado exitosamente");
		return resultadoDTO;
	}
	
	
	/**
	 * 
	 * Metodo encargado de traer todos los roles
	 * http://localhost:8085/semillero-servicios/rest/GestionarRol/consultarRoles
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarRoles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO> consultarRoles() {
		return gestionarRolEJB.consultarRoles();
	}
}