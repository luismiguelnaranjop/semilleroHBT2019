/**
 * GestionarComicPOJO.java
 */
package com.hbt.semillero.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.EstadoEnum;
import com.hbt.semillero.entidad.TematicaEnum;

/**
 * <b>Descripción:<b> Clase que determina el uso de metodos para manipular
 * informacion POJO «Plain Old Java Object», que puede interpretarse como «Un
 * objeto Java Plano». Es una instancia de una clase que no extiende ni
 * implementa nada en particular.
 * 
 * @author ccastano
 * @version 1.0
 */
public class GestionarComicPOJO {

	/**
	 * Lista que permite reunir un conjunto de comics
	 */
	private List<ComicDTO> listaComics = null;

	/**
	 * 
	 * Metodo encargado de crear un nuevo comic con informacion por defecto
	 * 
	 * @author ccastano
	 *
	 */
	public void crearComicDTO() {
		// Se crea e instancia un objeto de tipo ComicDTO
		ComicDTO comic = new ComicDTO();
		TematicaEnum aventuras = TematicaEnum.AVENTURAS;
		comic.setId("100");
		comic.setNombre("EL EJÉRCITO DEL CAPITÁN AMÉRICA");
		comic.setEditorial("Panini Comics ");
		comic.setTematicaEnum(aventuras);
		comic.setColeccion("BIBLIOTECA MARVEL ");
		comic.setNumeroPaginas(128);
		comic.setPrecio(new BigDecimal(50));
		comic.setAutores("Phillippe Briones, Roger Stern ");
		comic.setColor(Boolean.TRUE);
		comic.setFechaVenta(LocalDate.now());
		comic.setEstadoEnum(EstadoEnum.ACTIVO);
		comic.setCantidad(200L);

		// Adicionar el comic creado a la lista de comics
		// Se valida primero que la lista este inicializada
		if (listaComics == null) {
			listaComics = new ArrayList<>();
		}
		// Se adiciona el nuevo elmento a la lista
		listaComics.add(comic);
	}

	/**
	 * 
	 * Metodo encargado de cambiar el nombre a un comic con el mismo id
	 * 
	 * @author ccastano
	 * 
	 * @param comicModificar
	 */
	public void modificarComicDTO(ComicDTO comicModificar) {
		// validacion que permite determinar si una lista esta inicializada y tiene
		// elementos
		if (listaComics != null && !listaComics.isEmpty()) {
			int i = 0;
			// ciclo while que permite recorrerer una lista
			// iniciando desde cero hasta el tamaño de la lista de comics
			while (i <= listaComics.size()) {
				if (listaComics.get(i).getId().equals(comicModificar.getId())) {
					listaComics.get(i).setNombre(comicModificar.getNombre());
				}
				// atributo que permite incrementar el iterador
				// permite controlar cuando se termine el ciclo o
				// cuando terminar
				i++;
			}
		}
	}

	/**
	 * 
	 * Metodo encargado de eliminar un elemento determinado de la lista de comic
	 * 
	 * @param comicEliminar
	 */
	public void eliminarComicDTO(ComicDTO comicEliminar) {
		// validacion que permite determinar si una lista esta inicializada y tiene
		// elementos
		if (listaComics != null && !listaComics.isEmpty()) {
			// ciclo for each
			for (ComicDTO comicIterar : listaComics) {
				// validacion del mismo id
				if (comicIterar.getId().equals(comicEliminar.getId())) {
					// metodo que permite quitar un elemento de una lista
					listaComics.remove(comicIterar);
				}
			}
		}
	}

	/**
	 * 
	 * Metodo encargado de retornar un comic dado un id determinadao
	 * 
	 * @param idComic identificador del comic a ser buscado
	 * @return comic encontrado
	 */
	public ComicDTO consultarComicDTO(String idComic) {
		// validacion que permite determinar si una lista esta inicializada y tiene
		// elementos
		if (listaComics != null && !listaComics.isEmpty()) {
			// ciclo for
			for (int i = 0; i <= listaComics.size(); i++) {
				if (listaComics.get(i).getId().equals(idComic)) {
					return listaComics.get(i);
				}
			}
		}
		return null;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo listaComics
	 * 
	 * @return El listaComics asociado a la clase
	 */
	public List<ComicDTO> getListaComics() {
		return listaComics;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo listaComics
	 * 
	 * @param listaComics El nuevo listaComics a modificar.
	 */
	public void setListaComics(List<ComicDTO> listaComics) {
		this.listaComics = listaComics;
	}

}
