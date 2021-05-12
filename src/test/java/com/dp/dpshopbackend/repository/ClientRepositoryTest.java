package com.dp.dpshopbackend.repository;

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
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Rollback(false)
    public void testCreateClient() {
        String reference = "Client1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String phoneClient = "779440310";
        Client client = new Client(1L, reference, firstName, lastName, email, phoneClient);

        Client clientResult = clientRepository.save(client);

        assertNotNull(clientResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateClient() {
        Client client = new Client(1L, "Cl1", "Cl1", "Cl1", "cl1@gmail.com", "779440310");
        clientRepository.save(client);

        String firstName = "client1";
        String lastName = "Client1";

        client.setId(2L);
        client.setFirstName(firstName);
        client.setLastName(lastName);

        Client clientUpdate = clientRepository.save(client);

        assertThat(clientUpdate.getFirstName()).isEqualTo(firstName);
        assertThat(clientUpdate.getLastName()).isEqualTo(lastName);
        assertThat(clientUpdate.getReference()).isEqualTo(client.getReference());
        assertThat(clientUpdate.getPhoneClient()).isEqualTo(client.getPhoneClient());

    }

    @Test
    public void testFindById() {
        Client client = new Client(1L, "Cl1", "Cl1", "Cl1", "cl1@gmail.com", "779440310");

        Client clientResult = clientRepository.save(client);

        boolean isExistClient = clientRepository.findById(clientResult.getId()).isPresent();

        assertTrue(isExistClient);

    }

    @Test
    public void testFindAll() {
        Client client = new Client(1L, "Cl1", "Cl1", "Cl1", "cl1@gmail.com", "779440310");
        clientRepository.save(client);

        Client client1 = new Client(2L, "Cl1", "Cl1", "Cl1", "cl1@gmail.com", "779440310");
        clientRepository.save(client);

        List<Client> clientList = clientRepository.findAll();

        assertThat(clientList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Client client = new Client(1L, "Cl1", "Cl1", "Cl1", "cl1@gmail.com", "779440310");

        Client clientResult = clientRepository.save(client);

        boolean isExistBeforeDelete = clientRepository.findById(clientResult.getId()).isPresent();

        clientRepository.deleteById(clientResult.getId());

        boolean notExistAfterDelete = clientRepository.findById(clientResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
