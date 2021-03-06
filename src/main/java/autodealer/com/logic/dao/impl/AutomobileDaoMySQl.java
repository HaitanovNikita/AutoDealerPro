package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.AutomobileDao;
import autodealer.com.logic.entity.Automobile;
import autodealer.com.logic.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class AutomobileDaoMySQl implements AutomobileDao{

    @PersistenceContext
    private EntityManager entityManager;

    private final AutomobileRepository automobileRepository;

    @Autowired
    public AutomobileDaoMySQl(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    @Override
    public Automobile save(Automobile automobile) {
        automobileRepository.save(automobile);
        return automobile;
    }

    @Override
    public List<Automobile> findAll() {
        return (ArrayList) automobileRepository.findAll();
    }

    @Override
    public Automobile getMostPopularAuto() {

        Long id = entityManager.createQuery("select count(cs.ID_car) from CarSale as cs group by cs.ID_car order by count(cs.ID_car) DESC", Long.class).getSingleResult();
        Automobile res2 = entityManager.createQuery("Select cs.ID_car from CarSale as cs group by ID_car having count(cs.ID_car) = " + id, Automobile.class).getSingleResult();

        String queryForGetMostPopularAuto = "Select aut.id, aut.car_price, aut.car_make, aut.year_issue_car, pc.horse_power, mc.name_model, ec.type_engine, cc.color_car, tcb.type_body from Automobile as aut inner join ModelCar as mc on aut.model_car = mc.ID inner join PowerCar as pc on  aut.power_car = pc.ID inner join EngineCar as ec on  aut.engine_car = ec.ID inner join ColorCar as cc on aut.color_car = cc.ID inner join TypeCarBody as tcb on aut.type_car_body= tcb.ID where aut.id =";
        Automobile automobile = entityManager.createQuery(queryForGetMostPopularAuto + res2.getId(), Automobile.class).getSingleResult();
        return automobile;
    }

    @Override
    public void deleteByID(long id) {
        automobileRepository.deleteByID(id);
    }

    @Override
    public String getSalesProfitForTheGap(String fromDate, String forDate) {
        String query = "Select sum(auto.car_price) from CarSale as cs inner join Automobile as auto on auto.id = cs.ID_car  where cs.date_sale_car between ':fromDate' and ':forDate'";
        String profit = entityManager.createQuery(query).getSingleResult().toString();
        return profit;
    }

    @Override
    public Automobile findById(Long id) {
        return automobileRepository.findById(id).get();
    }

    @Override
    public Automobile findByYear_issue_car(String yearIssueCar) {
        return automobileRepository.findByYear_issue_car(yearIssueCar).get();
    }



}


