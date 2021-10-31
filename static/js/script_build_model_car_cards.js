var xhr = new XMLHttpRequest();
let path = '/autodealer-apigateway/auto-dealer/modelCar/read'
xhr.open('GET', path, true);
xhr.setRequestHeader('Content-Type', 'application/json'); // Отправляем кодировку
xhr.send(path);
xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) {
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText + ' no answer');
        } else {
            console.log(xhr.responseText);
            let arrResponseText = JSON.parse(xhr.responseText);
            let i = 0;
            while (i < arrResponseText.length - 1) {
                let obj = Object.values(arrResponseText[i]);
                console.log('script build car new AutoModelCard(' + obj[1] + ', ' + obj[0] + ');')
                new AutoModelCard(obj[1], obj[0]);
                i += 1;
            }
        }
    }
}
var elements = document.querySelectorAll(".auto-card");
for (var i = 0; i < elements.length; i++) {
    elements[i].onclick = function () {

        let name_model = this.textContent.split(' ');
        let id_model_car = name_model[29].replace(/\s+/g, '');
        console.log("idModel=" + id_model_car);
        document.cookie = "idModel=" + id_model_car + "; path=/; expires='+" + new Date(Date.now() + 86400e3).toUTCString;
        console.log(document.cookie);
        window.location.href = '/specificModelCar.html';
    };
}
