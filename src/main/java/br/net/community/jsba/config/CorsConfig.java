package br.net.community.jsba.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe: CorsConfig
 *
 * <p>Classe reponsável pela configuração de CORS (Cross-Origin Resource Sharing) para a aplicação.<br>
 * Define quais origens, métodos HTTP e headers são permitidos nas requisições feitas a partir de domínios diferentes.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Configuração de CORS
    	CorsConfiguration config = new CorsConfiguration();

        // Permite qualquer origem (forma correta com Security)
        config.setAllowedOriginPatterns(List.of("*"));

        // Métodos HTTP permitidos
        config.setAllowedMethods(List.of(
        		"GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        // Headers permitidos
        config.setAllowedHeaders(List.of("*"));

        // Permite envio de cookies / authorization header
        config.setAllowCredentials(true);

        // Aplica a configuração para todas as rotas
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
