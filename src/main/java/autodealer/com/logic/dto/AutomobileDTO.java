package autodealer.com.logic.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AutomobileDTO {

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
    private long model_car;
    private long engine_car;
    private long color_car;
    private long type_car_body;

}
