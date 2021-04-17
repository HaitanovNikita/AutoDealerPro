package autodealer.com.logic.dto;

import lombok.*;

/**
 * @author nhaitanov
 */
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class IntegrationDataDTO {
    private long id;
    private long answerId;
    private long section;
    private String text;

}
