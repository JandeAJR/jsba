package br.net.community.jsba.interfaces.rest;

import java.util.Objects;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.community.jsba.config.ProjectInfo;
import br.net.community.jsba.dto.response.ApplicationInfoDTO;
import br.net.community.jsba.interfaces.rest.assembler.ApplicationInfoHatoasAssembler;
import br.net.community.jsba.util.Consts;
import br.net.community.jsba.wrapper.ResourceHatoasResponse;

/**
 * Classe: ApplicationInfoResource
 *
 * <p>Recurso REST que disponibiliza as informações da aplicação.</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

@RestController
@RequestMapping("/application")
public class ApplicationInfoResource {
    private final ProjectInfo projectInfo;
    private final ApplicationInfoHatoasAssembler hatoasAssembler;
    
    public ApplicationInfoResource(
    		ProjectInfo projectInfo, 
    		ApplicationInfoHatoasAssembler hatoasAssembler) {
    	
		this.projectInfo = projectInfo;
		this.hatoasAssembler = hatoasAssembler;
	}

    @GetMapping(value = "/info", produces = "application/json") // GET /api/jsba/application/info
    public ResponseEntity<ResourceHatoasResponse<EntityModel<ApplicationInfoDTO>>> info() {
    	ApplicationInfoDTO dto = ApplicationInfoDTO.builder()
    			.name(Objects.toString(projectInfo.name(), Consts.UNKNOWN))
    			.version(Objects.toString(projectInfo.version(), Consts.UNKNOWN))
    			.database(Objects.toString(projectInfo.database(), Consts.NOT_CONFIGURED))
    			.profile(Objects.toString(projectInfo.profile(), Consts.UNKNOWN))
    			.build();
    	
    	// Usa o hatoas assembler para adicionar links ao recurso
	    EntityModel<ApplicationInfoDTO> resource = hatoasAssembler.toModel(dto);
	    
	    // Monta o corpo da resposta padronizada
	    ResourceHatoasResponse<EntityModel<ApplicationInfoDTO>> responseBody = new ResourceHatoasResponse<>();
	    responseBody.setStatus(HttpStatus.OK.getReasonPhrase());
	    responseBody.setMessage(Consts.APPLICATION_INFO);
	    responseBody.setData(resource);
	    responseBody.setMetadata(null); // Não há paginação de dados aqui
	         	
    	return ResponseEntity.ok(responseBody);
    }
}
