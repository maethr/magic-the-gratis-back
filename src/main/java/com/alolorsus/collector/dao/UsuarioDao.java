package com.alolorsus.collector.dao;

import org.springframework.data.repository.CrudRepository;

import com.alolorsus.collector.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, String>  {

}
