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
@Table(name = "PowerCar")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PowerCar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int ID;
    @Column(name = "horse_power")
    private int horse_power;

    @OneToMany(mappedBy = "power_car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Automobile> automobileList = new ArrayList<>();
}
