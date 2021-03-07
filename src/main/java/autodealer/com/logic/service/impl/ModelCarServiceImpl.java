package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.ModelCarDao;
import autodealer.com.logic.entity.ModelCar;
import autodealer.com.logic.service.ModelCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelCarServiceImpl implements ModelCarService {

    @Autowired
    private ModelCarDao modelCarDao;

    @Override
    public ModelCar update(ModelCar object) {
        return modelCarDao.update(object);
    }

    @Override
    public ModelCar findById(Long id) {
        return modelCarDao.find(id);
    }

    @Override
    public ModelCar create(ModelCar modelCar) {
        return modelCarDao.create(modelCar);
    }

    @Override
    public void delete(ModelCar modelCar) {
         modelCarDao.delete(modelCar);
    }

    @Override
    public List<ModelCar> readAll() {
        return modelCarDao.read();
    }
}
