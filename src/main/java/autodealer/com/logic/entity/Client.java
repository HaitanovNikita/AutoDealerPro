package autodealer.com.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Client")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Client {

    @Id
    @JoinColumn(name = "ID")
    private int ID;
    @Column(name = "client_fname")
    private String client_fname;
    @Column(name = "client_lname")
    private String client_lname;
    @Column(name = "client_phone_num")
    private String client_phone_num;
    @Column(name = "client_age")
    private int client_age;
    @Column(name = "client_email")
    private String client_email;
    @Column(name = "client_gender")
    private String client_gender;

}
