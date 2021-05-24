package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.EngineCarDao;
import autodealer.com.logic.entity.EngineCar;
import autodealer.com.logic.repository.EngineCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Haitanov Nikita
 */
@Repository
public class EngineCarDaoImpl implements EngineCarDao {
    @Autowired
    private EngineCarRepository repository;

    @Override
    public EngineCar create(EngineCar object) {
        return repository.save(object);
    }

    @Override
    public EngineCar update(EngineCar object) {
        return repository.save(object);
    }

    @Override
    public void delete(EngineCar object) {
         repository.delete(object);
    }

    @Override
    public EngineCar find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<EngineCar> read() {
        return (ArrayList<EngineCar>) repository.findAll();
    }
}
