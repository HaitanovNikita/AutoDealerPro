package autodealer.com.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@Table(name = "Manager")
@Data
@Builder
@NoArgsConstructor
public class Manager implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "manager_fname")
    private String fname;
    @Column(name = "manager_lname")
    private String lname;
    @Column(name = "manager_post")
    private String post;
    @Column(name = "manager_phone_num")
    private String phone;
    @Column(name = "manager_age")
    private int age;
    @Column(name = "manager_email")
    private String email;
    @Column(name = "manager_gender")
    private String gender;
    @Column(name = "manager_login")
    private String login;
    @Column(name = "manager_pass")
    private String password;
}
