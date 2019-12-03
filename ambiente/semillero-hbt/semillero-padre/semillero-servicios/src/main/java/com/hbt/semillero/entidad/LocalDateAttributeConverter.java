/**
 * LocalDateAttributeConverter.java
 */
package com.hbt.semillero.entidad;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * <b>Descripción:<b> Clase que determina la manipulacion de las fechas <b>Caso
 * de Uso:<b>
 * 
 * @author ccastano
 * @version
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate locDate) {
		return locDate == null ? null : Date.valueOf(locDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		return sqlDate == null ? null : sqlDate.toLocalDate();
	}
}