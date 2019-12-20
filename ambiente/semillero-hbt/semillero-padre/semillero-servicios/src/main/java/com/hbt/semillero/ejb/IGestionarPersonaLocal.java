package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.exceptions.ComicException;

/**
 * Expone los métodos del EJB GestionarPersona 
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 */
@Local
public interface IGestionarPersonaLocal {

	/**
	 * Metodo encargado de crear una persona y persistirla
	 * @param nuevaPersonaDTO para insertar
	 * @return PersonaDTO insertado
	 * @throws ComicException
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public PersonaDTO crearPersona(PersonaDTO nuevaPersonaDTO) throws ComicException;


	/**
	 * Metodo encargado de modificar la información de una persona
	 * @param personaDTO para modificar
	 * @return personaDTO modificado
	 * @throws ComicException
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public PersonaDTO modificarPersona(PersonaDTO personaDTO) throws ComicException;

	
	/**
	 * Metodo encargado de eliminar una persona
	 * 
	 * @param idPersona a eliminar
	 * @throws ComicException
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public void eliminarPersona(Long idPersona) throws ComicException;

	
	/**
	 * Metodo encargado de retornar una lista de personas registradas
	 * @return List<PersonaDTO>
	 * @throws ComicException
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public List<PersonaDTO> consultarPersonas() throws ComicException;
	
	
	/**
	 * Metodo encargado de retornar la informacion de una persona especifica
	 * @param idPersona a consultar
	 * @return personaDTO
	 * @throws ComicException
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public PersonaDTO consultarPersona(Long idPersona) throws ComicException;
}
