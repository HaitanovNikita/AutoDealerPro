package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class CarSaleDTO {
    private int ID;
    private int ID_car;
    private int ID_client;
    private int ID_manager;
    private String date_sale_car;
    private String type_pay;

}
