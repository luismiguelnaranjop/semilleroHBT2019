package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;;

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
public interface IGestionarPersonajeLocal {

	/**
	 * @description Metodo encargado de crear un personaje y persistirlo
	 * @param personajeDTO informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeDTO);

	/**
	 * @description Metodo encargado de consultar un personaje, modificarlo y
	 *              guardarlo
	 * @param personajeDTO informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeDTO);

	/**
	 * @description Metodo encargado de eliminar un personaje
	 * @param idPersonaje informacion a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje);

	/**
	 * @description Metodo encargado de retornar una lista de personajes
	 * @return List<PersonajeDTO> Lista de personajes
	 */
	public List<PersonajeDTO> consultarPersonajes();

	/**
	 * @description Metodo encargado de retornar una lista de personajes que
	 *              pertenecen a un comic determinado
	 * 
	 * @return List<PersonajeDTO> Lista de personajes
	 */
	public List<PersonajeDTO> consultarPersonajes(Long idComic);
}
