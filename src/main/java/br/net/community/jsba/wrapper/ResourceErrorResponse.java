package br.net.community.jsba.wrapper;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe: ErrorResponse
 *
 * <p>Classe reponsável por encapsular (wrapper) e padronizar a estrutura de erros nas respostas da API.<br>
 * Utilizada nas classes GlobalExceptionHandler e BusinessExceptionHandler para retornar detalhes sobre erros ocorridos.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public class ResourceErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer status;
    private String description;
    private String error;
    private String message;
    private String path;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") // ISO 8601
    private Instant timestamp;

    public ResourceErrorResponse(
    		Integer status, 
    		String description,
    		String error, 
    		String message, 
    		String path, 
    		Instant timestamp) {
    	
    	this.status = status;
    	this.description = description;
    	this.error = error;
    	this.message = message;
    	this.path = path;
        this.timestamp = timestamp;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}
