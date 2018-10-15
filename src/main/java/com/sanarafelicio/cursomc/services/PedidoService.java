package com.sanarafelicio.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanarafelicio.cursomc.domain.ItemPedido;
import com.sanarafelicio.cursomc.domain.PagamentoComBoleto;
import com.sanarafelicio.cursomc.domain.Pedido;
import com.sanarafelicio.cursomc.domain.enums.EstadoPagamento;
import com.sanarafelicio.cursomc.repositories.ClienteRepository;
import com.sanarafelicio.cursomc.repositories.ItemPedidoRepository;
import com.sanarafelicio.cursomc.repositories.PagamentoRepository;
import com.sanarafelicio.cursomc.repositories.PedidoRepository;
import com.sanarafelicio.cursomc.repositories.ProdutoRepository;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
@Transactional
public class PedidoService {

	//declarando uma dependência do objeto pedido repository para poder fazer os métodos
	//O @Autowired é uma depoendência q faz com q seja carregado automaticamente pelo spring 
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
		throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
		+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;		
	}
	
	@Transactional
	public Pedido insert(Pedido obj){
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto){
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		//salva o obj 
		obj = repo.save(obj);
		//salva o pagamento
		pagamentoRepository.save(obj.getPagamento());
		//salvar o item de pedido
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			//associar esse produto ao pedido no obj
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println(obj);
		return obj;
	}
	
}
