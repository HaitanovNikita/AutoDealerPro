package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.ModelCarDao;
import autodealer.com.logic.entity.ModelCar;
import autodealer.com.logic.repository.ModelCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nhaitanov
 */
@Component
public class ModelCarDaoImpl implements ModelCarDao {

    @Autowired
    private ModelCarRepository repository;

    @Override
    public ModelCar create(ModelCar object) {
        return repository.save(object);
    }

    @Override
    public ModelCar update(ModelCar object) {
        return repository.save(object);
    }

    @Override
    public void delete(ModelCar object) {
         repository.delete(object);
    }

    @Override
    public ModelCar find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ModelCar> read() {
        return (ArrayList<ModelCar>) repository.findAll();
    }
}
