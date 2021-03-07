package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.PowerCarDao;
import autodealer.com.logic.entity.PowerCar;
import autodealer.com.logic.service.PowerCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PowerCarServiceImpl implements PowerCarService {

    @Autowired
    private PowerCarDao powerCarDao;

    @Override
    public PowerCar update(PowerCar object) {
        return powerCarDao.update(object);
    }

    @Override
    public PowerCar findById(Long id) {
        return powerCarDao.find(id);
    }

    @Override
    public PowerCar create(PowerCar powerCar) {
        return powerCarDao.create(powerCar);
    }

    @Override
    public void delete(PowerCar powerCar) {
        powerCarDao.delete(powerCar);
    }

    @Override
    public List<PowerCar> readAll() {
        return powerCarDao.read();
    }
}
