package br.net.community.jsba.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.net.community.jsba.exception.global.DatabaseException;
import br.net.community.jsba.wrapper.ResourceErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe: GlobalExceptionHandler
 *
 * <p>Classe responsável por tratar exceções de forma global na aplicação.<br>
 * É anotada com @RestControllerAdvice para interceptar exceções lançadas pelos controllers (resources).<br>
 * Os tratamentos para as classes globais de exception da aplicação devem ser implementados aqui</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DatabaseException.class)  // essa anotação trata erros de banco de dados
    public ResponseEntity<ResourceErrorResponse> dataBaseException(
            DatabaseException ex,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "Database error";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
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
    
    @ExceptionHandler(UsernameNotFoundException.class)  // essa anotação trata o erro de usuário não encontrado (Spring Security)
    public ResponseEntity<ResourceErrorResponse> userNameNotFoundException(
            UsernameNotFoundException ex,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "User not found";
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
    
    @ExceptionHandler(BadCredentialsException.class)  // essa anotação trata o erro de credenciais inválidas (Spring Security)
    public ResponseEntity<ResourceErrorResponse> badCredentialsException(
    		BadCredentialsException ex,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "Credenciais inválidas";
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        
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
    
    @ExceptionHandler(AuthenticationException.class)  // essa anotação trata o status 401 unauthorized (Spring Security)
    public ResponseEntity<ResourceErrorResponse> authenticationException(
    		AuthenticationException ex,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "Unauthorized";
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        
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
    
    @ExceptionHandler(AccessDeniedException.class)  // essa anotação trata o status 403 forbidden (Spring Security)
    public ResponseEntity<ResourceErrorResponse> accessDeniedException(
    		AccessDeniedException ex,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "Não autorizado";
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        
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
    
    @ExceptionHandler(NoHandlerFoundException.class)  // essa anotação o trata erro de uri inválida
    public ResponseEntity<ResourceErrorResponse> noHandlerFoundException(
    		NoHandlerFoundException ex,
            HttpServletRequest httpServletRequest) {
    	
        String errorMessage = "Resource not found";
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
        
    @ExceptionHandler(MethodArgumentNotValidException.class)  // essa anotação trata o erro de dados de requisição inválidos
    public ResponseEntity<ResourceErrorResponse> methodArgumentNotValidException(
    		MethodArgumentNotValidException ex,
            HttpServletRequest httpServletRequest) {
    	
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .findFirst()
            .orElse("Dados inválidos");

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
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
    
    @ExceptionHandler(MissingServletRequestParameterException.class)  // essa anotação trata o erro de parâmetros de requisição obrigatórios
    public ResponseEntity<ResourceErrorResponse> missingServletRequestParameterException(
    		MissingServletRequestParameterException ex,
            HttpServletRequest httpServletRequest) {
    	
		String errorMessage = "Parâmetro obrigatório ausente: " + ex.getParameterName();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
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
    
    @ExceptionHandler(HttpMessageNotReadableException.class)  // essa anotação trata o erro de JSON (body) inválido
    public ResponseEntity<ResourceErrorResponse> httpMessageNotReadableException(
    		HttpMessageNotReadableException ex,
            HttpServletRequest httpServletRequest) {
    	
		String errorMessage = "JSON malformado ou corpo inválido";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
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
    
    @ExceptionHandler(Exception.class)  // essa anotação trata os erros genéricos
    public ResponseEntity<ResourceErrorResponse> httpMessageNotReadableException(
    		Exception ex,
            HttpServletRequest httpServletRequest) {
    	
		String errorMessage = "Erro interno do servidor";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        
        ResourceErrorResponse errorResponse = new ResourceErrorResponse(
        		httpStatus.value(),
        		httpStatus.getReasonPhrase(),
        		errorMessage, 
        		"Se este erro persistir procure o adminstrador do sistema.",
        		httpServletRequest.getRequestURI(),
        		Instant.now());
                
        log.error(ex.getMessage()); // Log no servidor (Lombok) 
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
