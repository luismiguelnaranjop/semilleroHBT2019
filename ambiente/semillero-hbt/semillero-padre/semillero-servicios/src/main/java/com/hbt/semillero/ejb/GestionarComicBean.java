/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.TematicaEnum;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.interfaces.ICalcularPrecioIVA;

/**
 * Clase que determina el bean para realizar las gestion de los comics
 * 
 * @author Luis Miguel Naranjo Pastrana <luismiguelnaranjop@gmail.com>
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal, ICalcularPrecioIVA {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/*************************************************************************************************/
	/* METODOS PARA HACER CRUD */
	/*************************************************************************************************/

	/**
	 * @description Metodo para crear nuevos Comics
	 * 
	 * @param nuevoComicDTO: DTO con los datos del comic a insertar
	 * @throws ComicException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO nuevoComicDTO) throws ComicException {
		logger.debug("Se ejecuta el metodo 'crearComic'");

		try {
			// Recibe un ComicDTO y lo convierte en un objeto de la entidad Comic listo para
			// persistir
			Comic comic = convertirComicDTOToComic(nuevoComicDTO);
			em.persist(comic);
		} catch (Exception e) {
			logger.error("Se produjo un error al crear comic: " + e);
			throw new ComicException("COD-0001", "Error al crear comic", e);
		}

		logger.debug("Finaliza el metodo 'crearComic'");
	}


	/**
	 * Metodo que permite actualizar el nombre nombre de un comic. merge(T entity):
	 * Combina el estado de la entidad en el contexto de la persistencia actual.
	 * 
	 * @param idComic: identificador del comic al que se le va a modificar el nombre
	 * @param nombre: nuevo nombre del comic
	 * @param comicDTO: DTO con los datos del comic al que se le va a cambiar el
	 *        nombre, puede ser null
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long idComic, String nombre, ComicDTO comicDTO) throws ComicException {
		logger.debug("Se ejecuta el metodo 'modificarComic'");

		Comic comicModificar = null;

		try {

			if (comicDTO == null) {
				// comicModificar = em.find(Comic.class, idComic);
				Query query = em.createQuery("SELECT c FROM Comic c WHERE c.id = :idComic");
				query.setParameter("idComic", idComic);
				comicModificar = (Comic) query.getSingleResult();
			} else {
				comicModificar = convertirComicDTOToComic(comicDTO);
			}

			comicModificar.setNombre(nombre);
			em.merge(comicModificar);

		} catch (Exception e) {
			logger.error("Se produjo un error al actualizar el comic: " + e);
			throw new ComicException("COD-0002", "Error al actualizar comic", e);
		}

		logger.debug("Termina el metodo 'modificarComic'");
	}

	/**
	 * Metodo que elimina un comic a partir de su identidicador
	 * 
	 * @param idComic: identificador del comic
	 * @throws ComicExcepcion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) throws ComicException {
		logger.debug("Se ejecuta el metodo 'eliminarComic'");

		try {
//			Comic comicEliminar = em.find(Comic.class, idComic);
//			if (comicEliminar != null) {
//				em.remove(comicEliminar);
//			}
//			em.flush();
//			em.clear();

			Query query = em.createQuery("DELETE FROM Comic c WHERE c.id = :idComic").setParameter("idComic", idComic);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Error al eliminar comic " + e);
			throw new ComicException("COD-0003", "Error al ejecutar la eliminación del Comic", e);
		}

		logger.debug("Finaliza el metodo 'eliminarComic'");
	}

	/**
	 * Metodo que permite obtener una lista con todos los comic
	 * 
	 * @throws ComicException
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() throws ComicException {
		logger.debug("Se ejecuta el metodo 'consultarComics'");

		List<ComicDTO> listComicsDTO = null;

		try {
			List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
			listComicsDTO = new ArrayList<ComicDTO>();
			for (Comic comic : resultados) {
				listComicsDTO.add(convertirComicToComicDTO(comic));
			}
		} catch (Exception e) {
			logger.error("Error al eliminar comic " + e);
			throw new ComicException("COD-0004", "Error al obtener la lista de Comics: ", e);
		}

		logger.debug("Finaliza el metodo 'consultarComics'");
		return listComicsDTO;
	}

	/**
	 * 
	 * @throws ComicException
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(Long idComic) throws ComicException {
		logger.debug("Se ejecuta el metodo 'consultarComic' por idComic");

		ComicDTO comicDTO = null;

		try {

			Comic comic = new Comic();
//			comic = em.find(Comic.class, Long.parseLong(idComic));

			Query query = em.createQuery("SELECT c FROM Comic c WHERE c.id = :idComic");
			query.setParameter("idComic", idComic);

			comic = (Comic) query.getSingleResult();
			comicDTO = convertirComicToComicDTO(comic);

		} catch (Exception e) {
			logger.error("Error al consultar comic: " + idComic);
			throw new ComicException("COD-0004", "Error al consultar el Comic", e);
		}

		logger.debug("Finaliza el metodo 'consultarComic' por idComic");
		return comicDTO;
	}

	/*************************************************************************************************/
	/* METODOS PARA COMPLEMENTAR LAS OPERACIONES DEL CRUD */
	/*************************************************************************************************/

	/**
	 * Metodo que asigna un porcentaje de IVA a un comic dependiendo la tematica de
	 * dicho comic
	 * 
	 * @param tematicaEnum
	 * @return
	 */
	@Override
	public double PorcentajeIVA(TematicaEnum tematica) {

		switch (tematica) {
		case AVENTURAS:
			return 0.05;
		case BELICO:
			return 0.16;
		case DEPORTIVO:
			return 0.10;
		case FANTASTICO:
			return 0.05;
		case CIENCIA_FICCION:
			return 0.16;
		case HISTORICO:
			return 0.05;
		case HORROR:
			return 0.16;
		default:
			return 0;
		}
	}

	/**
	 * Metodo que calcula el precio total de un Comic
	 * 
	 * @param iva
	 * @param precio base
	 * @return
	 */
	@Override
	public BigDecimal CalcularPrecioTotal(double iva, BigDecimal precio) {
		BigDecimal total = precio.add(precio.multiply(new BigDecimal(iva)));
		return total;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {

		ComicDTO comicDTO = new ComicDTO();

		if (comic.getId() != null) {
			comicDTO.setId(comic.getId().toString());
		}

		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());

		// Le asignamos el porcentaje de IVA correspondiente
		comic.setIva(PorcentajeIVA(comic.getTematicaEnum()));

		// Calculo del precio total con IVA
		comicDTO.setPrecioTotal(CalcularPrecioTotal(comic.getIva(), comic.getPrecio()));
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		if (comicDTO.getId() != null) {
			comic.setId(Long.parseLong(comicDTO.getId()));
		}
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}
}
