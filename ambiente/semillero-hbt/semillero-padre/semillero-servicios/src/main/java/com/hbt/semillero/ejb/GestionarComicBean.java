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

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO crearComic(ComicDTO nuevoComicDTO) throws ComicException {
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
		return nuevoComicDTO;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO modificarComic(ComicDTO comicModificarDTO) throws ComicException {
		logger.debug("Se ejecuta el metodo 'modificarComic'");

		try {
		
			if (comicModificarDTO.getId() == null) {
				throw new ComicException("COD-0011", "El id del objeto es requerido");
			}
			
			StringBuilder sbJPQL = new StringBuilder();
			sbJPQL.append("UPDATE Comic c ");
			sbJPQL.append("SET c.nombre = :nombre ");
			sbJPQL.append("WHERE c.id = :idComic");
			
			Query query = em.createQuery(sbJPQL.toString());
			query.setParameter("nombre", comicModificarDTO.getNombre());
			query.setParameter("idComic", Long.parseLong(comicModificarDTO.getId()));
			query.executeUpdate();
			
			logger.debug("Termina el metodo 'modificarComic'");
			return convertirComicToComicDTO(em.find(Comic.class, Long.parseLong(comicModificarDTO.getId())));			

		} catch (Exception e) {
			logger.error("Se produjo un error al actualizar el comic: " + e);
			throw new ComicException("COD-0002", "Error al actualizar comic", e);
		}
	}

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
			
			StringBuilder sbJPQL = new StringBuilder();
			sbJPQL.append("DELETE FROM Comic c ");
			sbJPQL.append("WHERE c.id = :idComic");			

			Query query = em.createQuery(sbJPQL.toString());
			query.setParameter("idComic", idComic);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("Error al eliminar comic " + e);
			throw new ComicException("COD-0003", "Error al ejecutar la eliminación del Comic", e);
		}

		logger.debug("Finaliza el metodo 'eliminarComic'");
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() throws ComicException {
		logger.debug("Se ejecuta el metodo 'consultarComics'");

		List<ComicDTO> listComicsDTO = null;

		try {

			String jpql = "select c from Comic c";
			Query query = em.createQuery(jpql);
			List<Comic> resultados = query.getResultList();			

			listComicsDTO = new ArrayList<ComicDTO>();
			for (Comic comic : resultados) {
				listComicsDTO.add(convertirComicToComicDTO(comic));
			}
			
		} catch (Exception e) {

			logger.error("Error al consultar comics " + e);
			throw new ComicException("COD-0004", "Error al obtener la lista de Comics: ", e);
			
		}

		logger.debug("Finaliza el metodo 'consultarComics'");
		return listComicsDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(Long idComic) throws ComicException {
		logger.debug("Se ejecuta el metodo 'consultarComic' por idComic");

		ComicDTO comicDTO = null;

		try {

			Comic comic = new Comic();

			StringBuilder sbJPQL = new StringBuilder();
			sbJPQL.append("SELECT c ");
			sbJPQL.append("FROM Comic c ");
			sbJPQL.append("WHERE c.id = :idComic");			

			Query query = em.createQuery(sbJPQL.toString());
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
