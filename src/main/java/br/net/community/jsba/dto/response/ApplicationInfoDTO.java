package br.net.community.jsba.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe: ApplicationInfoDTO
 *
 * <p>Classe DTO response para retornar as informações da aplicação.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Getter
@Setter
@Builder
@JsonPropertyOrder({ "name", "version", "database", "profile" })
public class ApplicationInfoDTO {
	private String name;
	private String version;
	private String database;
	private String profile;
}
