package autodealer.com.logic.service;

import autodealer.com.logic.dto.ManagerDTO;

import java.util.List;

/**
 * @author nhaitanov
 */
public interface ManagerService {

    List<ManagerDTO> readAll();

    ManagerDTO save(ManagerDTO managerDTO);

    ManagerDTO delete(ManagerDTO managerDTO);

    ManagerDTO findById(Long id);

    ManagerDTO findByLoginAndPassword(String login, String password);

    ManagerDTO findByEmail(String email);

    ManagerDTO findByPhone(String phone);
}
