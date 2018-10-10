package com.sanarafelicio.cursomc.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.domain.Produto;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;
import com.sanarafelicio.cursomc.repositories.ProdutoRepository;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	// declarando uma dependência do objeto produto repository para poder fazer os
	// métodos
	// O @Autowired é uma depoendência q faz com q seja carregado automaticamente
	// pelo spring
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName());
		}
		return obj;
	}
	
	//operação de search
	public Page<Produto> search(String nome,  List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}
	
}
