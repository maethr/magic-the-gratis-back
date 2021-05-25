package com.alolorsus.collector.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alolorsus.collector.entity.Usuario;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, String>  {

}
