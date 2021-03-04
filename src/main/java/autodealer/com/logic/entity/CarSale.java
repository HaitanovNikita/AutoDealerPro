package autodealer.com.logic.entity;
import javax.persistence.*;

@Entity
@Table(name="CarSale")
public class CarSale {

    private int ID;
    private int ID_car;
    private int ID_client;
    private int ID_manager;
    private String date_sale_car;
    private String type_pay;




    public CarSale(){

    }

    public CarSale(int ID, int ID_car, int ID_client, int ID_manager, String date_sale_car, String type_pay) {
        this.ID = ID;
        this.ID_car = ID_car;
        this.ID_client = ID_client;
        this.ID_manager = ID_manager;
        this.date_sale_car = date_sale_car;
        this.type_pay = type_pay;
    }

    @Id
    @JoinColumn(name="ID")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name="ID_car")
    public int getID_car() {
        return ID_car;
    }

    public void setID_car(int ID_car) {
        this.ID_car = ID_car;
    }

    @Column(name="ID_client")
    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    @Column(name="ID_manager")
    public int getID_manager() {
        return ID_manager;
    }


    public void setID_manager(int ID_manager) {
        this.ID_manager = ID_manager;
    }

    @Column(name="date_sale_car")
    public String getDate_sale_car() {
        return date_sale_car;
    }

    public void setDate_sale_car(String date_sale_car) {
        this.date_sale_car = date_sale_car;
    }

    @Column(name="type_pay")
    public String getType_pay() {
        return type_pay;
    }

    public void setType_pay(String type_pay) {
        this.type_pay = type_pay;
    }
}
