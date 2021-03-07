package autodealer.com.logic.service;

import autodealer.com.logic.entity.ColorCar;


public interface ColorCarService extends AutoDealerCRUDService<ColorCar> {

    ColorCar update(ColorCar object);

    ColorCar findById(Long id);
}
