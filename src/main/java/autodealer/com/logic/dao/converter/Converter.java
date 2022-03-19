package autodealer.com.logic.dao.converter;

import autodealer.com.logic.dto.*;
import autodealer.com.logic.entity.*;

public class Converter {

    public static ColorCar convertDtoToEntity(ColorCarDTO colorCarDTO) {
        return ColorCar.builder().ID(colorCarDTO.getID()).color_car(colorCarDTO.getColor_car()).build();
    }

    public static ColorCarDTO convertEntityToDto(ColorCar colorCar) {
        return ColorCarDTO.builder().ID(colorCar.getID()).color_car(colorCar.getColor_car()).build();
    }

    public static EngineCar convertDtoToEntity(EngineCarDTO engineCarDTO) {
        return EngineCar.builder().ID(engineCarDTO.getID()).type_engine(engineCarDTO.getType_engine()).build();
    }

    public static EngineCarDTO convertEntityToDto(EngineCar engineCar) {
        return EngineCarDTO.builder().ID(engineCar.getID()).type_engine(engineCar.getType_engine()).build();
    }

    public static ModelCar convertDtoToEntity(ModelCarDTO modelCarDTO) {
        return ModelCar.builder().ID(modelCarDTO.getID()).name_model(modelCarDTO.getName_model()).section(modelCarDTO.getSection()).build();
    }

    public static ModelCarDTO convertEntityToDto(ModelCar modelCar) {
        return ModelCarDTO.builder().ID(modelCar.getID()).name_model(modelCar.getName_model()).section(modelCar.getSection()).build();
    }

    public static PowerCar convertDtoToEntity(PowerCarDTO powerCarDTO) {
        return PowerCar.builder().ID(powerCarDTO.getID()).horse_power(powerCarDTO.getHorse_power()).build();
    }

    public static PowerCarDTO convertEntityToDto(PowerCar powerCar) {
        return PowerCarDTO.builder().ID(powerCar.getID()).horse_power(powerCar.getHorse_power()).build();
    }

    public static TypeCarBody convertDtoToEntity(TypeCarBodyDTO typeCarBodyDTO) {
        return TypeCarBody.builder().ID(typeCarBodyDTO.getID()).type_body(typeCarBodyDTO.getType_body()).build();
    }

    public static TypeCarBodyDTO convertEntityToDto(TypeCarBody typeCarBody) {
        return TypeCarBodyDTO.builder().ID(typeCarBody.getID()).type_body(typeCarBody.getType_body()).build();
    }

    public static Client convertDtoToEntity(ClientDTO clientDTO) {
        return Client
                .builder()
                .ID(clientDTO.getID())
                .age(clientDTO.getClient_age())
                .email(clientDTO.getClient_email())
                .fname(clientDTO.getClient_fname())
                .gender(clientDTO.getClient_gender())
                .lname(clientDTO.getClient_lname())
                .phone(clientDTO.getClient_phone_num())
                .build();
    }

    public static ClientDTO convertEntityToDto(Client client) {
        return ClientDTO
                .builder()
                .ID(client.getID())
                .client_phone_num(client.getPhone())
                .client_lname(client.getLname())
                .client_gender(client.getGender())
                .client_fname(client.getFname())
                .client_email(client.getEmail())
                .client_age(client.getAge())
                .build();
    }

    public static Automobile convertDtoToEntity(AutomobileDTO automobileDTO) {
        return Automobile
                .builder()
                .ID(automobileDTO.getID())
                .car_price(automobileDTO.getCar_price())
                .car_make(automobileDTO.getCar_make())
                .year_issue_car(automobileDTO.getYear_issue_car())
                .build();
    }

    public static AutomobileDTO convertEntityToDto(Automobile automobile) {
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

    public static IntegrationData convertDtoToEntity(IntegrationDataDTO integrationDataDTO) {
        return IntegrationData
                .builder()
                .answerId(integrationDataDTO.getAnswerId())
                .section(integrationDataDTO.getSection())
                .text(integrationDataDTO.getText())
                .id(integrationDataDTO.getId())
                .build();
    }

    public static IntegrationDataDTO convertEntityToDto(IntegrationData integrationData) {
        return IntegrationDataDTO
                .builder()
                .answerId(integrationData.getAnswerId())
                .section(integrationData.getSection())
                .text(integrationData.getText())
                .id(integrationData.getId())
                .build();
    }
}
