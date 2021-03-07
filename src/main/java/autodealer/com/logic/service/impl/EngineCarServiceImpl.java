package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.EngineCarDao;
import autodealer.com.logic.entity.EngineCar;
import autodealer.com.logic.service.EngineCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EngineCarServiceImpl implements EngineCarService {

    @Autowired
    private EngineCarDao engineCarDao;

    @Override
    public EngineCar update(EngineCar object) {
        return engineCarDao.update(object);
    }

    @Override
    public EngineCar findById(Long id) {
        return engineCarDao.find(id);
    }

    @Override
    public EngineCar create(EngineCar engineCar) {
        return engineCarDao.create(engineCar);
    }

    @Override
    public void delete(EngineCar engineCar) {
        engineCarDao.delete(engineCar);
    }

    @Override
    public List<EngineCar> readAll() {
        return engineCarDao.read();
    }
}
