package br.net.jassa.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.net.jassa.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
