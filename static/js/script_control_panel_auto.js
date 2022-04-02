class Auto {

    constructor(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body,) {
        this.ID = ID;
        this.car_make = car_make;
        this.car_price = car_price;
        this.color_car = color_car;
        this.engine_car = engine_car;
        this.model_car = model_car;
        this.power_car = power_car;
        this.type_car_body = type_car_body;
        this.year_issue_car = year_issue_car;
        document.getElementById('cars-container').innerHTML += this.auto();
    }

    auto() {
        return `<div class="car-container">
        <img class="card-img-top text-monospace" alt="Audi ` + this.model_car + `" src="/autodealer-apigateway/auto-dealer/images/auto-cards/` + this.ID + `.jpg">
        <div class="card-body">
            <h5 class="card-title text-monospace">` + this.car_make + '  ' + this.model_car + ` <span class="badge badge-dark">Дата випуску:` + this.year_issue_car + `</span> </h5>
            <h6 class="card-subtitle mb-2 text-muted text-monospace">` + this.type_car_body + `</h6>
            <p class="card-text card-title">Сесійний номер авто: ` + '000' + this.ID + '' + this.getRandom(34567, 56789) + `</p>
            <p class="card-text card-title">Двигун: ` + this.engine_car + `</p>
            <p class="card-text card-title">Потужність: ` + this.power_car + ` к.с.</p>
            <p class="card-text card-title">Ціна: ` + this.car_price + ` $</p>
            <p class="card-text card-title">Колір: ` + this.color_car + `</p>
        </div>
        <div class="line-card"></div >
    </div>`;
    }

    getRandom(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min)) + min;
    }

}

class GenerateAuto {

    constructor(operation) {
        this.operation = operation;
        this.request();
    }

    request() {
        let xhr = new XMLHttpRequest();
        let query = '/autodealer-apigateway/auto-dealer/auto/' + this.operation;
        xhr.open('GET', query, true);
        xhr.setRequestHeader('Content-Type', 'application/json'); // Отправляем кодировку
        xhr.send(query);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) { // Ответ пришёл
                if (xhr.status == 200) { // Успішний вхід в систему!
                    let autos = JSON.parse(xhr.responseText);
                    let i = 0;
                    while (i < autos.length - 1) {
                        let auto = autos[i];
                        this.addAuto(auto.id, auto.car_price, auto.car_make, auto.year_issue_car, auto.power_car, auto.model_car, auto.engine_car, auto.color_car, auto.type_car_body);
                        i += 1;
                    }
                } else if (xhr.status == 401) {//Вхід в систему не успішний!
                    alert(xhr.responseText)
                } else {
                    alert(xhr.status + ': ' + xhr.statusText);//Если код не 200
                }
            }
        }
    }

    addAuto(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body) {
        return new Auto(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body);
    }


}












