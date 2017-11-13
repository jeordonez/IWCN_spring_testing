package com.iwcn.master.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductoTest {
	
	private static final int codigo = 1;
	private static final String nombre = "Manzana";
	private static final String descripcion = "producto comestible";
	private static final int precio = 3;
	
	private Producto prod;
	private Producto prod1;
	private Producto prodC1;
	private Producto prodC2;
	private Producto prodC3;
	
	@Before
	public void init() {
		 prod = new Producto();
		 prod.setCodigo(codigo);
		 prod.setNombre(nombre);
		 prod.setDescripcion(descripcion);
		 prod.setPrecio(precio);
	}
	
	@Test
    public void ClassProductoTest() {
        Assert.assertNotNull(prod);
        Assert.assertEquals(prod.getCodigo(), codigo);
        Assert.assertEquals(prod.getNombre(), nombre);
        Assert.assertEquals(prod.getDescripcion(), descripcion);
        Assert.assertEquals(prod.getPrecio(), precio);
        Assert.assertTrue(prod.getCodigo() == codigo);
        Assert.assertTrue(prod.getNombre() == nombre);
        Assert.assertTrue(prod.getDescripcion() == descripcion);
        Assert.assertTrue(prod.getPrecio() == precio);
        Assert.assertNotNull(prod);
        Assert.assertNull(prod1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointer() {
    	Producto prodnull = new Producto();
    	prodnull.getNombre().equals(nombre);
    	prodnull.getDescripcion().equals(descripcion);
       Assert.assertNull(prodnull.getCodigo());
    }
    
	@Test
    public void ConstProductoTest() {
		
		prodC1 = new Producto();
		prodC2 = new Producto("boligrafo","producto",1);
		prodC3 = new Producto(1,"cuaderno","producto",2);

		Assert.assertNotNull(prodC1);
        Assert.assertNotNull(prodC2);
        Assert.assertNotNull(prodC3);
        
        Assert.assertEquals(prodC2.getCodigo(), 0);
        Assert.assertEquals(prodC2.getNombre(), "boligrafo");
        Assert.assertEquals(prodC2.getDescripcion(), "producto");
        Assert.assertEquals(prodC2.getPrecio(), 1);
        
        Assert.assertTrue(prodC3.getCodigo() == 1);
        Assert.assertTrue(prodC3.getNombre() == "cuaderno");
        Assert.assertTrue(prodC3.getDescripcion() == "producto");
        Assert.assertTrue(prodC3.getPrecio() == 2);
        
        Assert.assertFalse(prodC2 == prodC3);
  
    }    
	
}
