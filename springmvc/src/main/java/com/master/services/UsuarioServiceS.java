package com.master.services;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.master.models.Usuario;
import com.master.repositories.UsuarioRepositorio;

public class UsuarioServiceS implements UsuarioService {

	private UsuarioRepositorio usrepo;

	@Override
	public Usuario getUser(Long user) {
		return usrepo.findOne(user);
	}

	@Override
	public Usuario saveUser(Usuario us) {
		return usrepo.save(us);
	}

	@Override
	public List<GrantedAuthority> getRoles() {
		return null;
	}

}
