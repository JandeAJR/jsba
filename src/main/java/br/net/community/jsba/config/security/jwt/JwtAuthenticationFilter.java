package br.net.community.jsba.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe: JwtAuthenticationFilter
 *
 * <p>Classe reponsável por implementar o filtro de autenticação JWT que intercepta requisições HTTP para validar tokens JWT.<br>
 * Realiza a extração do token de cabeçalho Authorization, faz a validação do token 
 * e autentica o usuário no contexto de segurança do Spring</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService; // Serviço para manipulação de tokens JWT
    private final UserDetailsService userDetailsService; // Serviço para carregar detalhes do usuário (faz parte do Spring Security)

    public JwtAuthenticationFilter(
    		JwtService jwtService, 
    		UserDetailsService userDetailsService) {
    	
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal (
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

    	// Este método extrai o token JWT do cabeçalho Authorization da requisição HTTP,
    	// valida o token, extrai o nome de usuário e autentica o usuário no contexto de segurança do Spring.
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " do início do cabeçalho
            String username = jwtService.extractUsername(token); // Extrai o nome de usuário do token

            // Carrega os detalhes do usuário usando o UserDetailsService
            UserDetails user =
                    userDetailsService.loadUserByUsername(username);

            // Cria um objeto de autenticação com os detalhes do usuário
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user, // principal
                            null, // credentials
                            user.getAuthorities() // authorities
                    );

            // Define o objeto de autenticação no contexto de segurança do Spring
            SecurityContextHolder
                    .getContext() // Obtém o contexto de segurança atual
                    .setAuthentication(authentication); // Define a autenticação
        }

        // Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
    	// Método para determinar se o filtro deve ser aplicado ou não.
        return request.getServletPath().startsWith("/auth");
    }
}
