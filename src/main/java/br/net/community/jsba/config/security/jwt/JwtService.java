package br.net.community.jsba.config.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.net.community.jsba.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;

/**
 * Classe: JwtService
 *
 * <p>Classe reponsável por implementar o serviço JWT que gera e valida tokens JWT.<br>
 * Realiza a criação dos tokens JWT assinados com uma chave secreta e extrai informações dos tokens.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Service
public class JwtService {
	@Value("${jwt.key}") // Chave configurada no application.yml
    private String key;

    @Value("${jwt.expiration}") // Expiração em minutos configurada no application.yml
    private long jwtExpirationMinutes;

    private SecretKey jwtKey;

    @PostConstruct
    private void init() {
    	// Método chamado após a injeção de dependências para inicializar o jwtKey
    	this.jwtKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)); // Decodifica a chave secreta em Base64 e cria a chave HMAC SHA.
    	                                                              // HMAC SHA é um algoritmo de assinatura usado para garantir 
    	                                                             // a integridade e autenticidade dos tokens JWT.
    }

    public String generateToken(User user) {
    	// Método para gerar um token JWT para o usuário fornecido
        long expirationMillis = jwtExpirationMinutes * 60 * 1000; // Converte minutos para milissegundos

        // Cria o token JWT com o nome de usuário, data de emissão e data de expiração
        return Jwts.builder()
                .subject(user.getUsername()) // Define o nome de usuário (subject) do token
                .issuedAt(new Date()) // Define a data de emissão do token
                .expiration(new Date(System.currentTimeMillis() + expirationMillis)) // Define a data de expiração do token (em milissegundos)
                .signWith(jwtKey) // Assina o token com a chave secreta
                .compact(); // Constrói o token JWT como uma string compacta
    }

    public String extractUsername(String token) {
    	// Metodo para extrair o nome de usuário do token JWT
        Claims claims = Jwts.parser() // Cria um parser JWT
                .verifyWith(jwtKey) // Configura a chave secreta para verificar a assinatura do token
                .build() // Constrói o parser JWT, serve para validar e analisar tokens JWT
                .parseSignedClaims(token) // Analisa o token JWT assinado
                .getPayload(); // Obtém as reivindicações (claims) do token

        // Retorna o nome de usuário (subject) das reivindicações (claims)
        return claims.getSubject();
    }
}
