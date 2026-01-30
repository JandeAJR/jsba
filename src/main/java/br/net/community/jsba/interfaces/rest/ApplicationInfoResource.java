package br.net.community.jsba.interfaces.rest;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.community.jsba.config.ProjectInfo;
import br.net.community.jsba.dto.response.ApplicationInfoDTO;
import br.net.community.jsba.util.Consts;

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

    public ApplicationInfoResource(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    @GetMapping(value = "/info", produces = "application/json") // GET /api/jsba/application/info
    public ResponseEntity<ApplicationInfoDTO> info() {
    	ApplicationInfoDTO body = ApplicationInfoDTO.builder()
    			.name(Objects.toString(projectInfo.name(), Consts.UNKNOWN))
    			.version(Objects.toString(projectInfo.version(), Consts.UNKNOWN))
    			.database(Objects.toString(projectInfo.database(), Consts.NOT_CONFIGURED))
    			.profile(Objects.toString(projectInfo.profile(), Consts.UNKNOWN))
    			.build();
     	
    	return ResponseEntity.ok(body);
    }
}
