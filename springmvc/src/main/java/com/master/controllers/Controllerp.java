package com.master.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.master.models.Producto;
import com.master.services.ProductoService;

@Controller
public class Controllerp {
	
	private ProductoService proSer;

	@Autowired
	public void setProductRepository(ProductoService productService) {
        this.proSer = productService;
	}
	
	@RequestMapping("/")
	public ModelAndView root() {	
		ModelAndView model;
		
		if (isAuthenticathed()) {
			model = new ModelAndView("home");		
		}else {
			model = new ModelAndView("index"); 		
		}
		return model;
	}
	
	private boolean isAuthenticathed() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.filter(r-> !r.getAuthority().equals("ROLE_ANONYMOUS")).collect(Collectors.toList()).size() > 0;
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/home")
	public ModelAndView home() {				
		return new ModelAndView("home");
	}

	@RequestMapping("/login")
	public ModelAndView login() {				
		return new ModelAndView("login");
	}
	
	@RequestMapping("/denied")
	public ModelAndView denied() {				
		return new ModelAndView("denied");
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/productos", method = RequestMethod.GET)
    public String lista(Model model){
        model.addAttribute("productos", proSer.listaproductos());
        return "lista";
    }
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("producto/{codigo}")
    public String mostrarproducto(@PathVariable Integer codigo, Model model){
        model.addAttribute("producto", proSer.getproductoid(codigo));
        return "verproductoingr";
    }
	
	@Secured({"ROLE_ADMIN"})
    @RequestMapping("producto/nuevo")
    public String productonuevo(Model model){
        model.addAttribute("producto", new Producto());
        return "nuevo";
    }

	@Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "producto", method = RequestMethod.POST)
    public String saveProduct(Producto prod){
    	proSer.guardarproducto(prod);
    	return "exito";
    }
	
	@Secured({"ROLE_ADMIN"})
    @RequestMapping("producto/borrar/{codigo}")
    public String borrar(@PathVariable Integer codigo){
    	proSer.borrarproducto(codigo);
        return "redirect:/productos";
    }
    
	@Secured({"ROLE_ADMIN"})
    @RequestMapping("producto/editar/{codigo}")
    public String editar(@PathVariable Integer codigo, Model model){
        model.addAttribute("producto", proSer.getproductoid(codigo));
        return "editar";
    }
	
	
	
	
	
	
	
//----------------------------------------------------------------------------------	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Autowired
	private productoCarga plista;
	
	@RequestMapping("/greeting")
    public ModelAndView pagInic(HttpSession sesion) {	
		sesion.setAttribute("productos", plista.getListProductos());
        return new ModelAndView("index");   
    }
	
	@RequestMapping("/lista.html")
    public ModelAndView iteracion(HttpSession sesion) {	
		Object slista = sesion.getAttribute("productos");		
		return new ModelAndView("lista").addObject("sproductos", slista);
    }
	
	@RequestMapping(value="/nuevo.html")
    public ModelAndView insert(HttpSession sesion) {
		Object slista = sesion.getAttribute("productos");
        return new ModelAndView("nuevo").addObject("sproductos", slista);  
    }
	
	@RequestMapping(value="/guardar.html", method = RequestMethod.GET)
		public ModelAndView insertar(HttpSession sesion, @RequestParam("codigo") int codigo, 
				@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
				@RequestParam("precio") int precio) {
		
		producto nuevoprod = new producto();
		nuevoprod.setCodigo(codigo);
		nuevoprod.setNombre(nombre);
		nuevoprod.setDescripcion(descripcion);
		nuevoprod.setPrecio(precio);
		
		List<producto> slista = (List<producto>) sesion.getAttribute("productos");
		slista.add(nuevoprod);
		sesion.setAttribute("productos", slista);	
        return new ModelAndView("guardar");   
    }

	@RequestMapping(value = "/borrar.html")
    public ModelAndView delete(HttpSession sesion) {
		Object slista = sesion.getAttribute("productos");
        return new ModelAndView("borrar").addObject("sproductos", slista);  
    }

	
	@RequestMapping("/greeting")
    public ModelAndView pagInic(HttpSession sesion) {	
		sesion.setAttribute("productos", plista.getListProductos());
        return new ModelAndView("index");   
    }
	
	@RequestMapping(value = "/lista.html")
    public ModelAndView iteracion(HttpSession sesion) {	
		sesion.getAttribute("productos");
		return new ModelAndView("lista");
    }
	
	@RequestMapping(value = "/nuevo.html")
    public ModelAndView insert() {		
        return new ModelAndView("nuevo");   
    }	

	/*@RequestMapping(value="/iteracion")
	    public ModelAndView processPerson(@ModelAttribute productolista plista) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("lista");
	        
	        modelAndView.addObject("productos", plista);
	        return modelAndView;
	    }

   /* @RequestMapping("/iteracion")
    public ModelAndView iteracion() {		
        return new ModelAndView("lista").addObject("productos", plista.getListProductos());   
    }
    */
}
