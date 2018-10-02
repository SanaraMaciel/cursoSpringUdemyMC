package com.sanarafelicio.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.domain.Cidade;
import com.sanarafelicio.cursomc.domain.Cliente;
import com.sanarafelicio.cursomc.domain.Endereco;
import com.sanarafelicio.cursomc.domain.Estado;
import com.sanarafelicio.cursomc.domain.Produto;
import com.sanarafelicio.cursomc.domain.enums.TipoCliente;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;
import com.sanarafelicio.cursomc.repositories.CidadeRepository;
import com.sanarafelicio.cursomc.repositories.ClienteRepository;
import com.sanarafelicio.cursomc.repositories.EnderecoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		//Instanciação um estado
		Estado est2 = new Estado(null,"São Paulo");
		Estado est1 = new Estado(null,"Minas Gerais");

		//Instanciação as cidades e seus estados
		Cidade c1 = new Cidade (null,"Uberlandia",est1);
		Cidade c2 = new Cidade (null,"São Paulo",est2);	
		Cidade c3 = new Cidade (null,"Campinas",est2);
				
		//Fazendo o estado conhecer a cidade que pertence a ele
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		//salvando e criando uma lista automática		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		//Instanciação de cliente
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		
		//Colocando os telefones no cliente
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		//Instanciar o endereços para o cliente
		Endereco e1 = new Endereco(null, "Rua Flores","300","Apto 303","Jardim","38220834",cli1,c1);
		Endereco e2 = new Endereco(null, "Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
		
		//Fazendo o cliente conhecer seus endereços
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//Salvando os objs criados no banco
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		
		
	}
	
	
}
