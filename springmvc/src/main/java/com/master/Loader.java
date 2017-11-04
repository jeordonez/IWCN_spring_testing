package com.master;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.master.models.Usuario;
import com.master.repositories.UsuarioRepositorio;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent>{

	private UsuarioRepositorio usrepo;
	
	@Autowired
	public void setUserRepositorio(UsuarioRepositorio userRepositorio) {
		this.usrepo = userRepositorio;
	}

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		usrepo.save(new Usuario("user","user", Arrays.asList(userRoles)));
		
		GrantedAuthority[] adminRoles = { new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ADMIN_USER") };
		usrepo.save(new Usuario("admin","admin", Arrays.asList(adminRoles)));

	}
}