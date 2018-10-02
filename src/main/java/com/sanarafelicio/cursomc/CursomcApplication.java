package com.sanarafelicio.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.domain.Cidade;
import com.sanarafelicio.cursomc.domain.Estado;
import com.sanarafelicio.cursomc.domain.Produto;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;
import com.sanarafelicio.cursomc.repositories.CidadeRepository;
import com.sanarafelicio.cursomc.repositories.EstadoRepository;
import com.sanarafelicio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	//criando as dependências @Autowired

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		
		//instanciando 3 produtos
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		
		//Adicionando os produtos na lista de categoria
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//Adicionando as categorias na lista de produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
					
		
		//salvando e criando uma lista automática
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		//Adicionando um estado
		Estado est2 = new Estado(null,"São Paulo");
		Estado est1 = new Estado(null,"Minas Gerais");

		//Adicionando as cidades e seus estados
		Cidade c1 = new Cidade (null,"Uberlandia",est1);
		Cidade c2 = new Cidade (null,"São Paulo",est2);	
		Cidade c3 = new Cidade (null,"Campinas",est2);
				
		//Fazendo o estado conhecer a cidade que pertence a ele
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		//salvando e criando uma lista automática		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
	}
	
	
}
