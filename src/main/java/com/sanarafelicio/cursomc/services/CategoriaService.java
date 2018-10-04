package com.sanarafelicio.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.dto.CategoriaDTO;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;
import com.sanarafelicio.cursomc.services.exceptions.DataIntegrityException;
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
	
	//update
	public Categoria update(Categoria obj) {
	//chamada ao método find para ver se tem a categoria se ñ tiver já lança a excessão 
		find(obj.getId());
		return repo.save(obj);
	}
	
	//delete
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos"); 
		}
	}
	
	//Listar todas as categorias
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	//Adicionando Paginação 
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);		
	}
	
	//método auxiliar para converter de CategoriaDTO para Categoria instancia uma categoria a partir de um DTO
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
}
