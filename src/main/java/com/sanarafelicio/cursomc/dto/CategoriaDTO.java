package com.sanarafelicio.cursomc.dto;

import java.io.Serializable;

import com.sanarafelicio.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	//constructors
	public CategoriaDTO() {
		
	}
	
	//construtor para mapear a categoria e transformar a categoria em categoriaDTO para fazer as consultas
	public CategoriaDTO(Categoria obj) {
		id= obj.getId();
		nome = obj.getNome();
	}

	//getters and setters 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
