var btnOption = "controlBtnAuto";// контрольные кнопки выбора: Авто,Менеджер, Клиент

var colorActive = '#4169e1';
var colorNotActive = '#6c757d';

function queryToServer(operation) {

    var xhr = new XMLHttpRequest();
    let path = operation;
    xhr.open('GET', path, true);
    xhr.setRequestHeader('Content-Type', 'application/json'); // Отправляем кодировку
    xhr.send(path);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) { // Ответ пришёл
            if (xhr.status == 200) { // Успішний вхід в систему!
                console.log("Result operation: " + operation + " : " + xhr.responseText);
                let arrResponseText = xhr.responseText.split(" ");
                if (operation.includes('/manager/read')) {
                    console.log("if|getAllManager");
                    addTable(arrResponseText, 'manager');
                } else if (operation.includes('/client/read')) {
                    console.log("if|getAllClient");
                    addTable(arrResponseText, 'client');
                } else if (operation.includes('/auto/profit')) {
                    let res = xhr.responseText;
                    return res.substring(1);
                } else if (operation.includes('/auto/mostPopularAuto')) {
                    // console.log("xhr.responseText: " + xhr.responseText);
                    // console.log("JSON.parse(): " +);
                    return JSON.parse(xhr.responseText);
                }
            } else if (xhr.status == 401) {
                alert(xhr.responseText)
            } else {
                alert(xhr.status + ': ' + xhr.statusText);//Если код не 200
            }
        }
    }
}

function addTable(arrResultData, type) {
    let arrHeadersTable;
    let countColumns, countRows;
    if (arrResultData != undefined) {
        if (type === 'manager') {
            arrHeadersTable = new Array("Персональний номер", "Ім'я", "Прізвище", "Номер телефону", "Вік", "Пошта", "Стать", "Посада");
            countColumns = 8;
            var delimeter = 10;
            arrResultData = JSON.parse(arrResultData);
            countRows = ((arrResultData.length - 1) / delimeter) * 10 + 1;
            var headerTable = '<table class="table table-sm table-inverse"><thead><tr><th scope="row">Персональний номер</th><th scope="row">Ім\'я</th><th scope="row">Прізвище</th><th scope="row">Посада</th><th scope="row">Номер телефону</th><th scope="row">Вік</th><th scope="row">Пошта</th><th scope="row">Стать</th></tr></thead>';
        } else if (type === 'client') {
            arrHeadersTable = new Array("Персональний номер", "Ім'я", "Прізвище", "Номер телефону", "Вік", "Пошта", "Стать");
            countColumns = 7;
            var delimeter = 10;
            arrResultData = JSON.parse(arrResultData);
            countRows = ((arrResultData.length - 1) / delimeter) * 10 + 1;
            var headerTable = '<table class="table table-striped table-inverse"><thead><tr><th scope="row">Персональний номер</th><th scope="row">Ім\'я</th><th scope="row">Прізвище</th><th scope="row">Номер телефону</th><th scope="row">Вік</th><th scope="row">Пошта</th><th scope="row">Стать</th></tr></thead>';
        }

        var body = document.querySelector("body"),
            columns = countColumns,
            tr = "",
            td = "",
            firstTable = document.querySelector("table");
        let table = document.createElement("table");

        table.innerHTML = headerTable;
        table.setAttribute("id", "table");
        table.setAttribute("class", "table table-striped table-inverse  ");
        table.setAttribute("align", "center");
        tbody = document.createElement("tbody");
        tbody.setAttribute("class", "table-hover");
        for (var i = 0; i < countRows; i++) {
            tr = document.createElement("tr");
            tr.setAttribute("class", "table-active");
            let object = Object.values(arrResultData[i]);
            for (var j = 0; j < columns; j++) {
                th = document.createElement("th");
                th.setAttribute("scope", "row");
                let text = document.createTextNode(object[j]);
                th.appendChild(text);
                tr.appendChild(th);
            }
            tbody.appendChild(tr);
            table.appendChild(tbody);
        }

        let container = document.getElementById('cars-container');
        container.innerHTML = "";
        if (firstTable == null) {
            return container.appendChild(table);
        } else {
            var newTable = container.appendChild(table);
            return container.replaceChild(newTable, firstTable);
        }
    } else {
    }
}


/*centr-cont*/
let autoBtn = document.getElementById("controlBtnAuto");
let staffBtn = document.getElementById("controlBtnStaff");
let clientBtn = document.getElementById("controlBtnClient");
autoBtn.onclick = function () {
    btnOption = 'controlBtnAuto';
    setToDefaultColorsControlBtn();
    hideAllOperationPanel();
    setToDefaultColorsOperationBtn();
    document.getElementById('viewAuto').style.backgroundColor = colorActive;
    autoBtn.style.backgroundColor = colorActive;
    document.getElementById('cars-container').innerHTML = "";
    new GenerateAuto('getAllAuto');
    // добавить вычитывание всех авто из базы данных и отрисовка по нажатию function queryToServer(query)  -- query - getAllAuto  
}

