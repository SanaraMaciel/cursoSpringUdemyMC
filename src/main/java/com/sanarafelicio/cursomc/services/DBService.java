package com.sanarafelicio.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanarafelicio.cursomc.domain.Categoria;
import com.sanarafelicio.cursomc.domain.Cidade;
import com.sanarafelicio.cursomc.domain.Cliente;
import com.sanarafelicio.cursomc.domain.Endereco;
import com.sanarafelicio.cursomc.domain.Estado;
import com.sanarafelicio.cursomc.domain.ItemPedido;
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
import com.sanarafelicio.cursomc.repositories.ItemPedidoRepository;
import com.sanarafelicio.cursomc.repositories.PagamentoRepository;
import com.sanarafelicio.cursomc.repositories.PedidoRepository;
import com.sanarafelicio.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	// criando as dependências @Autowired

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

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {
		// instanciando categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		// instanciando 3 produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV True color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		// Adicionando os produtos na lista de categoria
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		// Adicionando as categorias na lista de produtos
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p3.getCategorias().addAll(Arrays.asList(cat4));
		p3.getCategorias().addAll(Arrays.asList(cat5));
		p3.getCategorias().addAll(Arrays.asList(cat6));
		p3.getCategorias().addAll(Arrays.asList(cat6));
		p3.getCategorias().addAll(Arrays.asList(cat7));

		// salvando e criando uma lista automática
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		// Instanciação um estado
		Estado est2 = new Estado(null, "São Paulo");
		Estado est1 = new Estado(null, "Minas Gerais");

		// Instanciação as cidades e seus estados
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		// Fazendo o estado conhecer a cidade que pertence a ele
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		// salvando e criando uma lista automática
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		// Instanciação de cliente
		Cliente cli1 = new Cliente(null, "Maria Silva", "sanarafm27@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		// Colocando os telefones no cliente
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		// Instanciar o endereços para o cliente
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		// Fazendo o cliente conhecer seus endereços
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		// Salvando os objs criados no banco
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));

		// criando a data para salvar no banco
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// Instanciando o pedido
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		// Instanciando o pagamento do pedido
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		// Associando os clientes ao pedidos que foram pagos por eles
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		// Salvando os objetos no Banco
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));

		// instanciando itens de pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		// Associando cada pedido com o seu produto e itens
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		// Associando cada produto a seus pedidos e itens
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		// salvando os itens de pedido no bd
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));

	}

}
