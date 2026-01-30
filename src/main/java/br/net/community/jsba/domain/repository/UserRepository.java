package br.net.community.jsba.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.community.jsba.domain.model.User;

/**
 * Interface: UserRepository
 * 
 * <p>Interface reponsável por definir as operações de acesso a dados para a entidade User.</p>
 * 
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLogin(String login);
}
