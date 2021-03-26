class Auto{

    constructor(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body ){
        this.ID = ID;
        this.car_make = car_make;
        this.car_price = car_price;
        this.color_car = color_car;
        this.engine_car = engine_car;
        this.model_car = model_car;
        this.power_car = power_car;
        this.type_car_body = type_car_body;
        this.year_issue_car = year_issue_car;
        document.getElementById('cars-container').innerHTML+=this.auto();
    }

    auto(){
        return `<div class="car-container">
        <img class="card-img-top text-monospace" alt="Audi `+this.model_car+`" src="images/auto-cards/`+this.ID+`.jpg">
        <div class="card-body">
            <h5 class="card-title text-monospace">`+this.car_make+'  '+this.model_car+ ` <span class="badge badge-dark">Дата випуску:`+this.year_issue_car+`</span> </h5>
            <h6 class="card-subtitle mb-2 text-muted text-monospace">`+this.type_car_body+`</h6>
            <p class="card-text card-title">Двигун: `+this.engine_car+`</p>
            <p class="card-text card-title">Потужність: `+this.power_car+` к.с.</p>
            <p class="card-text card-title">Ціна: `+this.car_price+` $</p>
            <p class="card-text card-title">Колір: `+this.color_car+`</p>
        </div>
        <div class="line-card"></div >
    </div>`;
    }
}
 



