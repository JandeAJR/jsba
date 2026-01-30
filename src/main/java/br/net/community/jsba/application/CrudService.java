package br.net.community.jsba.application;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface: CrudService
 *
 * <p>Interface genérica para serviços de CRUD:<br>
 * T = Tipo de DTO (ex.: CustomerDTO);<br>
 * Id = Tipo do identificador da entidade (ex.: Long, CustomerPk).</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public interface CrudService<T, Id> {
	T create(T dto);
    T findById(Id id);
    List<T> findAll();
    T update(Id id, T dto);
    
    default Page<T> findAllPageable(Pageable pageable) {
    	// Método opcional (as classes não são obrigadas a implementá-lo)
    	throw new NotImplementedException("Método não implementado.");
    }
    
    default List<T> findAllById(List<Id> ids) {
    	// Método opcional (as classes não são obrigadas a implementá-lo)
    	throw new NotImplementedException("Método não implementado.");
    }
    
    default void deleteById(Id id) {
    	// Método opcional (as classes não são obrigadas a implementá-lo)
		throw new NotImplementedException("Método não implementado.");
	}
}
