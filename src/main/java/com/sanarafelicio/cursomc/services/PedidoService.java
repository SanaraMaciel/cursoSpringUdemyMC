package com.sanarafelicio.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Pedido;
import com.sanarafelicio.cursomc.repositories.PedidoRepository;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	//declarando uma dependência do objeto pedido repository para poder fazer os métodos
	//O @Autowired é uma depoendência q faz com q seja carregado automaticamente pelo spring 
	@Autowired
	private PedidoRepository repo;
	
		
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
		throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
		+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
		
	}
}
