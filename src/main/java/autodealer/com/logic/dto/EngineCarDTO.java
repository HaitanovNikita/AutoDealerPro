package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EngineCarDTO {
    private Long ID;
    private String type_engine;
}
