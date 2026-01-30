package br.net.community.jsba.config.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Classe: JwtProperties
 *
 * <p>Classe reponsável por mapear a estrutura das propriedades de configuração do JWT a partir do arquivo application.yml.<br>
 * Contém a chave secreta e o tempo de expiração dos tokens JWT.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Validated
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	@NotBlank
    private String key; // private key for signing JWT
	
	@NotNull
    private Long expiration; // in minutes

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
