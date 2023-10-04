package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.repository.CommandeRepository;
import com.dp.dpshopbackend.services.impl.CommandeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CommandeServiceTest {

    @InjectMocks
    private CommandeServiceImpl commandeService;

    @Mock
    private CommandeRepository commandeRepository;

    @Test
    public void findAllTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .build();
        UtilisateurPOSTDto utilisateurPOSTDto = UtilisateurPOSTDto.builder()
                .id(1L)
                .email("user@gmail.com")
                .username("user")
                .password("passer1234")
                .build();
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .numeroCommande(120L)
                .status("ENCOURS")
                //            .clientDto(clientDto)
                //        .utilisateurPOSTDto(utilisateurPOSTDto)
                .build();
        Commande commander = CommandeDto.fromDtoToEntity(commandeDto);
        when(commandeRepository.findAll()).thenReturn(singletonList(commander));

        List<CommandeDto> commandeDtoList = commandeService.findAllActiveCommandes();

        assertThat(commandeDtoList).isNotNull();
        assertThat(commandeDtoList.size()).isEqualTo(1);
        verify(commandeRepository).findAll();
        assertThat(commandeDtoList.get(0)).isEqualTo(CommandeDto.fromEntityToDto(commander));
    }

    @Test
    public void findByIdTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .build();
        UtilisateurPOSTDto utilisateurPOSTDto = UtilisateurPOSTDto.builder()
                .id(1L)
                .email("user@gmail.com")
                .username("user")
                .password("passer1234")
                .build();
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .numeroCommande(120L)
                .status("PAYEE")
                //           .clientDto(clientDto)
                //        .utilisateurPOSTDto(utilisateurPOSTDto)
                .build();

        Optional<Commande> commander = Optional.ofNullable(CommandeDto.fromDtoToEntity(commandeDto));
        when(commandeRepository.findById(commander.get().getId())).thenReturn(commander);

        CommandeDto commanderDtoSavedResult = commandeService.findById(commandeDto.getId());

        verify(commandeRepository).findById(commander.get().getId());
        assertThat(commandeDto).isNotNull();
        //    assertThat(commanderDtoSavedResult).isEqualTo(commandeDto);
        assertThat(commanderDtoSavedResult.getId()).isEqualTo(commander.get().getId());

    }


}
