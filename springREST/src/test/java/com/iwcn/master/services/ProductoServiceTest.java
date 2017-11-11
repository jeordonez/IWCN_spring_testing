package com.iwcn.master.services;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

import com.iwcn.master.models.Producto;
import com.iwcn.master.repositories.ProductoRepositorio;

@RunWith(SpringRunner.class)
public class ProductoServiceTest {
	
	private Producto producto1;
	
	private ArrayList<Producto> Productos = new ArrayList<Producto> ();
	
	@Mock
	private ProductoRepositorio producRepo;
	
	@InjectMocks
	private ProductoServiceS proSer = new ProductoServiceS();
	
	@Before
	public void init() {
		producto1 = new Producto("manzana","producto comestible",1);
		Productos.add(producto1);
		when(producRepo.findAll()).thenReturn(Productos);
		when(producRepo.findOne(anyInt())).thenReturn(producto1);
	}
	
	//el test falla si el metodo tarda más de 200 milisegundos
	@Test(timeout=200)
	public void listaproductosTests() throws ServiceException{
		ArrayList<Producto> productos = proSer.listaproductos();
		assertEquals(productos.size(), 1);
		verify(producRepo).findAll();
	}
	
	//IndexOutOfBoundsException -> indice fuera de rango
	//si el indice fuera del rango el test funciona
	//si el indice esta dentro del rango el test falla
    @Test(expected = IndexOutOfBoundsException.class)
    public void indiceListaTests() {
		ArrayList<Producto> productos = proSer.listaproductos();
    	//productos.get(0);
    	productos.get(9);	
    }
    
    @Test(timeout=50)
    public void getproductoidTests() throws ServiceException{
    	Producto p1 = proSer.getproductoid(1);
    	assertEquals(p1.getNombre(), "manzana");
    	assertEquals(p1.getDescripcion(), "producto comestible");
    	assertEquals(p1.getPrecio(), 1);
    	verify(producRepo).findOne(1);   	
    }
    
    @Test(timeout=50)
	public void guardarproductoTests() throws ServiceException{
        assertEquals(proSer.listaproductos().size(), 1);
        Producto pro = new Producto("galletas", "comestible", 2);
        proSer.guardarproducto(pro);
        Productos.add(pro);
        assertEquals(proSer.listaproductos().size(), 2);
        verify(producRepo).save(pro);
	}    
	
    @Test(timeout=50)
	public void EditarProductoTests() throws ServiceException{ 
        assertEquals(proSer.listaproductos().size(), 1);
        Producto prod = proSer.getproductoid(1);
        prod.setNombre("Pera");
        prod.setPrecio(10);
        proSer.guardarproducto(prod);
        assertEquals(proSer.listaproductos().size(), 1);
        verify(producRepo).save(prod);  
	}

    @Test(timeout=50)
	public void borrarproductoTests() throws ServiceException{
		assertEquals(proSer.listaproductos().size(), 1);
		Producto pro = proSer.getproductoid(1);
		proSer.borrarproducto(pro.getCodigo());
		Productos.remove(pro);
		assertEquals(proSer.listaproductos().size(), 0);
		verify(producRepo).delete(proSer.getproductoid(1));
	}
    
    
    @Test(expected = NullPointerException.class)
    public void testAddProducto(){
    	ProductoServiceS proSernull = new ProductoServiceS();
    	proSernull.guardarproducto(new Producto());
    }
 
    @Test(expected = NullPointerException.class)
    public void testDeleteProducto(){
    	ProductoServiceS proSernull = new ProductoServiceS();
    	proSernull.borrarproducto(0);
    }
	
}
