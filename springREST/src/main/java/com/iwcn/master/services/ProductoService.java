package com.iwcn.master.services;

import java.util.ArrayList;

import com.iwcn.master.models.Producto;

public interface ProductoService {
	ArrayList<Producto> listaproductos();	
	Producto getproductoid(Integer codigo);	
	void guardarproducto(Producto prod);	
	void borrarproducto(Integer codigo);
}