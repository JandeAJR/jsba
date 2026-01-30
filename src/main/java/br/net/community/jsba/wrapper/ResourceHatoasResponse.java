package br.net.community.jsba.wrapper;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe: ResourceHatoasResponse
 *
 * <p>Classe reponsável por encapsular (wrapper) as respostas http no padrão HATOAS.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceHatoasResponse<T> extends RepresentationModel<ResourceHatoasResponse<T>> {
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("message")
    private String message;
	
	@JsonProperty("data")
    private T data;
	
	@JsonProperty("metadata")
    private Metadata metadata;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor    
    public static class Metadata {
    	@JsonProperty("page")
        private int page;
    	
    	@JsonProperty("size")
        private int size;
    	
    	@JsonProperty("totalElements")
        private long totalElements;
    	
    	@JsonProperty("totalPages")
        private int totalPages;
    }
    
    /**
     * Métodos hashCode e equal implementados para ficar em conformidades
     * com as regras do SonarLint Rules Descriptions.
     */
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(data);
		return result;
	}

    @Override
    public boolean equals(Object other) {
    	return super.equals(other);
    }
}
