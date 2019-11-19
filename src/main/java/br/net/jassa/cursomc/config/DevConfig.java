package br.net.jassa.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.net.jassa.cursomc.services.DBService;
import br.net.jassa.cursomc.services.EmailService;
import br.net.jassa.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	/* essa forma busca o parametro no arquivo application.properties e associa ao valor da variavel strategy */
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws Exception {
		/* se a estrategia é de criar o BDs roda os comandos sql */
		if (strategy.equals("create")) {
			dbService.instantiateTestDatabase();
		}
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}	
	
}
