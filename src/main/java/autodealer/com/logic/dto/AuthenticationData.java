package autodealer.com.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nhaitanov
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationData {

    private String login;
    private String password;

}
