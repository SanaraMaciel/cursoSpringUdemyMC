package com.sanarafelicio.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Cliente;
import com.sanarafelicio.cursomc.repositories.ClienteRepository;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	//declarando uma dependência do objeto categoria repository para poder fazer os métodos
	//O @Autowired é uma dependência q faz com q seja carregado automaticamente pelo spring 
	@Autowired
	private ClienteRepository repo;
	
		
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
		throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
		+ ", Tipo: " + Cliente.class.getName());
		}
		return obj;
		
	}
}
