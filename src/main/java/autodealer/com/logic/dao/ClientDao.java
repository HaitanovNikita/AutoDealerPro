package autodealer.com.logic.dao;

import autodealer.com.logic.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Haitanov Nikita
 */
@Repository
public interface ClientDao {

    List<Client> readAllClients();

    Client createClient(Client client);

    Client updateClient(Client client);

    Long deleteClient(Client client);

    void deleteByID(Integer id);

    Client findClient(Integer idClient);

    Client findByClient_phone_num(String phoneNum);

}
