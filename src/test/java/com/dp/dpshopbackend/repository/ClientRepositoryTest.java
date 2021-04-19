package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Rollback(false)
    public void testCreateClient() {
        ClientDto clientDto = new ClientDto();
        String reference = "Client1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String phoneClient = "779440310";
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setPhoneClient(phoneClient);

        ClientDto clientDtoResult = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );

        assertNotNull(clientDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateClient() {
        ClientDto clientDto = new ClientDto();
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String phoneClient = "779440310";
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setPhoneClient(phoneClient);

        ClientDto clientDtoResult = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );

        String clientFirstName = "Thir";
        ClientDto clientUpdateDto = new ClientDto();
        clientUpdateDto.setFirstName(clientFirstName);
        clientUpdateDto.setId((long) 1);

        ClientDto.fromEntityToDto(clientRepository.save(ClientDto.fromDtoToEntity(clientUpdateDto)));

        assertThat(clientUpdateDto.getFirstName()).isEqualTo(clientFirstName);

    }

    @Test
    public void testFindById() {
        ClientDto clientDto = new ClientDto();
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String phoneClient = "779440310";
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setPhoneClient(phoneClient);

        ClientDto clientDtoResult = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );

        boolean isCategory = clientRepository.findById(clientDtoResult.getId()).isPresent();

        assertTrue(isCategory);

    }

    @Test
    public void testFindAll() {
        ClientDto clientDto = new ClientDto();
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String phoneClient = "779440310";
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setPhoneClient(phoneClient);

        ClientDto clientDtoResult = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );

        ClientDto clientDto1 = new ClientDto();
        String firstName1 = "tairou";
        String lastName1 = "diallo";
        clientDto1.setFirstName(firstName1);
        clientDto1.setLastName(lastName1);

        ClientDto clientDtoResult1 = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto1)
                )
        );

        ClientDto clientDto2 = new ClientDto();
        String firstName2 = "tairou";
        String lastName2 = "diallo";
        clientDto2.setFirstName(firstName2);
        clientDto2.setLastName(lastName2);

        ClientDto clientDtoResult2 = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto2)
                )
        );

        List<?> clients = clientRepository.findAll();

        assertThat(clients).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        ClientDto clientDto2 = new ClientDto();
        String firstName2 = "tairou";
        String lastName2 = "diallo";
        clientDto2.setFirstName(firstName2);
        clientDto2.setLastName(lastName2);

        ClientDto clientDtoResult2 = ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto2)
                )
        );


        boolean isExistBeforeDelete = clientRepository.findById(clientDtoResult2.getId()).isPresent();

        clientRepository.deleteById(clientDtoResult2.getId());

        boolean notExistAfterDelete = clientRepository.findById(clientDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
