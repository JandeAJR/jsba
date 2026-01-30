package br.net.community.jsba.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe: AuthDTO
 *
 * <p>Classe DTO request para postar as informações de autenticação (login e senha).</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Getter
@Setter
public class AuthDTO {
	private String login;
	private String password;
}
