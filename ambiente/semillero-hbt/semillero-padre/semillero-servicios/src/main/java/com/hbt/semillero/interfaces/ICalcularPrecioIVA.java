package com.hbt.semillero.interfaces;

import java.math.BigDecimal;

import javax.ejb.Local;

import com.hbt.semillero.entidad.TematicaEnum;

@Local
public interface ICalcularPrecioIVA {
	
	/**
	 * Firma de un metodo que calcule el precio total de un comic incluyendo
	 * el Iva
	 * 
	 * @param iva contiene el porcentaje del iva que se debe pagar
	 * @param precio precio base del comic
	 * @return
	 */
	public BigDecimal CalcularPrecioTotal(double iva, BigDecimal precio);
	

	/**
	 * Firma de un metodo que retorne el porcentaje a pagar en IVA por la compra
	 * de un Comic 
	 * 
	 * @param tematica del comic
	 */
	public double PorcentajeIVA(TematicaEnum tematica);

}
