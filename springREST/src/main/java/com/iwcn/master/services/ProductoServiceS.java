package com.iwcn.master.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwcn.master.models.Producto;
import com.iwcn.master.repositories.ProductoRepositorio;

@Service
public class ProductoServiceS implements ProductoService {
	
	private ProductoRepositorio prodRepo;
	
	@Autowired
	public void setporductoRepositorio(ProductoRepositorio productoRepo) {
		this.prodRepo = productoRepo;
	}

	@Override
	public ArrayList<Producto> listaproductos() {
		ArrayList<Producto> productos = new ArrayList<>();
		for (Producto p : prodRepo.findAll()) {
			productos.add(p);
		}
		return productos;
	}

	@Override
	public Producto getproductoid(Integer codigo) {
		return prodRepo.findOne(codigo);
	}

	@Override
	public void guardarproducto(Producto prod) {
		prodRepo.save(prod);
	}

	@Override
	public void borrarproducto(Integer codigo) {
		prodRepo.delete(codigo);
	}
	
    /*@Override
    public String toString() {
        return "lista [productos=" + prodRepo + "]";
    }*/

}
