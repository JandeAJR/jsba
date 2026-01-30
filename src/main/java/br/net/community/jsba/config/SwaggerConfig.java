package br.net.community.jsba.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Classe: SwaggerConfig
 *
 * <p>Classe responsável pela Configuração do Swagger/OpenAPI para documentação da API REST.<br>
 * Define informações como título, versão, descrição, contato e servidores.<br>
 * Essas informações ajudam desenvolvedores a entenderem e utilizarem a API de forma eficaz.<br>
 * A documentação gerada pode ser acessada via Swagger UI.</p>
 * 
 * <p>Swagger/OpenAPI url:<br>
 * Api docs:   http://localhost:8080/api/jsba/v3/api-docs<br>
 * Swagger UI: http://localhost:8080/api/jsba/swagger-ui/index.html</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "JSBA - Java Spring Boot Application Project REST API",
        version = "v1",
        description = """
            Informe aqui a descrição da API do seu projeto.
            Este template para projetos de aplicações Spring Boot foi desenvolvido seguindo as boas práticas REST, 
            princípios de domínio e foco em clareza, previsibilidade e escalabilidade.
            """,
        contact = @Contact(
            name = "Time de Arquitetura",
            email = "arquitetura@empresa.com",
            url = "https://empresa.com.br"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080/api/jsba",
            description = "Ambiente Local"
        ),
        @Server(
            url = "https://empresa.dsv/api/jsba",
            description = "Desenvolvimento"
        ),
        @Server(
            url = "https://empresa.hml/api/jsba",
            description = "Homologação"
        ),
        @Server(
            url = "https://empresa.com.br/api/jsba",
            description = "Produção"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {}
