package autodealer.com.logic.service.impl;

import autodealer.com.logic.dao.ColorCarDao;
import autodealer.com.logic.entity.ColorCar;
import autodealer.com.logic.service.ColorCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ColorCarServiceImpl implements ColorCarService {

    @Autowired
    private ColorCarDao colorCarDao;

    @Override
    public ColorCar update(ColorCar object) {
        return colorCarDao.update(object);
    }

    @Override
    public ColorCar findById(Long id) {
        return colorCarDao.find(id);
    }

    @Override
    public ColorCar create(ColorCar colorCar) {
        return colorCarDao.create(colorCar);
    }

    @Override
    public void delete(ColorCar colorCar) {
        colorCarDao.delete(colorCar);
    }

    @Override
    public List<ColorCar> readAll() {
        return colorCarDao.read();
    }
}
