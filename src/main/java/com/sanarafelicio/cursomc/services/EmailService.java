package com.sanarafelicio.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.sanarafelicio.cursomc.domain.Pedido;

public interface EmailService {
	
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg );

}
