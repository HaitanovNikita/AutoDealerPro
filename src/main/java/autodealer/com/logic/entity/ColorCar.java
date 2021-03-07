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
@Table(name = "ColorCar")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ColorCar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long ID;
    @Column(name = "color_car")
    private String color_car;

    @OneToMany(mappedBy = "color_car",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Automobile> automobileList = new ArrayList<>();

}
