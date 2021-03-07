package autodealer.com.logic.repository;

import autodealer.com.logic.entity.ColorCar;
import autodealer.com.logic.entity.EngineCar;
import org.springframework.data.repository.CrudRepository;

public interface EngineCarRepository extends CrudRepository<EngineCar, Long> {
}
