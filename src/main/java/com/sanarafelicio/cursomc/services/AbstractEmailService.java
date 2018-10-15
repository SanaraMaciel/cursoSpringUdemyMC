package com.sanarafelicio.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.sanarafelicio.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	//pegando o valor q esta em aplication properties do destinatário de email
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj){
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
	
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado código"+ obj.getId());;
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
			return sm;
	}

}
