package com.iwcn.master.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iwcn.master.models.Producto;

public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {
}
