package br.net.community.jsba.exception.business;

/**
 * Classe: BusinessExceptionHandler
 *
 * <p>Exceção personalizada para indicar que um recurso não foi encontrado.<br>
 * Estende RuntimeException para permitir tratamento específico em controladores.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("ID: " + id.toString() + " não localizado");
	}
}
