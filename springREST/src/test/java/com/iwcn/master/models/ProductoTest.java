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
    }
	
	
    @Test(expected = NullPointerException.class)
    public void testNullPointer() {
 
    	Producto prodnull = new Producto();
    	prodnull.getNombre().equals(nombre);
    	prodnull.getDescripcion().equals(descripcion);
    }
	
}
