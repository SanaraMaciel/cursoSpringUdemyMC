package com.sanarafelicio.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.sanarafelicio.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Date datavencimento;
	private Date dataPagamento;
	
	//constructors
	public PagamentoComBoleto() {
		
	}

	//gerar este construtor como : Source- generate constructor from superclass adicionando os atributos locais
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.setDataPagamento(dataPagamento);
		this.setDatavencimento(dataVencimento);
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDatavencimento() {
		return datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}
	
	
	
	
	

}
