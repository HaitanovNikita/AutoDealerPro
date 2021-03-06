package autodealer.com.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AutomobileModelDTO {

    public String model_carString;
    public String engine_carString;
    public String color_carString;
    public String type_car_bodyString;
    @NotNull
    private Integer ID;
    @NotNull
    private long car_price;
    @NotNull
    private String car_make;
    @NotNull
    private String year_issue_car;
    @NotNull
    private Integer power_car;
}
