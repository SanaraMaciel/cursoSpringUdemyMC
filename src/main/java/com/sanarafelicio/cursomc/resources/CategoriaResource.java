package com.sanarafelicio.cursomc.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.dto.CategoriaDTO;
import com.sanarafelicio.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	//pesquisar categoria por id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj = service.find(id);		
		return ResponseEntity.ok().body(obj);			
		}
	
	//inserir uma categoria
	@RequestMapping(method=RequestMethod.POST) //@RequestBidy faz o Json ser convertido para obj java
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		//pegando o id da categoria q fio inserido adicionar a url e converter pra uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	//Atualizando uma categoria
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id){
		obj.setId(id);
		obj= service.update(obj);
		return ResponseEntity.noContent().build();			
	}
	
	//Deletando uma Categoria
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}

	//listando todas as categorias
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
			List<Categoria> list = service.findAll();
			//transformando a lista de categoria para uma categoria dto
			List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);			
		}
	
	//listando todas as categorias com paginação
		@RequestMapping(value="/page",method=RequestMethod.GET)
		public ResponseEntity<Page<CategoriaDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
				
				Page<Categoria> list = service.findPage(page, linesPerPage, orderBy,direction);
				//transformando a Page de categoria para uma page de categoriaDto
				Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
				return ResponseEntity.ok().body(listDto);			
			}
	
	
}
