package autodealer.com.logic.dao;

import autodealer.com.logic.entity.Automobile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Haitanov Nikita
 */
@Repository
public interface AutomobileDao  {

    /*@Query("Select aut.id, aut.car_price, aut.car_make, aut.year_issue_car, pc.horse_power, mc.name_model, ec.type_engine, cc.color_car, tcb.type_body from Automobile as aut inner join ModelCar as mc on aut.model_car = mc.ID inner join PowerCar as pc on aut.power_car = pc.ID inner join EngineCar as ec on aut.engine_car = ec.ID inner join ColorCar as cc on aut.color_car = cc.ID inner join TypeCarBody as tcb on aut.type_car_body= tcb.ID where aut.id = (Select cs.ID_car from CarSale as cs group by ID_car having count(cs.ID_car) = (select count(cs.ID_car) from CarSale as cs group by cs.ID_car order by cs.ID_car) LIMIT 1)")
    Optional<Automobile> getMostPopularAuto();*/
    Automobile save(Automobile automobile);

    List<Automobile> findAll();

    Automobile getMostPopularAuto();

    void deleteByID(long id);

    String getSalesProfitForTheGap(String fromDate,String forDate);

    Automobile findById(Long id);

    Automobile findByYear_issue_car(String yearIssueCar);

}
