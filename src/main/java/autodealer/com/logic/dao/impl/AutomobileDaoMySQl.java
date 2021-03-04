package autodealer.com.logic.dao.impl;

import autodealer.com.logic.dao.AutomobileDao;
import autodealer.com.logic.entity.*;
import autodealer.com.logic.utils.HibernateUtils;
import autodealer.com.logic.utils.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static autodealer.com.logic.utils.Utils.colorPrintln;

@Component
public class AutomobileDaoMySQl implements AutomobileDao {
    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    public AutomobileDaoMySQl(SessionFactory sessionFactory,Session session) {
        /*factory = HibernateUtils.getSessionFactory();
        session = factory.getCurrentSession();*/
        this.sessionFactory = sessionFactory;
        this.session = session;
    }

    @Override
    public ArrayList<Automobile> readAllAutomobiles() {
        ArrayList<Automobile> arrayListAutomobiles = null;
        try {
            session.getTransaction().begin();

            String sqlQuery = "Select " + " a.id, a.car_price, a.car_make, a.year_issue_car, "
                    + " p.horse_power, m.name_model, e.type_engine, c.color_car, t.type_body  "
                    + "	from Automobile as a " + "	inner join ModelCar as m on a.model_car = m.id "
                    + "	inner join PowerCar p on a.power_car = p.ID "
                    + " inner join EngineCar e on a.engine_car = e.ID "
                    + " inner join ColorCar c on a.color_car = c.ID "
                    + " inner join TypeCarBody  t on a.type_car_body = t.ID";

            Query<Object[]> query = session.createQuery(sqlQuery);
            ArrayList<Object[]> datasList = (ArrayList<Object[]>) query.getResultList();
            arrayListAutomobiles = convertToAutomobiles(datasList);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return arrayListAutomobiles;
    }

    @Override
    public ArrayList<Automobile> queryAboutAuto(String querySqlString) {
        ArrayList<Automobile> arrayListAutomobiles = null;
        try {
            session.getTransaction().begin();
            colorPrintln(querySqlString, 34);
            ArrayList<Object[]> datasList = (ArrayList<Object[]>) session.createQuery(querySqlString).getResultList();
            arrayListAutomobiles = convertToAutomobiles(datasList);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return arrayListAutomobiles;

    }

    @Override
    public void createAutomobile(Automobile automobile) {
        try {
            session.getTransaction().begin();
            session.persist(automobile);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Override
    public void updateAutomobile(Automobile automobile) {
        try {
            session.getTransaction().begin();
            session.update(automobile);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Override
    public void deleteAutomobile(Automobile automobile) {
        try {
            session.getTransaction().begin();
            session.delete(automobile);
            session.getTransaction().commit();

        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public ArrayList<Automobile> getMostPopularAuto() {
        int id = auxiliaryMethod1(auxiliaryMethod2());
        ArrayList<Automobile> automobileArrayList = null;
        try {
            session = HibernateUtils.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            ArrayList<Object[]> datasList = (ArrayList<Object[]>) session.createQuery(Utils.queryForMostPopularCar + id).getResultList();
            automobileArrayList = convertToAutomobiles(datasList);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return automobileArrayList;
    }

    private int auxiliaryMethod1(int id) {
        int result = 0;
        try {
            String query = Utils.auxiliaryQuery1 + id;
            session = HibernateUtils.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            colorPrintln("Query to 1: \n" + query, 34);
            Object[] array = session.createQuery(query).getResultList().toArray();
            result = Integer.parseInt(array[0].toString());
            colorPrintln("auxiliaryMethod1: " + Arrays.toString(array), 34);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        colorPrintln("auxiliaryMethod1: " + result, 34);
        return result;
    }

    private int auxiliaryMethod2() {
        int result = 0;
        try {
            session = HibernateUtils.getSessionFactory().getCurrentSession();
            session.getTransaction().begin();
            Object[] array = session.createQuery(Utils.auxiliaryQuery2).getResultList().toArray();
            result = Integer.parseInt(array[0].toString());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        colorPrintln("auxiliaryMethod2: " + result, 34);
        return result;
    }

    public String getSalesProfitForTheGap(String fromDate, String forDate) {
        String result = null;
        try {
            session.getTransaction().begin();
            String sqlQuery = "Select sum(auto.car_price) from CarSale as cs " +
                    "inner join Automobile as auto on auto.id = cs.ID_car " + " where cs.date_sale_car between '" + fromDate + "' and '" + forDate + "'";
            result = session.createQuery(sqlQuery).getResultList().toString();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return result;
    }

    private ArrayList<Automobile> convertToAutomobiles(ArrayList<Object[]> datasList)  {
        if (datasList.isEmpty() == true)
            throw new IllegalArgumentException("Collection is empty! AutomobileDaoMuSQl/createAutomobile()");
        ArrayList<Automobile> arrayListAutomobiles = new ArrayList<Automobile>();
        try {
            for (Object[] autoArr : datasList) {
                arrayListAutomobiles.add(
                        new Automobile(
                                Integer.valueOf(autoArr[0].toString()),
                                (long) Integer.valueOf(autoArr[1].toString()),
                                autoArr[2].toString(),
                                autoArr[3].toString(),
                                Integer.valueOf(autoArr[4].toString()),
                                autoArr[5].toString(),
                                autoArr[6].toString(),
                                autoArr[7].toString(),
                                autoArr[8].toString()));
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        }
        return arrayListAutomobiles;
    }
}


