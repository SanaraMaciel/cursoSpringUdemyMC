package com.sanarafelicio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanarafelicio.cursomc.domain.Cidade;

@Repository													//objeto cidade e tipo do idntificador id que é Integer				
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
