package com.hbt.semillero.servicios;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hbt.semillero.pojo.GestionarComicPOJO;

/**
 * Test unitario del semillero HBT para el pojo GestionarComicPOJOTest
 * @author ccastano
 *
 */
public class GestionarComicPOJOTest 
{
	
	private GestionarComicPOJO gestionarComicPOJO = new GestionarComicPOJO();
    
	/**
	 * Metodo que permite validar si se creo correctamente un comic por defecto
	 */
    @Test
    public void crearComicDTOTest(){
    	//Se valida que la primera vez la lista de comics este vacia
    	Assert.assertNull(gestionarComicPOJO.getListaComics());
    	//Invocacion al metodo que peremite crear un comic por defecto
    	gestionarComicPOJO.crearComicDTO();
    	//Validacion de que la lista de comics no este vacia
    	Assert.assertNotNull(gestionarComicPOJO.getListaComics());
    	//Se valida que exista solo un elemento
    	Assert.assertTrue(gestionarComicPOJO.getListaComics().size()==1);
    	String nombreComicEsperado = "EL EJÉRCITO DEL CAPITÁN AMÉRICA";
    	String nombreComicActual = gestionarComicPOJO.getListaComics().get(0).getNombre();
    	//Se valida que se creo un elemento y que tenga el nombre esperado
    	Assert.assertEquals(nombreComicActual, nombreComicEsperado);
    }
    
    @Test
    public void modificarComicDTOTest() {
    	//TODO
    }
    
    @Test
    public void eliminarComicDTOTest() {
    	//TODO
    }
    
    @Test
    public void consultarComicDTOTest() {
    	//TODO
    }
}
