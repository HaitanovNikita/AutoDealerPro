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
    private String fname;
    @Column(name = "client_lname")
    private String lname;
    @Column(name = "client_phone_num")
    private String phone;
    @Column(name = "client_age")
    private int age;
    @Column(name = "client_email")
    private String email;
    @Column(name = "client_gender")
    private String gender;

}
