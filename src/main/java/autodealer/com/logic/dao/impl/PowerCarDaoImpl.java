package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.PowerCarDao;
import autodealer.com.logic.entity.PowerCar;
import autodealer.com.logic.repository.PowerCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** @author nhaitanov */
@Component
public class PowerCarDaoImpl implements PowerCarDao {

    @Autowired
    private PowerCarRepository repository;

    @Override
    public PowerCar create(PowerCar object) {
        return repository.save(object);
    }

    @Override
    public PowerCar update(PowerCar object) {
        return repository.save(object);
    }

    @Override
    public void delete(PowerCar object) {
         repository.delete(object);
    }

    @Override
    public PowerCar find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<PowerCar> read() {
        return (ArrayList<PowerCar>) repository.findAll();
    }
}
