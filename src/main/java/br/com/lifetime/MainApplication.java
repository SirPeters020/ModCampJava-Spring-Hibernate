package br.com.lifetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * Classe apenas para rodar a aplicacao;
 * Classe apena para rodar a aplica��o
 *
 */
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(MainApplication.class); //method de config para tomcat, tendo que apontar para si mesmo a classe
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		
	}
	
}
