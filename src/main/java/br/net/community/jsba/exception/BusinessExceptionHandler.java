package br.net.community.jsba.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.net.community.jsba.exception.business.ResourceNotFoundException;
import br.net.community.jsba.wrapper.ResourceErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe: BusinessExceptionHandler
 *
 * <p>Classe para tratar exceções de regras de negócio na aplicação.<br>
 * É anotada com @RestControllerAdvice para interceptar exceções lançadas pelos controllers (resources).<br>
 * Os tratamentos para as classes de business exceptions da aplicação devem ser implementados aqui</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)  // essa anotação trata o erro da exception ResourceNotFoundException
    public ResponseEntity<ResourceErrorResponse> resourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest httpServletRequest) {
		
        String errorMessage = "Não localizado";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        
        ResourceErrorResponse errorResponse = new ResourceErrorResponse(
        		httpStatus.value(),
        		httpStatus.getReasonPhrase(),
        		errorMessage, 
        		ex.getMessage(),
        		httpServletRequest.getRequestURI(),
        		Instant.now());
        
        log.error(ex.getMessage()); // Log no servidor (Lombok)  
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