staffBtn.onclick = function () {
    btnOption = 'controlBtnStaff';
    setToDefaultColorsControlBtn();
    hideAllOperationPanel();
    setToDefaultColorsOperationBtn();
    document.getElementById('viewAuto').style.backgroundColor = colorActive;
    staffBtn.style.backgroundColor = colorActive;
    queryToServer('/manager/read');
    // добавить вычитывание всех менеджеров из базы данных и запись в таблицу по нажатию function queryToServer(query)  -- query - getAllManager
}

clientBtn.onclick = function () {
    btnOption = 'controlBtnClient';
    setToDefaultColorsControlBtn();
    hideAllOperationPanel();
    setToDefaultColorsOperationBtn();
    document.getElementById('viewAuto').style.backgroundColor = colorActive;
    clientBtn.style.backgroundColor = colorActive;
    queryToServer('/client/read');
    // добавить вычитывание всех клиентов из базы данных и запись в таблицу по нажатию function queryToServer(query)  -- query - getAllClient
}


// when loading from a page
pageLoadActions();

//////////////////////////controlBtnAuto   controlBtnStaff   controlBtnClient

function setToDefaultColorsControlBtn() {
    document.getElementById('controlBtnAuto').style.backgroundColor = colorNotActive;
    document.getElementById('controlBtnStaff').style.backgroundColor = colorNotActive;
    document.getElementById('controlBtnClient').style.backgroundColor = colorNotActive;
}

function setToDefaultColorsOperationBtn() {
    document.getElementById('changeAuto').style.backgroundColor = colorNotActive;
    document.getElementById('viewAuto').style.backgroundColor = colorNotActive;
    document.getElementById('searchAuto').style.backgroundColor = colorNotActive;
}

function hideAllOperationPanel() {
    hideAllSearchingPanel();
    hideAllChangingPanel();
}

function hideAllSearchingPanel() {
    document.getElementById('contSearcAuto').hidden = true;
    document.getElementById('containerSearchClient').hidden = true;
    document.getElementById('containerSearchManager').hidden = true;
}

function hideAllChangingPanel() {
    document.getElementById('containerChangeAuto').hidden = true;
    document.getElementById('containerChangeManager').hidden = true;
    document.getElementById('containerChangeClient').hidden = true;
}

function pageLoadActions() {
    document.getElementById('controlBtnAuto').style.backgroundColor = colorActive;
    document.getElementById('viewAuto').style.backgroundColor = colorActive;
    document.getElementById('contSearcAuto').hidden = true;
    document.getElementById('containerChangeAuto').hidden = true;
    document.getElementById('containerSearchManager').hidden = true;
    document.getElementById('containerChangeManager').hidden = true;
    document.getElementById('containerChangeClient').hidden = true;
    document.getElementById('containerSearchClient').hidden = true;
}

/*left-cont*/
let viewSalesProfitBtn = document.getElementById("viewSalesProfit");

viewSalesProfitBtn.onclick = function () {

    let inputState_from_period = document.getElementById("inputState_from_period").value;
    let inputState_for_period = document.getElementById("inputState_for_period").value;
    if (inputState_from_period == '' && inputState_for_period == '') {
        alert("Введіть проміжки дати! ");
    } else {
        let salesProfit = queryToServer('/auto/profit/fromDate/' + inputState_from_period + '/forDate/' + inputState_for_period);
        let textResponse = 'Загальний прибуток від продажів за період ' + inputState_from_period + " - " + inputState_for_period + ' складає: ' + salesProfit + ' $'
        document.getElementById('viewProfit').innerHTML = "";
        document.getElementById('viewProfit').append(textResponse);
    }

};


function getMostPopularAuto() {
    let auto = '';
    var xhr = new XMLHttpRequest();
    let path = '/auto/mostPopularAuto';
    xhr.open('GET', path, true);
    xhr.setRequestHeader('Content-Type', 'application/json'); // Отправляем кодировку
    xhr.send(path);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                auto = JSON.parse(xhr.responseText);
                console.log("data:" + auto);
                let containerMostPopularCar = document.getElementById("containerMostPopularModel");
                containerMostPopularCar.innerHTML = `
            <h4 class="font-weight-light"> Найбільш популярна марка авто по продажам в 2019 році</h4> 
            <br>
            <img id="mostPopularCar" src="images/` + auto.id + `.jpg">
            <h5 id="mostPopularMakeCar"class="font-weight-bold">` + auto.car_make + ` ` + auto.model_car + `</h5>
            <p id="mostPopularBodyCar"class="font-weight-light">Кузов: ` + auto.type_car_body + `</p>
            <p id="mostPopularEngineCar"class="font-weight-light">Тип двигуна: ` + auto.engine_car + `</p>
            <p id="mostPopularHPCar"class="font-weight-light">Потужність: ` + auto.power_car + `к.с.</p>
            <p id="mostPopularCarDateRelease"class="font-weight-light">Дата випуску: ` + auto.year_issue_car + `</p>`;
            }
        } else if (xhr.status == 401) {
            alert(xhr.responseText)
        } else {
            alert(xhr.status + ': ' + xhr.statusText);//Если код не 200
        }
    }
}

