package com.hbt.semillero.common;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * <b>Descripción:<b> Clase que determina la identificacion la ruta de la aplicación
 * que sirve como URI base para todos los URI de recursos proporcionados por
 * javax.ws.rs.Path 
 * @version
 */
@ApplicationPath("/rest")
public class JaxRSActivator extends Application {

}
