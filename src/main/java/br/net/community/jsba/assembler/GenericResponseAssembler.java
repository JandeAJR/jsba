package br.net.community.jsba.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;

import br.net.community.jsba.wrapper.ResourceHatoasResponse;

/**
 * Classe: GenericHateoasAssembler
 *
 * <p>Classe reponsável por implementar a montagem da estrutura de resposta das requisições http.<br>
 * Monta também a estrutura dos links para paginação de dados.</p>
 * 
 * <p>Opção para quem não quiser gerar a resposta http dos controllers seguindo o padrão HATOAS.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public class GenericResponseAssembler<T> {
	private final RepresentationModelAssembler<T, EntityModel<T>> itemAssembler;

    public GenericResponseAssembler(RepresentationModelAssembler<T, EntityModel<T>> itemAssembler) {
        this.itemAssembler = itemAssembler;
    }

    public ResourceHatoasResponse<List<EntityModel<T>>> toPagedResponse(
    		Page<T> page, 
    		String message,
    		String baseUrl) {
    	
        List<EntityModel<T>> itemModels = page.getContent().stream()
                .map(itemAssembler::toModel)
                .collect(Collectors.toList());

        ResourceHatoasResponse<List<EntityModel<T>>> response = new ResourceHatoasResponse<>();
        response.setStatus(HttpStatus.OK.getReasonPhrase());
        response.setMessage(message);
        response.setData(itemModels);
        response.setMetadata(new ResourceHatoasResponse.Metadata(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        ));

        GenericHateoasAssembler.addPaginationLinks(response, page, baseUrl);
        return response;
    }
}
