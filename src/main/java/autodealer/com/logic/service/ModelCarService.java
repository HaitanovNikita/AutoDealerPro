package autodealer.com.logic.service;

import autodealer.com.logic.entity.ModelCar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface ModelCarService extends AutoDealerCRUDService<ModelCar> {

    ModelCar update(ModelCar object);

    ModelCar findById(Long id);
}
