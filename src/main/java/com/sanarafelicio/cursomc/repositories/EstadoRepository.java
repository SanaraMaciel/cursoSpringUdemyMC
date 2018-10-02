package com.sanarafelicio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanarafelicio.cursomc.domain.Estado;

@Repository													//objeto estado e tipo do idntificador id que é Integer				
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
