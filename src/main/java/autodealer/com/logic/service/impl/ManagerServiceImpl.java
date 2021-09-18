package autodealer.com.logic.service.impl;

import autodealer.com.logic.dto.ManagerDTO;
import autodealer.com.logic.entity.Manager;
import autodealer.com.logic.repository.ManagerRepository;
import autodealer.com.logic.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nhaitanov
 */
@Slf4j
@Transactional
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ManagerDTO> readAll() {
        return managerRepository
                .findAll()
                .stream()
                .map(manager -> mapper.map(manager, ManagerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ManagerDTO save(ManagerDTO manager) {
        return mapper.map(managerRepository.save(mapper.map(manager, Manager.class)), ManagerDTO.class);
    }

    @Override
    public ManagerDTO delete(ManagerDTO manager) {
        managerRepository.delete(mapper.map(manager, Manager.class));
        return manager;
    }

    @Override
    public ManagerDTO findById(Long id) {
        return mapper.map(managerRepository.findById(id), ManagerDTO.class);
    }

    @Override
    public ManagerDTO findByLoginAndPassword(String login, String password) {
        return mapper.map(managerRepository.findByLoginAndPassword(login, password), ManagerDTO.class);
    }

    @Override
    public ManagerDTO findByEmail(String email) {
        return mapper.map(managerRepository.findByEmail(email), ManagerDTO.class);
    }

    @Override
    public ManagerDTO findByPhone(String phone) {
        return mapper.map(managerRepository.findByPhone(phone), ManagerDTO.class);
    }
}
