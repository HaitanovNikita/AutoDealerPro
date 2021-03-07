package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PowerCarDTO {
    private Long ID;
    private int horse_power;
}
