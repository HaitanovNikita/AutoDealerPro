package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ColorCarDTO {
    private Long ID;
    private String color_car;
}
