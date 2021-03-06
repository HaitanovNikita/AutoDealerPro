package autodealer.com.logic.utils;

public class Utils {

    protected static final String queryGetAllCars = "Select " +
            "a.id, a.car_price, a.car_make, a.year_issue_car, " +
            "p.horse_power, m.name_model, " +
            "e.type_engine, c.color_car, " +
            "t.type_body " +
            "from Automobile as a " +
            "inner join PowerCar as p on a.power_car = p.ID " +
            "inner join EngineCar as e on  a.engine_car = e.ID " +
            "inner join ColorCar as c on a.color_car = c.ID " +
            "inner join TypeCarBody as t on a.type_car_body= t.ID " +
            "inner join ModelCar as m on a.model_car = m.ID ";

    public static final String queryForMostPopularCar ="Select " +
            " aut.id, aut.car_price, aut.car_make, aut.year_issue_car, pc.horse_power, mc.name_model, " +
            " ec.type_engine, cc.color_car, " +
            " tcb.type_body " +
            " from Automobile as aut  " +
            " inner join ModelCar as mc on aut.model_car = mc.ID " +
            " inner join PowerCar as pc on  aut.power_car = pc.ID " +
            " inner join EngineCar as ec on  aut.engine_car = ec.ID " +
            " inner join ColorCar as cc on aut.color_car = cc.ID " +
            " inner join TypeCarBody as tcb on aut.type_car_body= tcb.ID " +
            " where aut.id = ";

    public static final String auxiliaryQuery1 ="Select cs.ID_car from CarSale as cs group by ID_car having count(cs.ID_car) = (select count(cs.ID_car) from CarSale as cs group by cs.ID_car order by count(cs.ID_car) DESC LIMIT 1) LIMIT 1";

    public static final String auxiliaryQuery2="select count(cs.ID_car) from CarSale as cs group by cs.ID_car order by count(cs.ID_car) DESC LIMIT 1";

    public static void colorPrintln(String dataForPrint, int color30To37){
        System.out.println((char)27 + "["+color30To37+"m" + dataForPrint);
    }
}
