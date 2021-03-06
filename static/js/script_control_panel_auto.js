class Auto {

    constructor(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body, ) {
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
        <img class="card-img-top text-monospace" alt="Audi `+ this.model_car + `" src="images/auto-cards/` + this.ID + `.jpg">
        <div class="card-body">
            <h5 class="card-title text-monospace">`+ this.car_make + '  ' + this.model_car + ` <span class="badge badge-dark">Дата випуску:` + this.year_issue_car + `</span> </h5>
            <h6 class="card-subtitle mb-2 text-muted text-monospace">`+ this.type_car_body + `</h6>
            <p class="card-text card-title">Сесійний номер авто: `+ '000'+ this.ID + ''+this.getRandom(34567,56789) + `</p>
            <p class="card-text card-title">Двигун: `+ this.engine_car + `</p>
            <p class="card-text card-title">Потужність: `+ this.power_car + ` к.с.</p>
            <p class="card-text card-title">Ціна: `+ this.car_price + ` $</p>
            <p class="card-text card-title">Колір: `+ this.color_car + `</p>
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

        let query = '/api/get-client/?operation=' + this.operation;
        xhr.open('POST', query, false);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку                
        xhr.send(query);

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText + ' no answer');
        } else {
            let arrResponseText = xhr.responseText.split(" ");
            let i = 0;
            while (i < arrResponseText.length - 1) {
             this.addAuto(arrResponseText[i], arrResponseText[i + 2], arrResponseText[i + 1], arrResponseText[i + 8], arrResponseText[i + 6], arrResponseText[i + 5], arrResponseText[i + 4], arrResponseText[i + 3], arrResponseText[i + 7]);
                i += 9;
            }

        }
    }

    addAuto(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body) {
       return new Auto(ID, car_price, car_make, year_issue_car, power_car, model_car, engine_car, color_car, type_car_body);
    }

    
}












