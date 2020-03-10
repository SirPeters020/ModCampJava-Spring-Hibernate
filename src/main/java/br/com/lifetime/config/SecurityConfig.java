package br.com.lifetime.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.lifetime.resources.SugarConnection;

//import br.com.lifetime.resources.Login;

/**
 * 
 * Classe contendo as configuracao de seguranca web
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	@Autowired
	private SugarConnection login;
	
//	Boolean obj = SugarConnection.SugarLogin(usuario, senha);

	
	// Array com endpoint liberado sem passare pelo login
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/aai/**",
			"/subcampanha/**",
			"/cliente/**",
			"/controlecampanha/**",
			"/equipe/**",
			"/estrategia/**",
			"/campanha/**",
			"/login/**",
			"/DataTableDTO/**",
			"/cadastrocampanha/**",
			"/cadastroestrategia/**",
			"/loginAuth/**"
	};
	
	// Array com endpoint liberado sem passar pelo login, porem apenas com o GET liberado
	private static final String[] PUBLIC_MATCHERS_GET = {  
			"/h2-console/**",
			"/aai/**",
			"/subcampanha/**",
			"/cliente/**",
			"/controlecampanha/**",
			"/equipe/**",
			"/estrategia/**",
			"/campanha/**"
	};
	
	/**
	 * Metodo para liberar endpoints e definindo pagina de login
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors().and().csrf().disable();
		http.
			authorizeRequests()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
					.permitAll()
				.antMatchers(PUBLIC_MATCHERS)
			 		.permitAll()					
				.antMatchers("/tela-datatable")		//comentar/remover para login
					.permitAll()					//idem
					.antMatchers("/cadastrocampanha")		//comentar/remover para login
					.permitAll()
					.antMatchers("/cadastroestrategia")		//comentar/remover para login
					.permitAll()
					.antMatchers("/editcontrolecampanha")		//comentar/remover para login
					.permitAll()
					.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login")
					.loginProcessingUrl("loginAuth") // pagina para processar login apos post
					.defaultSuccessUrl("/tela-datatable")
					.loginProcessingUrl("/tela-datatable") //idem
					.permitAll();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/**
	 * Methodo para liberar acesso a pastas de config
	 */
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	            .ignoring()
	            .antMatchers("/resources/**", "/static/**","/webjars/**", "/css/**", "/js/**", "/img/**");
	    }
	
	/**
	 * 
	 * Metodo para permitir acesso aos endpoins por multiplas fontes, com as config basicas
	 * permitindo requisicoes de multiplas fontes
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
}
