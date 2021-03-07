package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.ClientDao;
import autodealer.com.logic.entity.Client;
import autodealer.com.logic.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Haitanov Nikita
 */
@Component
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final ClientRepository clientRepository;

    @Autowired
    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> readAllClients() {
        return (ArrayList<Client>) clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Long deleteClient(Client client) {
        clientRepository.delete(client);
        return Long.valueOf(client.getID());
    }

    @Override
    public void deleteByID(Integer id) {
        entityManager.createQuery("delete FROM Client where Client.ID = "+id);
    }


    @Override
    public Client findClient(Integer idClient) {
        return entityManager.createQuery("Select c FROM Client c where c.ID = " +idClient ,Client.class).getSingleResult();
    }


    @Override
    public Client findByClient_phone_num(String phoneNum) {
        return entityManager.createQuery("Select c FROM Client c where client_phone_num = '" + phoneNum + "'",Client.class).getSingleResult();
    }

}
