package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommandeRepositoryTest {

    @Autowired
    private CommandeRepository commandeRepository;

    @Test
    @Rollback(false)
    public void testCreateCommande() {
        String firstName = "tairou"; String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName); clientDto.setLastName(lastName);

        String reference = "Com120"; String numeroCommande = "Com120"; double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference); commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total); commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        assertNotNull(commandeDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCommande() {
        String firstName = "tairou"; String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName); clientDto.setLastName(lastName);

        String reference = "Com120"; String numeroCommande = "Com120"; double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference); commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total); commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        String refCommande = "Com04_2021";
        CommandeDto commandeUpdateDto = new CommandeDto();
        commandeUpdateDto.setReference(refCommande);
        commandeUpdateDto.setClientDto(clientDto);
        commandeUpdateDto.setId(1);
        CommandeDto.fromEntityToDto(commandeRepository.save(CommandeDto.fromDtoToEntity(commandeUpdateDto)));

        assertThat(commandeUpdateDto.getReference()).isEqualTo(refCommande);

    }

    @Test
    public void testFindById() {
        String firstName = "tairou"; String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName); clientDto.setLastName(lastName);

        String reference = "Com120"; String numeroCommande = "Com120"; double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference); commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total); commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        boolean isExistCommande = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        assertTrue(isExistCommande);

    }

    @Test
    public void testFindAll() {
        String firstName = "tairou"; String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName); clientDto.setLastName(lastName);

        String reference = "Com120"; String numeroCommande = "Com120"; double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference); commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total); commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        String reference1 = "Com120"; String numeroCommande1 = "Com120";
        CommandeDto commandeDto1 = new CommandeDto();
        commandeDto1.setReference(reference1); commandeDto.setNumeroCommande(numeroCommande1);
        commandeDto1.setClientDto(clientDto);

        CommandeDto commandeDtoResult1 = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto1)
                )
        );

        String reference2 = "Com122"; String numeroCommande2 = "Com122";
        CommandeDto commandeDto2 = new CommandeDto();
        commandeDto2.setReference(reference2); commandeDto.setNumeroCommande(numeroCommande2);
        commandeDto2.setClientDto(clientDto);

        CommandeDto commandeDtoResult2 = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto2)
                )
        );

        List<?> commandes = commandeRepository.findAll();

        assertThat(commandes).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String firstName = "tairou"; String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName); clientDto.setLastName(lastName);

        String reference = "Com120"; String numeroCommande = "Com120"; double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference); commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total); commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        boolean isExistBeforeDelete = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        commandeRepository.deleteById(commandeDtoResult.getId());

        boolean notExistAfterDelete = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
