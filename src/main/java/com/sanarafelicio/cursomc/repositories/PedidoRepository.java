package com.sanarafelicio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanarafelicio.cursomc.domain.Pedido;

@Repository													//objeto pedido e tipo do idntificador id que é Integer				
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
