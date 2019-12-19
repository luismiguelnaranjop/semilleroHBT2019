package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.exceptions.ComicException;

/**
 * Expone los métodos del EJB GestionarComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author ccastano
 *
 */
@Local
public interface IGestionarComicLocal {

	/**
	 * 
	 * Metodo encargado de crear un comic y persistirlo
	 * 
	 * @param nuevoComicDTO
	 * @throws ComicException
	 * 
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @return 
	 */
	public ComicDTO crearComic(ComicDTO nuevoComicDTO) throws ComicException;


	/**
	 * 
	 * Metodo encargado de consultar un comic por ID y actualizar su nombre
	 *  
	 * @param comicDTO
	 * @throws ComicException
	 * 
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 * @return 
	 */
	public ComicDTO modificarComic(ComicDTO comicDTO) throws ComicException;

	/**
	 * Metodo encargado de eliminar un comic modificarlo y guardarlo
	 * 
	 * @param idComic
	 * @throws ComicException
	 * 
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public void eliminarComic(Long idComic) throws ComicException;

	/**
	 * 
	 * Metodo encargado de retornar una lista de comics
	 * 
	 * @return
	 * @throws ComicException 
	 */
	public List<ComicDTO> consultarComics() throws ComicException;
	
	/**
	 * 
	 * Metodo encargado de retornar la informacion de un comic
	 * 	 
	 * @param idComic
	 * @return ComicDTO
	 * @throws ComicException
	 * 
	 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
	 */
	public ComicDTO consultarComic(Long idComic) throws ComicException;

}
