package autodealer.com.logic.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ManagerDTO {
    @NotNull
    private int ID;
    @NotNull
    private String manager_fname;
    @NotNull
    private String manager_lname;
    @NotNull
    private String manager_post;
    private String manager_phone_num;
    @NotNull
    private int manager_age;
    private String manager_email;
    @NotNull
    private String manager_gender;
    private String manager_login;
    private String manager_pass;
}
