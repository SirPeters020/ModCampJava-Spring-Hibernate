package br.com.lifetime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.lifetime.services.DBService;

/**
 * 
 * Classe de config do banco de dados no perfil "dev"
 *
 */
@Configuration
@Profile("dev")
public class DevDbConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	/**
	 * 
	 * Alteracao do BD por meio da classe DbService so ocorrera se ddl-auto em application-dev.properties for igual a "create"
	 * Altera��o no BD s� ocorrera se ddl-auto em application-dev.properties for igual a create
	 */
	@Bean
	public boolean instantiateDatabase() {
		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateDatabase(); //methodo para rodar instantiateDatabase e repovoar BD para teste
		return true;
	}
	
}
