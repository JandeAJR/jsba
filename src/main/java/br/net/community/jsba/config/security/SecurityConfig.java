package br.net.community.jsba.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.net.community.jsba.config.security.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe: SecurityConfig
 *
 * <p>Classe reponsável por implementar a configuração de segurança da aplicação.<br>
 * Define regras de autenticação e autorização para os endpoints da API.<br>
 * Configura CORS, desabilita CSRF e define quais endpoints são públicos ou protegidos.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Configuration  
@EnableWebSecurity 
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
    	// BCrypt para hash de senhas. Usado no UserDetailsService
    	// BCrypt é um algoritmo de hashing seguro para senhas
    	// https://docs.spring.io/spring-security/reference/passwords/encoding.html
        return new BCryptPasswordEncoder();
    }
    
    @Bean	
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtFilter
    ) throws Exception {
    	// Metodo para configurar a segurança HTTP da aplicação
        http
            // 🔑 CORS
            .cors(Customizer.withDefaults())

            // ❌ API REST → sem CSRF (Cross-Site Request Forgery)
            .csrf(csrf -> csrf.disable())

            // 🧠 JWT → API STATELESS (não guarda sessão)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🛡️ AUTORIZAÇÃO GLOBAL DE ENDPOINTS
            .authorizeHttpRequests(auth -> auth
                // 🔓 Endpoint de autenticação JWT (login)
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                // Swagger liberado
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api/spring/v3/api-docs/**"
                ).permitAll()

                // Endpoints públicos (liste os endpoints públicos aqui)
                .requestMatchers(HttpMethod.GET, "/application-info").permitAll()

                // 🔐 O resto exige JWT válido
                .anyRequest().authenticated()
            )

            // 🔗 FILTRO JWT (serve para validar o token em cada requisição)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            // ❌ SEM POPUP (desabilita autenticação básica e form login)
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())

            // 🔐 Erro 401 (unauthorized) para requisições sem token ou token inválido
            //    Erro 403 (forbidden) para acesso negado
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, e) ->
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .accessDeniedHandler((req, res, e) -> 
                	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden"))
            );

        // Retorna a configuração de segurança construída
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            HttpSecurity http,                     // Configuração HTTP, serve para pegar o AuthenticationManagerBuilder (faz parte do Spring Security)
            UserDetailsService userDetailsService, // Serviço para carregar detalhes do usuário, serve para autenticação (faz parte do Spring Security)
            PasswordEncoder passwordEncoder        // Encoder para senhas, serve para comparar senhas (faz parte do Spring Security)
    ) throws Exception {
    	// Metodo para autenticar usuários com UserDetailsService e PasswordEncoder
        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class); // Pega o builder do AuthenticationManager

        builder
            .userDetailsService(userDetailsService) // Configura o UserDetailsService para carregar usuários
            .passwordEncoder(passwordEncoder);      // Configura o PasswordEncoder para comparar senhas

        // Retorna o AuthenticationManager configurado
        return builder.build();
    }
}
