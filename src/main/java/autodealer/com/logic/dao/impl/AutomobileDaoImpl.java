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
    public String getSalesProfitForTheGap(String fromDate, String forDate) {
        String query = "Select sum(auto.car_price) from CarSale as cs inner join Automobile as auto on auto.id = cs.ID_car  where cs.date_sale_car between '" + fromDate + "' and '" + forDate + "'";
        return entityManager.createQuery(query).getSingleResult().toString();
    }

    @Override
    public Automobile findById(Long id) {
        return automobileRepository.findById(id).get();
    }

}


