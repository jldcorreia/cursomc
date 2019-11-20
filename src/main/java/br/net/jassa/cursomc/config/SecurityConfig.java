package br.net.jassa.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	/* define um vetor de strings que vai ter os caminhos que vão estar liberados */
	private static final String[] PUBLIC_MATCHERS = {
		"/h2-console/**"
	};

	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**"
		};	
	
	/* neste método configura que recursos serão liberados ou bloqueados */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// verificação para liberar o banco de dados h2 
		// busca os profiles ativos, se tiver no test desabilita
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers()
			    .frameOptions().disable();
		}
		
		
		http.cors() // chama o corConfigurationSource abaixo para liberar requisições de qualquer origem
		    .and().csrf().disable();  // configura o backend para desabilitar a proteção de ataques CSRF baseado no armazenamento da autenticação e sessão 
		http.authorizeRequests()
		    .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		    .antMatchers(PUBLIC_MATCHERS).permitAll()
		    .anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // assegura que o nosso backend não vai criar sessão do usuário
	}
	
	/* libera acesso básico de multiplas fontes para todos os caminhos com as configurações basicas,
	 * assim é liberado o acesso ao sistema não importa a origem, se não tiver o método abaixo aí não é liberado */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
