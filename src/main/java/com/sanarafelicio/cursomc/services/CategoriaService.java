package com.sanarafelicio.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	//declarando uma dependência do objeto categoria repository para poder fazer os métodos
	//O @Autowired é uma depoendência q faz com q seja carregado automaticamente pelo spring 
	@Autowired
	private CategoriaRepository repo;
	
		
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		return obj;
		
	}
}
