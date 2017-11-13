package com.iwcn.master.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.iwcn.master.models.Producto;
import com.iwcn.master.services.ProductoServiceS;

@RunWith(SpringRunner.class)
public class RestControllersTest {
    
	private Producto producto1;
	private Producto producto2;
	private Producto producto3;
	private Producto producto4;
	private Producto producto5;
	private ArrayList<Producto> Productos = new ArrayList<Producto> ();
		
	MockMvc mockMvc;
	
    @Mock
    private ProductoServiceS proSerS;

    
    @InjectMocks
    private RestControllers rescontr = new RestControllers();
    
    @Before
    public void setUp() throws Exception {
    	mockMvc = standaloneSetup(rescontr).build(); 
		producto1 = new Producto(1,"manzana","producto comestible",1);
		Productos.add(producto1);
		producto2 = new Producto(2,"pera","producto comestible",3);
		Productos.add(producto2);
		when(rescontr.getProducto(1)).thenReturn(producto1);
		when(rescontr.getProducto(2)).thenReturn(producto2);
		when(rescontr.lista()).thenReturn(Productos);
	    
    }									
    
    @Test
    public void getProductoControllerTest() throws Exception {
        mockMvc.perform(get("/server/producto/{codigo}",1))
        .andExpect(status().isOk())
        .andExpect(content().string("{\"codigo\":1,\"nombre\":\"manzana\",\"descripcion\":\"producto comestible\",\"precio\":1}"));
    	verify(proSerS).getproductoid(1);    	
    }
    
    @Test
    public void listaControllerTest() throws Exception {
        mockMvc.perform(get("/server/productos"))
        .andExpect(status().isOk()) //200
        .andExpect(content().string("[{\"codigo\":1,\"nombre\":\"manzana\",\"descripcion\":\"producto comestible\",\"precio\":1},{\"codigo\":2,\"nombre\":\"pera\",\"descripcion\":\"producto comestible\",\"precio\":3}]"));
    	verify(proSerS).listaproductos();    	
    }
    
    @Test
    public void borrarProductoControllerTest() throws Exception {
    	producto3 = new Producto(3,"uva","producto comestible",1);
    	when(proSerS.getproductoid(3)).thenReturn(producto3);
    	doNothing().when(proSerS).borrarproducto(3);
        mockMvc.perform(delete("/server/borrar/{codigo}",3))
        .andExpect(status().isCreated()); //201
        verify(proSerS, times(1)).borrarproducto(3);;
        verifyNoMoreInteractions(proSerS);	
    }
      
    @Test   //test de error  no guarda producto
    public void addProductoControllerTest() throws Exception {
    	producto4 = new Producto(4,"cigarrillos","producto consumible",5);
    	when(proSerS.getproductoid(4)).thenReturn(producto4);
    	doNothing().when(proSerS).guardarproducto(producto4);
        this.mockMvc.perform(post("/server/agregar",producto4))
        .andExpect(status().is4xxClientError());
        verify(proSerS, times(0)).guardarproducto(producto4);;
        verifyNoMoreInteractions(proSerS);	
    }
    
    @Test   //test de error   no guarda producto
    public void updateControllerTest() throws Exception {
    	producto5 = new Producto(5,"cigarrillos","producto consumible",5);
    	when(proSerS.getproductoid(5)).thenReturn(producto5);
    	doNothing().when(proSerS).guardarproducto(producto5);
        this.mockMvc.perform(put("/server/actualizar/{codigo}",5))
        .andExpect(status().is4xxClientError());
        verify(proSerS, times(0)).guardarproducto(producto5);;
        verifyNoMoreInteractions(proSerS);	
    }    

}
