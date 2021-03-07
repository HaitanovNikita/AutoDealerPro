package autodealer.com.logic.service;

import autodealer.com.logic.dto.ClientDTO;

public interface ClientService extends AutoDealerCRUDService<ClientDTO> {

    ClientDTO updateClient(ClientDTO client);

    void deleteByID(Integer id);

    ClientDTO findClient(Integer idClient);

    ClientDTO findByClient_phone_num(String phoneNum);

}
