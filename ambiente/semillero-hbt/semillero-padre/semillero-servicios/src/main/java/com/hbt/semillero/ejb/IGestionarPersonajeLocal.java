package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ConsultarTotalPersonajesPorComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.exceptions.ComicException;;

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
	 * @return 
	 */
	public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO);

	/**
	 * @description Metodo encargado de consultar un personaje, modificarlo y
	 *              guardarlo
	 * @param personajeDTO informacion nueva a modificar
	 * @return 
	 * @throws ComicException 
	 */
	public PersonajeDTO modificarPersonaje(PersonajeDTO personajeDTO) throws ComicException;

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

	/**
	 * @description Metodo para hacer pruebas de excepciones
	 * 
	 * @return Lista de comics con su respectiva cantidad de personajes
	 * @throws ComicException
	 */
	public List<ConsultarTotalPersonajesPorComicDTO> ConsultarTotalPersonajesPorComicDTO() throws ComicException;
	
	/**
	 * @description Metodo para hacer pruebas de excepciones
	 * 
	 * @return List<PersonajeDTO> Lista de personajes
	 */
	public List<PersonajeDTO> consultarPersonajes(int index, String cadena);

}
