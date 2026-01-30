package br.net.community.jsba.assembler;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe: GenericHateoasAssembler
 *
 * <p>Classe reponsável por implementar a montagem do padrão HATOAS nas respostas http dos controllers.<br>
 * Monta também a estrutura dos links para paginação de dados.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public class GenericHateoasAssembler {
	// Com um construtor privado a classe não pode ser instansializada
	private GenericHateoasAssembler() {
		throw new UnsupportedOperationException("This assembler class cannot be instantiated");
	}
	
	public static <R extends RepresentationModel<?>> void addPaginationLinks(
			R resource, 
			Page<?> page,
			String baseUrl) {
		
		// Cria o UriComponents a partir da URI
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(baseUrl).build();	

		// Recupera o valor do parâmetro "sort" vindo da querystring
		String sortParam = uriComponents.getQueryParams().getFirst("sort");

		// Self
	    String selfUrl = UriComponentsBuilder.fromUriString(baseUrl)
	            .replaceQueryParam("page", page.getNumber())
	            .replaceQueryParam("size", page.getSize())
	            .replaceQueryParam("sort", sortParam)
	            .build()
	            .toUriString();
	    resource.add(Link.of(selfUrl, "self"));

	    // Next
	    if ((page.getTotalPages() - 1) > 0 && page.hasNext()) {
	    	String nextUrl = UriComponentsBuilder.fromUriString(baseUrl)
	    			.replaceQueryParam("page", page.getNumber() + 1)
	    			.replaceQueryParam("size", page.getSize())
	    			.replaceQueryParam("sort", sortParam)
	    			.build()
	    			.toUriString();
	    	resource.add(Link.of(nextUrl, "next"));	    	
	    }

	    // Prev
	    if ((page.getTotalPages() - 1) > 0 && page.hasPrevious()) {
	        String prevUrl = UriComponentsBuilder.fromUriString(baseUrl)
	                .replaceQueryParam("page", page.getNumber() - 1)
	                .replaceQueryParam("size", page.getSize())
	                .replaceQueryParam("sort", sortParam)
	                .build()
	                .toUriString();
	        resource.add(Link.of(prevUrl, "prev"));
	    }

	    // First
	    String firstUrl = UriComponentsBuilder.fromUriString(baseUrl)
	            .replaceQueryParam("page", 0)
	            .replaceQueryParam("size", page.getSize())
	            .replaceQueryParam("sort", sortParam)
	            .build()
	            .toUriString();
	    resource.add(Link.of(firstUrl, "first"));

	    // Last
	    String lastUrl = UriComponentsBuilder.fromUriString(baseUrl)
	            .replaceQueryParam("page", page.getTotalPages() - 1)
	            .replaceQueryParam("size", page.getSize())
	            .replaceQueryParam("sort", sortParam)
	            .build()
	            .toUriString();
	    resource.add(Link.of(lastUrl, "last"));
    }
}
