package br.com.lifetime.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lifetime.services.DBService;

/**
 * 
 * Classe de config do banco de dados no perfil "test"
 *
 */
@Configuration
@Profile("test")
public class TestDbConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateDatabase();
		return true;
	}
	
}
