package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class EngineCarDTO {
    private int ID;
    private String type_engine;
}
