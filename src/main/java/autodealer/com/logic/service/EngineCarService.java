package autodealer.com.logic.service;

import autodealer.com.logic.entity.EngineCar;

public interface EngineCarService  extends AutoDealerCRUDService<EngineCar>{
    EngineCar update(EngineCar object);

    EngineCar findById(Long id);
}
