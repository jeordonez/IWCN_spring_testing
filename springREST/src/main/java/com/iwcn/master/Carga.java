package com.iwcn.master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.iwcn.master.models.Producto;
import com.iwcn.master.repositories.ProductoRepositorio;

@Component
public class Carga implements ApplicationListener<ContextRefreshedEvent> {
	private ProductoRepositorio producRepo;

	@Autowired
	public void setporductoRepositorio(ProductoRepositorio productoRepo) {
		this.producRepo = productoRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		Producto producto1 = new Producto();
		producto1.setNombre("manzana");
		producto1.setDescripcion("producto comestible");
		producto1.setPrecio(1);
		producRepo.save(producto1);
		
		Producto producto2 = new Producto();
		producto2.setNombre("pera");
		producto2.setDescripcion("producto comestible");
		producto2.setPrecio(2);
		producRepo.save(producto2);
	}
}
