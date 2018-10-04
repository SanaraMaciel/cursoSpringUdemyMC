package com.sanarafelicio.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	//declarando uma dependência do objeto categoria repository para poder fazer os métodos
	//O @Autowired é uma depoendência q faz com q seja carregado automaticamente pelo spring 
	@Autowired
	private CategoriaRepository repo;
	
		//pesquisar por id
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
		throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
		+ ", Tipo: " + Categoria.class.getName());
		}
		return obj;
		
	}
	
	//insert
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
}
