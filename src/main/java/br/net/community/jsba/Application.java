package br.net.community.jsba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Classe: Application
 *
 * <p>Starter da aplicação Java Spring Boot - JSBA.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@SpringBootApplication
@EnableFeignClients           // Habilita o uso do OpenFeign para comunicação entre serviços (apis externas)
@ConfigurationPropertiesScan  // Habilita o scan de classes anotadas com @ConfigurationProperties
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
