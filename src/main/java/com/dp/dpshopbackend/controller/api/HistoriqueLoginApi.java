package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.HistoriqueLoginDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/historiqueLogins")
public interface HistoriqueLoginApi {


    @GetMapping(value = "/search-all-active-historiqueLogins", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueLogins actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueLogins actives",
            responseContainer = "List<HistoriqueLoginDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueLogins par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<HistoriqueLoginDto>> getAllActiveHistoriqueLogins();

    @DeleteMapping(value = "/delete-historiqueLogin/{idHisotiqueLogin}")
    @ApiOperation(value = "Supprimer une HisotiqueLogin par son ID",
            notes = "Cette méthode permet de supprimer une HisotiqueLogin par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HisotiqueLogin a été supprimé")
    })
    void deleteHisotiqueLogin(@PathVariable("idHisotiqueLogin") Long idHisotiqueLogin);
}
