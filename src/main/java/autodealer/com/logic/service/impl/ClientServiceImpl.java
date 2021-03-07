package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.ClientDao;
import autodealer.com.logic.dao.converter.Converter;
import autodealer.com.logic.dto.ClientDTO;
import autodealer.com.logic.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static autodealer.com.logic.dao.converter.Converter.convertDtoToEntity;
import static autodealer.com.logic.dao.converter.Converter.convertEntityToDto;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public ClientDTO updateClient(ClientDTO client) {
        return convertEntityToDto(clientDao.updateClient(convertDtoToEntity(client)));
    }

    @Override
    public void deleteByID(Integer id) {
        clientDao.deleteByID(id);
    }


    @Override
    public ClientDTO findClient(Integer idClient) {
        return convertEntityToDto(clientDao.findClient(idClient));
    }

    @Override
    public ClientDTO findByClient_phone_num(String phoneNum) {
        return Converter.convertEntityToDto(clientDao.findByClient_phone_num(phoneNum));
    }

    @Override
    public ClientDTO create(ClientDTO client) {
        return convertEntityToDto(clientDao.createClient(convertDtoToEntity(client)));
    }

    @Override
    public void delete(ClientDTO client) {
        clientDao.deleteClient(convertDtoToEntity(client));
    }

    @Override
    public List<ClientDTO> readAll() {
        return clientDao.readAllClients()
                .stream()
                .map(Converter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
