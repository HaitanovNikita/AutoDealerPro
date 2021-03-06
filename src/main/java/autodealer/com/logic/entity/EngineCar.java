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
@Table(name = "EngineCar")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class EngineCar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int ID;
    @Column(name = "type_engine")
    private String type_engine;

    @OneToMany(mappedBy = "engine_car",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Automobile> automobileList = new ArrayList<>();
}
