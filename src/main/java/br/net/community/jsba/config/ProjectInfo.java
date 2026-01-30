package br.net.community.jsba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Record: ProjectInfo
 *
 * <p>Record para mapear a estrutura das propriedades do application.yml relacionadas ao projeto.<br>
 * As propriedades são prefixadas com "project".</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@ConfigurationProperties(prefix = "project")
public record ProjectInfo(
    String name,
    String version,
    String database,
    String profile
) {}
