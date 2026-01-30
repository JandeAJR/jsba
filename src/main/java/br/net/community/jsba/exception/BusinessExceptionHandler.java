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
 * Utiliza @ControllerAdvice para interceptar exceções lançadas pelos controllers (resources).<br>
 * Implemente o tratamento para as classes de business exceptions da aplicação aqui</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Slf4j
@RestControllerAdvice(basePackages = "com.javacode.template") // escopo do ControllerAdvice
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
        
        log.info(ex.getMessage()); // Log de info no servidor (Lombok)  
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
