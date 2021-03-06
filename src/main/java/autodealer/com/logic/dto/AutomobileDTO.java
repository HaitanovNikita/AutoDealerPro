package autodealer.com.logic.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AutomobileDTO {

    @NotNull
    private long ID;
    @NotNull
    private long car_price;
    @NotNull
    private String car_make;
    @NotNull
    private String year_issue_car;
    @NotNull
    private int power_car;
    private String model_car;
    private String engine_car;
    private String color_car;
    private String type_car_body;

}
