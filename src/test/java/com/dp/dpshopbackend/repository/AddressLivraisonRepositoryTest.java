package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.dto.CommandeDto;
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
public class AddressLivraisonRepositoryTest {

    @Autowired
    private AddressLivraisonRepository addressLivraisonRepository;

    @Test
    @Rollback(false)
    public void testCreateAddressLivraison() {

        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);
        commandeDto.setClientDto(clientDto);

        String refAddLivraison = "123EST";
        String city = "Dakar";
        AddressLivraisonDto addressLivraisonDto = new AddressLivraisonDto();
        addressLivraisonDto.setReference(refAddLivraison);
        addressLivraisonDto.setCity(city);
        addressLivraisonDto.setCommandeDto(commandeDto);


        AddressLivraisonDto addressLivraisonDtoResult = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
                )
        );

        assertNotNull(addressLivraisonDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAddressLivraison() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);
        commandeDto.setClientDto(clientDto);

        String refAddLivraison = "123EST";
        String city = "Dakar";
        AddressLivraisonDto addressLivraisonDto = new AddressLivraisonDto();
        addressLivraisonDto.setReference(refAddLivraison);
        addressLivraisonDto.setCity(city);
        addressLivraisonDto.setCommandeDto(commandeDto);


        AddressLivraisonDto addressLivraisonDtoResult = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
                )
        );

        String refLivraivraion = "Livr120";
        AddressLivraisonDto addressLivraisonUpdateDto = new AddressLivraisonDto();
        addressLivraisonUpdateDto.setReference(refLivraivraion);
        addressLivraisonDto.setCommandeDto(commandeDto);
        addressLivraisonDto.setId(1);
        AddressLivraisonDto.fromEntityToDto(addressLivraisonRepository.save(AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)));

        assertThat(addressLivraisonUpdateDto.getReference()).isEqualTo(refLivraivraion);

    }

    @Test
    public void testFindById() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);
        commandeDto.setClientDto(clientDto);

        String refAddLivraison = "123EST";
        String city = "Dakar";
        AddressLivraisonDto addressLivraisonDto = new AddressLivraisonDto();
        addressLivraisonDto.setReference(refAddLivraison);
        addressLivraisonDto.setCity(city);
        addressLivraisonDto.setCommandeDto(commandeDto);


        AddressLivraisonDto addressLivraisonDtoResult = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
                )
        );
        boolean isExistAddressLivraison = addressLivraisonRepository.findById(addressLivraisonDtoResult.getId()).isPresent();

        assertTrue(isExistAddressLivraison);

    }

    @Test
    public void testFindAll() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);
        commandeDto.setClientDto(clientDto);

        String refAddLivraison = "123EST";
        String city = "Dakar";
        AddressLivraisonDto addressLivraisonDto = new AddressLivraisonDto();
        addressLivraisonDto.setReference(refAddLivraison);
        addressLivraisonDto.setCity(city);
        addressLivraisonDto.setCommandeDto(commandeDto);


        AddressLivraisonDto addressLivraisonDtoResult = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
                )
        );

        String refLivraison = "Address-126";
        AddressLivraisonDto addressLivraisonDto1 = new AddressLivraisonDto();
        addressLivraisonDto1.setReference(refLivraison);

        AddressLivraisonDto addressLivraisonDtoResult1 = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto1)
                )
        );

        String refLivraison2 = "Address-1262";
        AddressLivraisonDto addressLivraisonDto2 = new AddressLivraisonDto();
        addressLivraisonDto2.setReference(refLivraison2);

        AddressLivraisonDto addressLivraisonDtoResult2 = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto2)
                )
        );

        List<?> addresseLivraisons = addressLivraisonRepository.findAll();

        assertThat(addresseLivraisons).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String firstName = "tairou";
        String lastName = "Diallo";
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);

        String reference = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(reference);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);
        commandeDto.setClientDto(clientDto);

        String refAddLivraison = "123EST";
        String city = "Dakar";
        AddressLivraisonDto addressLivraisonDto = new AddressLivraisonDto();
        addressLivraisonDto.setReference(refAddLivraison);
        addressLivraisonDto.setCity(city);
        addressLivraisonDto.setCommandeDto(commandeDto);


        AddressLivraisonDto addressLivraisonDtoResult = AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
                )
        );

        boolean isExistBeforeDelete = addressLivraisonRepository.findById(addressLivraisonDtoResult.getId()).isPresent();

        addressLivraisonRepository.deleteById(addressLivraisonDtoResult.getId());

        boolean notExistAfterDelete = addressLivraisonRepository.findById(addressLivraisonDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
