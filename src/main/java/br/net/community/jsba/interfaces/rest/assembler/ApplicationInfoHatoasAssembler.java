package br.net.community.jsba.interfaces.rest.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import br.net.community.jsba.dto.response.ApplicationInfoDTO;
import br.net.community.jsba.interfaces.rest.ApplicationInfoResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApplicationInfoHatoasAssembler 
	implements RepresentationModelAssembler<ApplicationInfoDTO, EntityModel<ApplicationInfoDTO>> {

	@Override
	public EntityModel<ApplicationInfoDTO> toModel(ApplicationInfoDTO dto) {
		return EntityModel.of(dto,
				linkTo(methodOn(ApplicationInfoResource.class).info())
					.withSelfRel()
					.withType(HttpMethod.GET.toString()));
	}
}
