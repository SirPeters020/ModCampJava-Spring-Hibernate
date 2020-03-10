package br.com.lifetime.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Classe de config de tolken JWT, implementação basica apenas para o spring não dar erro na hora de executar
 *
 */
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	public String generateToken(String username) {
		return Jwts.builder()	//gera o token
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())	//assinatura do token 
				.compact();
	}
	
}
