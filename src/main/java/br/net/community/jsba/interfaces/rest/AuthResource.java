package br.net.community.jsba.interfaces.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.community.jsba.config.security.jwt.JwtService;
import br.net.community.jsba.domain.model.User;
import br.net.community.jsba.dto.request.AuthDTO;

/**
 * Classe: AuthResource
 *
 * <p>Recurso (controller) REST para autenticação de usuários com JWT.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@RestController
@RequestMapping("/auth")  
public class AuthResource {
	private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthResource(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login") // POST /api/jsba/auth/login
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthDTO request) {
        // Realiza a autenticação do usuário
    	Authentication auth = authManager.authenticate(
        		new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );

        User user = (User) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