getMostPopularAuto();

/*/left-cont*/

/*right-cont*/
let name = Cookies.get('name');
let post = Cookies.get('post');
let email = Cookies.get('email');
let timeLogin = Cookies.get('time');
let dateLogin = Cookies.get('date');

console.log("Data manager: " + name + "  " + post + "  " + email);
if (name === undefined) {
    alert("Час сессії вийшов, необхідно повторно зайти в систему!")
    window.location.href = "/audiConnect.html";
}
document.getElementById('name_manager').append(name);
document.getElementById('post_manager').append('Посада: ' + post);
document.getElementById('email_manager').append('Пошта: ' + email);

let date = new Date();
document.getElementById('login_time').append('Час входу в систему: ' + timeLogin);
document.getElementById('login_day').append('Дата : ' + dateLogin);


/*left-cont*/
document.getElementById('containerChangeAuto').hidden = true;

let btnChange = document.getElementById('changeAuto');
let btnSearch = document.getElementById('searchAuto');
let btnView = document.getElementById('viewAuto');


let btnPerformOperation = document.getElementById('performOperBtn');

btnSearch.onclick = function () {
    setToDefaultColorsOperationBtn();
    hideAllOperationPanel();
    btnSearch.style.backgroundColor = colorActive;
    if (btnOption === 'controlBtnAuto') {
        document.getElementById('contSearcAuto').hidden = false;
    } else if (btnOption === 'controlBtnStaff') {
        document.getElementById('containerSearchManager').hidden = false;
    } else if (btnOption === 'controlBtnClient') {
        document.getElementById('containerSearchClient').hidden = false;
    }
};

btnChange.onclick = function () {
    setToDefaultColorsOperationBtn();
    hideAllOperationPanel();
    btnChange.style.backgroundColor = colorActive;
    if (btnOption === 'controlBtnAuto') {
        document.getElementById('containerChangeAuto').hidden = false;
    } else if (btnOption === 'controlBtnStaff') {
        document.getElementById('containerChangeManager').hidden = false;
    } else if (btnOption === 'controlBtnClient') {
        document.getElementById('containerChangeClient').hidden = false;
    }

};

btnPerformOperation.onclick = function () {

    document.getElementById("cars-container").innerHTML = '';
    let unique_id = '' + document.getElementById('inputState id_auto').value;
    unique_id = unique_id.substr(0, unique_id.length - 5);
    unique_id = unique_id.substr(3, unique_id.length);
    let model = document.getElementById('inputState model_auto').value;
    let engine = document.getElementById('inputState engine_car').value;
    let power = document.getElementById('inputState power_car').value;
    let body = document.getElementById('inputState car_body').value;
    let color = document.getElementById('inputState color_car').value;
    let car_make = 'Audi';
    let price = document.getElementById('inputState price_auto').value;
    let year_issue_car = document.getElementById('inputState year_issue_car').value;
    let xhr = new XMLHttpRequest();
    let query = '/api/get-client/?operation=UpdateAuto&id_auto=' + unique_id
        + '&price=' + price
        + '&car_make=' + car_make
        + '&model_auto=' + model
        + '&year_issue_car=' + year_issue_car
        + '&power_car=' + power
        + '&engine_car=' + engine
        + '&color_car=' + color
        + '&car_body=' + body;
    xhr.open('POST', query, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку                
    xhr.send(query);
    if (xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText + ' no answer');
    } else {
        console.log('Answer about update auto: ' + xhr.responseText);
        location.href = location.href;
        if (xhr.responseTextstr.indexOf('успішно')) {
            location.href = location.href;
        } else {
            alert(xhr.responseText);
        }
    }
}

btnView.onclick = function () {
    setToDefaultColorsOperationBtn();
    hideAllOperationPanel();
    btnView.style.backgroundColor = colorActive;
};

function getAllComponentsCar(nameComponent, containerElement) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/get-client/?operation=' + nameComponent, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку                
    xhr.send('/api/get-client/?operation=' + nameComponent);

    if (xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText + ' no answer');
    } else {
        console.log(xhr.responseText);
        let arrResponseText = xhr.responseText.split(" ");
        let i = 0;
        let construction = '';
        while (i < arrResponseText.length - 1) {
            new ComponentCar(arrResponseText[i], arrResponseText[i + 1], containerElement);
            i += 2;
        }
    }
}

getAllComponentsCar('getAllModelCars', 'inputState model_auto');
getAllComponentsCar('GetAllEngineCar', 'inputState engine_car');
getAllComponentsCar('GetAllPowerCar', 'inputState power_car');
getAllComponentsCar('GetAllTypeCarBody', 'inputState car_body');
getAllComponentsCar('GetAllColorCar', 'inputState color_car');



