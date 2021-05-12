package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressClient;
import com.dp.dpshopbackend.models.Client;
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

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Rollback(false)
    public void testCreateAddressClient() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        AddressClient addressClient = new AddressClient(1L, "add", "add", "add", "add", "add", "add", client);

        AddressClient addressClientResult = addresseClientRepository.save(addressClient);

        assertNotNull(addressClientResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAddressClient() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        AddressClient addressClient = new AddressClient(1L, "add", "add", "add", "add", "add", "add", client);
        addresseClientRepository.save(addressClient);

        String refAddress = "Address1";
        String quartier = "Mariste";
        addressClient.setId(2L);
        addressClient.setReference(refAddress);
        addressClient.setQuartier(quartier);

        AddressClient addressClientUpdate = addresseClientRepository.save(addressClient);

        assertThat(addressClientUpdate.getReference()).isEqualTo(refAddress);
        assertThat(addressClientUpdate.getQuartier()).isEqualTo(quartier);
        assertThat(addressClientUpdate.getCity()).isEqualTo(addressClient.getCity());
        assertThat(addressClientUpdate.getRue()).isEqualTo(addressClient.getRue());

    }

    @Test
    public void testFindById() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        AddressClient addressClient = new AddressClient(1L, "add", "add", "add", "add", "add", "add", client);

        AddressClient addressClientResult = addresseClientRepository.save(addressClient);

        boolean isExistAddressClient = addresseClientRepository.findById(addressClientResult.getId()).isPresent();

        assertTrue(isExistAddressClient);

    }

    @Test
    public void testFindAll() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        AddressClient addressClient = new AddressClient(1L, "add", "add", "add", "add", "add", "add", client);
        addresseClientRepository.save(addressClient);

        AddressClient addressClient1 = new AddressClient(2L, "add", "add", "add", "add", "add", "add", client);
        addresseClientRepository.save(addressClient1);

        AddressClient addressClient2 = new AddressClient(3L, "add", "add", "add", "add", "add", "add", client);
        addresseClientRepository.save(addressClient2);


        List<AddressClient> addressClientList = addresseClientRepository.findAll();

        assertThat(addressClientList).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        AddressClient addressClient = new AddressClient(1L, "add", "add", "add", "add", "add", "add", client);

        AddressClient addressClientDtoDtoResult2 = addresseClientRepository.save(addressClient);

        boolean isExistBeforeDelete = addresseClientRepository.findById(addressClientDtoDtoResult2.getId()).isPresent();

        addresseClientRepository.deleteById(addressClientDtoDtoResult2.getId());

        boolean notExistAfterDelete = addresseClientRepository.findById(addressClientDtoDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
