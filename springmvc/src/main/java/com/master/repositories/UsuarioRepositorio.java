package com.master.repositories;

import org.springframework.data.repository.CrudRepository;

import com.master.models.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
	Usuario findByUser(String usuario);
}

