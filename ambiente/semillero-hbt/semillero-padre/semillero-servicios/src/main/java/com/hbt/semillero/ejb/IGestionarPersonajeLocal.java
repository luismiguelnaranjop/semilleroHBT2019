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
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeNuevo);


	/**
	 * @description Metodo encargado de consultar un personaje modificarlo y guardarlo
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @param personajeModificar informacion nueva a modificar
	 */
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeModificar);


	/**
	 * @description Metodo encargado de eliminar un personaje modificarlo y guardarlo
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @param idPersonaje informacion a eliminar
	 */
	public void eliminarPersonaje(Long idPersonaje);
	
	
	/**
	 * @description Metodo encargado de retornar la informacion de un personaje
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @param idPersonaje identificador del personaje a ser consultado
	 * @return personaje Resultado de la consulta
	 */
	public PersonajeDTO consultarPersonaje(String idPersonaje);

	
	/**
	 * @description Metodo encargado de retornar una lista de personajes	 * 
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @return Personajes Lista de personajes
	 */
	public List<PersonajeDTO> consultarPersonajes();
}
