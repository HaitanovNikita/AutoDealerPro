package autodealer.com.logic.service.impl;

import autodealer.com.logic.dto.AutomobileDTO;
import autodealer.com.logic.service.AutoDealerCRUDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AutomobileService implements AutoDealerCRUDService<AutomobileDTO> {


    @Override
    public void create(AutomobileDTO automobileDTO) {

    }

    @Override
    public void update(AutomobileDTO automobileDTO) {

    }

    @Override
    public void delete(AutomobileDTO automobileDTO) {

    }

    @Override
    public List<AutomobileDTO> readAll() {
        return null;
    }
}
