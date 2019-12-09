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

import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Rol;

/**
 * Clase que determina el bean para realizar las gestion de los roles
 * 
 * @author Luis Miguel Naranjo Pastrana
 * @version
 */
@Stateless
public class GestionarRolBean implements IGestionarRolLocal {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	public void crearRol(RolDTO rolDTO) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'crearRol'");

		Rol rol = convertirDTOEntidad(rolDTO);
		em.persist(rol);

		logger.debug("Fin del metodo 'crearRol'");
	}

	@Override
	public void modificarRol(Long id, String nombre, RolDTO rolDTO) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'modificarRol'");

		logger.debug("Fin del metodo 'modificarRol'");
	}

	@Override
	public void eliminarRol(Long idRol) {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'eliminarRol'");

		logger.debug("Fin del metodo 'eliminarRol'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolDTO> consultarRoles() {
		// TODO Auto-generated method stub

		logger.debug("Inicio del metodo 'consultarRoles' ");

		// Consulta todos los roles en la Tabla Rol
		String qlString = "SELECT rol FROM Rol rol";

		// listaRoles almacena todos los roles obtenido en la consulta
		List<Rol> listaRoles = em.createQuery(qlString).getResultList();

		// En listpersonajesDTO se almacenarán todos los elementos de listapersonajes
		// pero en DTO
		List<RolDTO> listaRolesDTO = new ArrayList<>();

		for (Rol rol : listaRoles) {
			listaRolesDTO.add(convertirDTOEntidad(rol));
		}

		logger.debug("Fin del metodo 'consultarRoles' ");
		return listaRolesDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un RolDTO a una entidad rol
	 * 
	 * @param rolDTO
	 * @return rol
	 */
	private Rol convertirDTOEntidad(RolDTO rolDTO) {
		Rol rol = new Rol();

		rol.setId(rolDTO.getId());
		rol.setNombre(rolDTO.getNombre());
		rol.setEstado(rolDTO.getEstado());

		return rol;
	}

	/**
	 * 
	 * Metodo encargado de transformar un rol a una entidad rolDTO
	 * 
	 * @param rol
	 * @return rolDTO
	 */
	private RolDTO convertirDTOEntidad(Rol rol) {
		RolDTO rolDTO = new RolDTO();

		rolDTO.setId(rol.getId());
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setEstado(rol.getEstado());

		return rolDTO;
	}
}
