package br.net.community.jsba.config.security.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.net.community.jsba.domain.repository.UserRepository;

/**
 * Classe: UserDetailsServiceImpl
 * 
 * <p>Classe responsável por implementar o serviço de detalhes do usuário que carrega as informações a partir do repositório.</p>
 * 
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
    	// Busca o usuário pelo login no repositório
        return repository.findByLogin(login)
        		.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
