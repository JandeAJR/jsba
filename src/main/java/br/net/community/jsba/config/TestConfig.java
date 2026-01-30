package br.net.community.jsba.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.net.community.jsba.domain.model.User;
import br.net.community.jsba.domain.repository.UserRepository;

/**
 * Classe: TestConfig
 *
 * <p>Configuração de teste para popular o banco de dados com dados iniciais.<br>
 * Profile: test</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	private final UserRepository userRepository;
    
	// Constructor Injection
	public TestConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    public void run(String... args) throws Exception {
        // Criando um User de exemplo
        // Login: admin, Senha: admin (criptografada com BCrypt)
        User user = new User("admin", "Administrator", "$2a$10$y.fTjOfbxl9oDZdYJ3NngukZrxTi94le7/fvor5umT091S31d.Eti");
        userRepository.save(user);
    }
}
