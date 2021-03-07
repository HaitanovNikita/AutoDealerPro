package autodealer.com.logic.service;

import autodealer.com.logic.entity.PowerCar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface PowerCarService extends AutoDealerCRUDService<PowerCar> {

    PowerCar update(PowerCar object);

    PowerCar findById(Long id);
}
