package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ModelCarDTO {
    private Long ID;
    private String name_model;
    private String section;
}
