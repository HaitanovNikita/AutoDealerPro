package autodealer.com.logic.dao;

import autodealer.com.logic.entity.Automobile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Haitanov Nikita
 */
@Repository
public interface AutomobileDao {

    List<Automobile> readAll();

    Automobile findById(Long id);

    List<Automobile> findByModelAuto(Long id);

    List<Automobile> findBySectionModelAuto(Long section);

    Automobile save(Automobile automobile);

    Automobile getMostPopularAuto();

    String getSalesProfitForTheGap(String fromDate, String forDate);

    void deleteByID(long id);

    Automobile findByAuto(Integer modelCar, Integer powerCar, Integer engineCar, Integer colorCar, Integer typeCarBody);

    Automobile update(long newPrice, long id);
}
