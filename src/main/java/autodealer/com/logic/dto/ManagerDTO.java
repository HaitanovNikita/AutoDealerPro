package autodealer.com.logic.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ManagerDTO {
    @NotNull
    private int id;
    @NotNull
    private String fname;
    @NotNull
    private String lname;
    @NotNull
    private String post;
    private String phone;
    @NotNull
    private int age;
    private String email;
    @NotNull
    private String gender;
    private String login;
    private String password;
}
