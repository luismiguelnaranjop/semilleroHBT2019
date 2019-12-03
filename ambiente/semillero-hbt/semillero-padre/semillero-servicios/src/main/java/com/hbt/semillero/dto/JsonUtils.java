package com.hbt.semillero.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * <b>Descripción:<b> Clase que determina las utilidades para convertir DTO de
 * entrada a objetos JSON y viceversa
 * 
 * @author
 * 
 */
public class JsonUtils {

	/**
	 * Constructor de la clase.
	 */
	private JsonUtils() {
		throw new IllegalStateException("Utility class");

	}

	/**
	 * Método encargado de convertir los datos recibidos en JSON al DTO esperado
	 * 
	 * @param arg      Cadena que representa el objeto complejo JSON.
	 * @param claseDTO DTO
	 * 
	 * @return Instancia con los datos recibidos.
	 */
	public static <T> T valueOf(String arg, Class<T> claseDTO) {
		try {
			return new ObjectMapper().readValue(arg, claseDTO);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método encargado de convertir los datos recibidos en DTO al JSON esperado
	 * 
	 * @param dto DTO con sus datos
	 * 
	 * @return Json
	 */
	public static String toStringJson(Object dto) {
		try {
			return new ObjectMapper().writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
