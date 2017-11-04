package com.iwcn.master.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.iwcn.master.models.Producto;
import com.iwcn.master.services.ProductoService;

@RestController
public class RestControllers {

	@Autowired
	private ProductoService proSer;
	
    @RequestMapping(value = "/server/producto/{codigo}", method = RequestMethod.GET)
    public Producto getProducto(@PathVariable("codigo") int codigo) {
        return proSer.getproductoid(codigo);
    }
      
    @RequestMapping(value = "/server/productos", method = RequestMethod.GET)
    public Iterable<Producto> lista() {
        return proSer.listaproductos();
    }
	
    @RequestMapping(value = "/server/agregar", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addProducto(@RequestBody Producto producto) {
    	Producto newprod = new Producto();
    	newprod = producto;
        proSer.guardarproducto(newprod);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/server/borrar/{codigo}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> borrarProducto(@PathVariable("codigo") int codigo) {
    	proSer.borrarproducto(codigo);
    	return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/server/actualizar/{codigo}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updateProducto(@RequestBody Producto producto) {
    	proSer.guardarproducto(producto);
    	return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
