package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.AddressClientDto;
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
public class AddressClientRepositoryTest {

    @Autowired
    private AddresseClientRepository addresseClientRepository;

    @Test
    @Rollback(false)
    public void testCreateAddressClient() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "DK";
        String city = "Dakar";
        AddressClientDto addressClientDto = new AddressClientDto();
        addressClientDto.setReference(reference);
        addressClientDto.setCity(city);
        addressClientDto.setClientDto(clientDto);

        AddressClientDto addressClientDtoDtoResult = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );

        assertNotNull(addressClientDtoDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAddressClient() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "DK";
        String city = "Dakar";
        AddressClientDto addressClientDto = new AddressClientDto();
        addressClientDto.setReference(reference);
        addressClientDto.setCity(city);
        addressClientDto.setClientDto(clientDto);

        AddressClientDto addressClientDtoDtoResult = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );

        String cityAddress = "Dakar-Plateaux";
        AddressClientDto addressClientUpdateDto = new AddressClientDto();
        addressClientUpdateDto.setCity(cityAddress);
        addressClientUpdateDto.setClientDto(clientDto);

        addressClientUpdateDto.setId(1);
        AddressClientDto.fromEntityToDto(addresseClientRepository.save(AddressClientDto.fromDtoToEntity(addressClientUpdateDto)));

        assertThat(addressClientUpdateDto.getCity()).isEqualTo(cityAddress);

    }

    @Test
    public void testFindById() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "DK";
        String city = "Dakar";
        AddressClientDto addressClientDto = new AddressClientDto();
        addressClientDto.setReference(reference);
        addressClientDto.setCity(city);
        addressClientDto.setClientDto(clientDto);

        AddressClientDto addressClientDtoDtoResult = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );

        boolean isExistAddressClient = addresseClientRepository.findById(addressClientDtoDtoResult.getId()).isPresent();

        assertTrue(isExistAddressClient);

    }

    @Test
    public void testFindAll() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "DK";
        String city = "Dakar";
        AddressClientDto addressClientDto = new AddressClientDto();
        addressClientDto.setReference(reference);
        addressClientDto.setCity(city);
        addressClientDto.setClientDto(clientDto);

        AddressClientDto addressClientDtoDtoResult = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );

        String firstName1 = "tairou";
        String lastName1 = "Diallo";
        ClientDto clientDto1 = new ClientDto();
        clientDto.setFirstName(firstName1);
        clientDto.setLastName(lastName1);

        String reference1 = "DK";
        String city1 = "Dakar";
        AddressClientDto addressClientDto1 = new AddressClientDto();
        addressClientDto.setReference(reference1);
        addressClientDto.setCity(city1);
        addressClientDto.setClientDto(clientDto1);

        AddressClientDto addressClientDtoDtoResult1 = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );

        String firstName2 = "tairou";
        String lastName2 = "Diallo";
        ClientDto clientDto2 = new ClientDto();
        clientDto.setFirstName(firstName2);
        clientDto.setLastName(lastName2);

        String reference2 = "DK";
        String city2 = "Dakar";
        AddressClientDto addressClientDto2 = new AddressClientDto();
        addressClientDto.setReference(reference2);
        addressClientDto.setCity(city2);
        addressClientDto.setClientDto(clientDto2);

        AddressClientDto addressClientDtoDtoResult2 = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );
        List<?> addressesClients = addresseClientRepository.findAll();

        assertThat(addressesClients).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String firstName2 = "tairou";
        String lastName2 = "Diallo";
        ClientDto clientDto2 = new ClientDto();
        clientDto2.setFirstName(firstName2);
        clientDto2.setLastName(lastName2);

        String reference2 = "DK";
        String city2 = "Dakar";
        AddressClientDto addressClientDto2 = new AddressClientDto();
        addressClientDto2.setReference(reference2);
        addressClientDto2.setCity(city2);
        addressClientDto2.setClientDto(clientDto2);

        AddressClientDto addressClientDtoDtoResult2 = AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto2)
                )
        );

        boolean isExistBeforeDelete = addresseClientRepository.findById(addressClientDtoDtoResult2.getId()).isPresent();

        addresseClientRepository.deleteById(addressClientDtoDtoResult2.getId());

        boolean notExistAfterDelete = addresseClientRepository.findById(addressClientDtoDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
