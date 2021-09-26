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
public class AutomobileDaoImpl implements AutomobileDao {

    private final AutomobileRepository automobileRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AutomobileDaoImpl(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    @Override
    public Automobile save(Automobile automobile) {
        automobileRepository.save(automobile);
        return automobile;
    }

    @Override
    public List<Automobile> readAll() {
        return (ArrayList) automobileRepository.findAll();
    }

    @Override
    public Automobile getMostPopularAuto() {
        Long idMostPopularAuto = entityManager.createQuery("Select count(cs.ID_car) from CarSale as cs group by cs.ID_car order by count(cs.ID_car) DESC", Long.class).getResultList().get(0);
        Integer idAuto = entityManager.createQuery("Select cs.ID_car from CarSale as cs group by ID_car having count(cs.ID_car) = " + idMostPopularAuto, Integer.class).getResultList().get(0);
        return findById(Long.valueOf(idAuto));
    }

    @Override
    public void deleteByID(long id) {
        automobileRepository.deleteById(id);
    }

    @Override
    public Automobile findByAuto(Integer modelCar, Integer powerCar, Integer engineCar, Integer colorCar, Integer typeCarBody) {
        String query = "Select " + "aut.id " +
                " from Automobile " + "as aut  " +
                " inner join ModelCar as mc on aut.model_car = mc.id " +
                " inner join PowerCar as pc on  aut.power_car = pc.id " +
                " inner join EngineCar as ec on  aut.engine_car = ec.id " +
                " inner join ColorCar as cc on aut.color_car = cc.id " +
                " inner join TypeCarBody as tcb on aut.type_car_body= tcb.id " +
                " where " +
                "aut.color_car = " + colorCar +
                " and aut.engine_car = " + engineCar +
                " and aut.power_car = " + powerCar +
                " and aut.model_car = " + modelCar +
                " and aut.type_car_body = " + typeCarBody;
        Long id = entityManager.createQuery(query, Long.class).getResultList().get(0);
        Automobile byId = findById(id);
        return byId;
    }

    @Override
    public Automobile update(long newPrice, long id) {
        String query = new StringBuilder().append("UPDATE Automobile as a set a.car_price = ").append(newPrice).append(" where a.id = ").append(id).toString();
        entityManager.createQuery(query).executeUpdate();
        return findById(id);
    }

    @Override
    public String getSalesProfitForTheGap(String fromDate, String forDate) {
        String query = "Select sum(auto.car_price) from CarSale as cs inner join Automobile as auto on auto.id = cs.ID_car  where cs.date_sale_car between '" + fromDate + "' and '" + forDate + "'";
        return entityManager.createQuery(query).getSingleResult().toString();
    }

    @Override
    public Automobile findById(Long id) {
        return automobileRepository.findById(id).get();
    }

    @Override
    public List<Automobile> findByModelAuto(Long id) {
        String query = "Select " + "aut.id " +
                " from Automobile " + "as aut  " +
                " inner join ModelCar as mc on aut.model_car = mc.id " +
                " inner join PowerCar as pc on  aut.power_car = pc.id " +
                " inner join EngineCar as ec on  aut.engine_car = ec.id " +
                " inner join ColorCar as cc on aut.color_car = cc.id " +
                " inner join TypeCarBody as tcb on aut.type_car_body= tcb.id " +
                " where " +
                " aut.model_car = " + id;
        List<Long> resultList = entityManager.createQuery(query, Long.class).getResultList();
        List<Automobile> automobileList = new ArrayList<>();
        resultList.stream().forEach(idAuto -> automobileList.add(findById(idAuto)));
        return automobileList;
    }

}


