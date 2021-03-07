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
@Table(name = "ModelCar")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ModelCar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;
    @Column(name = "name_model")
    private String name_model;

    @OneToMany(mappedBy = "model_car",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Automobile> automobileList = new ArrayList<>();
}
