package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;;

/**
 * Expone los métodos del EJB GestionarPersonaje Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 *
 */
@Local
public interface IGestionarRolLocal {

	/**
	 * @description Metodo encargado de crear un rol y persistirlo
	 * @param rolDTO informacion nueva a crear
	 */
	public void crearRol(RolDTO rolDTO);

	/**
	 * @description Metodo encargado de consultar un rol, modificarlo y
	 * guardarlo
	 * 
	 * @param rolDTO rol a modificar
	 * @param nombre nuevo nombre del rol 
	 * @param id id del rol a modificar
	 */
	public void modificarRol(Long id, String nombre, RolDTO rolDTO);

	/**
	 * @description Metodo encargado de eliminar un rol
	 * @param idRol informacion a eliminar
	 */
	public void eliminarRol(Long idRol);

	/**
	 * @description Metodo encargado de retornar una lista de roles
	 * @return List<RolDTO> Lista de roles
	 */
	public List<RolDTO> consultarRoles();
}
