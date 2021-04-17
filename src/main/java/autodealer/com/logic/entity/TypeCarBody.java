package autodealer.com.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TypeCarBody")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class TypeCarBody implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;
    @Column(name = "type_body")
    private String type_body;

    @OneToMany(mappedBy = "type_car_body",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Automobile> automobileList = new ArrayList<>();
}
