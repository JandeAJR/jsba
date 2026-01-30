package br.net.community.jsba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe: ProjectProperties
 *
 * <p>Classe responsável por mapear as propriedades do application.yml relacionadas ao projeto.<br>
 * As propriedades são prefixadas com "project".</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Component
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {
    private String name;
    private String version;
    private String database;
    private String profile;

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
