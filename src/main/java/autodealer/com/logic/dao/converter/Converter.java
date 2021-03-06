package autodealer.com.logic.dao.converter;

import autodealer.com.logic.dto.AutomobileDTO;
import autodealer.com.logic.entity.Automobile;

public class Converter {

     public static Automobile convertDtoToEntity(AutomobileDTO automobileDTO){
        return Automobile
                .builder()
                .ID(automobileDTO.getID())
                .car_price(automobileDTO.getCar_price())
                .car_make(automobileDTO.getCar_make())
                .year_issue_car(automobileDTO.getYear_issue_car())
                .build();
     }
    public static AutomobileDTO convertEntityToDto(Automobile automobile){
         return AutomobileDTO
                 .builder()
                 .ID(automobile.getID())
                 .car_price(automobile.getCar_price())
                 .car_make(automobile.getCar_make())
                 .model_car(automobile.getModel_car().getName_model())
                 .year_issue_car(automobile.getYear_issue_car())
                 .power_car(automobile.getPower_car().getHorse_power())
                 .engine_car(automobile.getEngine_car().getType_engine())
                 .color_car(automobile.getColor_car().getColor_car())
                 .type_car_body(automobile.getType_car_body().getType_body())
                 .build();
     }
}
