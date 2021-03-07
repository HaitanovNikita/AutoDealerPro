package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.ColorCarDao;
import autodealer.com.logic.entity.ColorCar;
import autodealer.com.logic.repository.ColorCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Haitanov Nikita
 */
@Component
public class ColorCarDaoImpl implements ColorCarDao {

    private final ColorCarRepository repository;

    @Autowired
    public ColorCarDaoImpl(ColorCarRepository repository) {
        this.repository = repository;
    }


    @Override
    public ColorCar create(ColorCar object) {
        return repository.save(object);
    }

    @Override
    public ColorCar update(ColorCar object) {
        return repository.save(object);
    }

    @Override
    public void delete(ColorCar object) {
         repository.delete(object);
    }

    @Override
    public ColorCar find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ColorCar> read() {
        return (ArrayList<ColorCar>) repository.findAll();
    }
}
