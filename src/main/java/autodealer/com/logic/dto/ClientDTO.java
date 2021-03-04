package autodealer.com.logic.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private int ID;
    private String client_fname;
    private String client_lname;
    private String client_phone_num;
    private int client_age ;
    private String client_email;
    private String client_gender;

}
