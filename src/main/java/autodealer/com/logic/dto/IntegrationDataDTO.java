package autodealer.com.logic.dto;

import lombok.*;

/**
 * @author nhaitanov
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class IntegrationDataDTO {
    private long id;
    private long answerId;
    private String text;
    private long section;

}
