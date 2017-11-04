package com.master.services;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.master.models.Usuario;

public interface UsuarioService {
	Usuario getUser(Long user);	
	Usuario saveUser(Usuario us);
	List<GrantedAuthority> getRoles();
}