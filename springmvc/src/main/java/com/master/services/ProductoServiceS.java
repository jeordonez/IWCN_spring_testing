package com.master.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.master.models.Producto;


@Service
public class ProductoServiceS implements  ProductoService{

	public static final String REST_SERVICE_URI = "http://localhost:8080/server";
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Producto> listaproductos() {
		
		 RestTemplate restTemplate = new RestTemplate();
		 List<Producto> listap = new ArrayList<Producto>();
		 listap = restTemplate.getForObject(REST_SERVICE_URI+"/productos/", List.class);
		return listap;
	}

	@Override
	public Producto getproductoid(Integer codigo) { 
		RestTemplate restTemplate = new RestTemplate();
        Producto prod = restTemplate.getForObject(REST_SERVICE_URI+"/producto/"+codigo, Producto.class);
		return prod;
	}

	@Override
	public Producto guardarproducto(Producto prod) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(REST_SERVICE_URI+"/agregar", prod, Producto.class);
        return prod;
	}

	@Override
	public void borrarproducto(Integer codigo) {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/borrar/"+codigo);
	}

}

