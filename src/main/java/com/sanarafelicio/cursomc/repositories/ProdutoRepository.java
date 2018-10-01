package com.sanarafelicio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanarafelicio.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
