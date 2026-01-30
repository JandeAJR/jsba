package br.net.community.jsba.wrapper;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Classe: ResourcePageableResponse
 *
 * <p>Classe reponsável por encapsular (wrapper) as respostas http para paginação de dados.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@Getter              
@NoArgsConstructor
@AllArgsConstructor
public class ResourcePageableResponse<T> {
	@JsonProperty("data")
	private List<T> data;
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("size")
    private int size;
	
	@JsonProperty("totalElements")
    private long totalElements;
	
	@JsonProperty("totalPages")
    private int totalPages;

    public ResourcePageableResponse(Page<T> pageData) {
        this.data = pageData.getContent();
        this.page = pageData.getNumber();
        this.size = pageData.getSize();
        this.totalElements = pageData.getTotalElements();
        this.totalPages = pageData.getTotalPages();
    }
}
