package br.net.community.jsba.domain.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Classe: User
 *
 * <p>Modelo de Usuário para autenticação e autorização.<br>
 * Implementa UserDetails do Spring Security.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user") // Usando sequence para Oracle
	@SequenceGenerator(
			name = "seq_user",
			sequenceName = "seq_user",
			allocationSize = 1
			)
	private Long id;
	
	@Column(name = "login", length = 30, unique = true, nullable = false)
	private String login;
	
	@Column(name = "name", length = 255, unique = true, nullable = false)
	private String name;
	
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	public User() {} // Precisa do construtor padrão para o JPA
	
	public User(String login, String name, String password) {
		this.login = login;
		this.name = name;
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Logica para buscar as permissões do usuário
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override public String getUsername() { return login; }
	
    @Override public String getPassword() { return password; }
    
    @Override public boolean isAccountNonExpired() { return true; }
    
    @Override public boolean isAccountNonLocked() { return true; }
    
    @Override public boolean isCredentialsNonExpired() { return true; }
    
    @Override public boolean isEnabled() { return true; }
}
