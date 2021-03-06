package autodealer.com.logic.dao.converter;

import autodealer.com.logic.dto.AutomobileDTO;
import autodealer.com.logic.entity.Automobile;

public class Converter {

     public static Automobile convertDtoToEntity(AutomobileDTO automobileDTO){
        return Automobile
                .builder()
//                .model_carString(automobileDTO.model_carString)
//                .engine_carString(automobileDTO.engine_carString)
//                .color_carString(automobileDTO.color_carString)
//                .type_car_bodyString(automobileDTO.type_car_bodyString)
                .ID(automobileDTO.getID())
                .car_price(automobileDTO.getCar_price())
                .car_make(automobileDTO.getCar_make())
                .model_car(automobileDTO.getModel_car())
                .year_issue_car(automobileDTO.getYear_issue_car())
                .power_car(automobileDTO.getPower_car())
                .engine_car(automobileDTO.getEngine_car())
                .color_car(automobileDTO.getColor_car())
                .type_car_body(automobileDTO.getType_car_body())
                .build();
     }
    public static AutomobileDTO convertEntityToDto(Automobile automobile){
         return AutomobileDTO
                 .builder()
//                 .model_carString(automobile.model_carString)
//                 .engine_carString(automobile.engine_carString)
//                 .color_carString(automobile.color_carString)
//                 .type_car_bodyString(automobile.type_car_bodyString)
                 .ID(automobile.getId())
                 .car_price(automobile.getCar_price())
                 .car_make(automobile.getCar_make())
                 .model_car(automobile.getModel_car())
                 .year_issue_car(automobile.getYear_issue_car())
                 .power_car((int)automobile.getPower_car())
                 .engine_car(automobile.getEngine_car())
                 .color_car(automobile.getColor_car())
                 .type_car_body(automobile.getType_car_body())
                 .build();
     }
}
