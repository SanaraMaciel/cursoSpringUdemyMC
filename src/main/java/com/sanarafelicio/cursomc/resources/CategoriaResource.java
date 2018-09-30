package com.sanarafelicio.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanarafelicio.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		//criando 2 categorias
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		//criando uma lista de Categoria
		List<Categoria> categoriaList = new ArrayList<>();
		categoriaList.add(cat1);
		categoriaList.add(cat2);
		
		return categoriaList;
	}

}
