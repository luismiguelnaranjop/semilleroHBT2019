package com.hbt.semillero.dto;

public class ConsultarTotalPersonajesPorComicDTO {
	
	private Long total;
	private String comic;
	
	
	public ConsultarTotalPersonajesPorComicDTO() {
		
	}

	/**
	 * 
	 * @param total
	 * @param comic
	 */
	public ConsultarTotalPersonajesPorComicDTO(Long total, String comic) {
		super();
		this.total = total;
		this.comic = comic;
	}


	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}


	/**
	 * @return the comic
	 */
	public String getComic() {
		return comic;
	}


	/**
	 * @param comic the comic to set
	 */
	public void setComic(String comic) {
		this.comic = comic;
	}
	

}
