package autodealer.com.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Automobile")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Automobile implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private long car_price;
    private String car_make;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_car")
    private ModelCar model_car;

    private String year_issue_car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "power_car")
    private PowerCar power_car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_car")
    private EngineCar engine_car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_car")
    private ColorCar color_car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_car_body")
    private TypeCarBody type_car_body;

}
