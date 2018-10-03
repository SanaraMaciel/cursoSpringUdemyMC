package com.sanarafelicio.cursomc;

import java.text.SimpleDateFormat;
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
import com.sanarafelicio.cursomc.domain.Pagamento;
import com.sanarafelicio.cursomc.domain.PagamentoComBoleto;
import com.sanarafelicio.cursomc.domain.PagamentoComCartao;
import com.sanarafelicio.cursomc.domain.Pedido;
import com.sanarafelicio.cursomc.domain.Produto;
import com.sanarafelicio.cursomc.domain.enums.EstadoPagamento;
import com.sanarafelicio.cursomc.domain.enums.TipoCliente;
import com.sanarafelicio.cursomc.repositories.CategoriaRepository;
import com.sanarafelicio.cursomc.repositories.CidadeRepository;
import com.sanarafelicio.cursomc.repositories.ClienteRepository;
import com.sanarafelicio.cursomc.repositories.EnderecoRepository;
import com.sanarafelicio.cursomc.repositories.EstadoRepository;
import com.sanarafelicio.cursomc.repositories.PagamentoRepository;
import com.sanarafelicio.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		//criando a data para salvar no banco
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		//Instanciando o pedido
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1, e1 );
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35") , cli1, e2);
		
		//Instanciando o pagamento do pedido
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		//Associando os clientes ao pedidos que foram pagos por eles		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		//Salvando os objetos no Banco
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
		
	}
	
	
}
