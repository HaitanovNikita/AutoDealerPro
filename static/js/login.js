class RegistryInSystem {

    constructor(view) {
        document.getElementById("placeRegist").style.display = "none";
        this.initUI(view);
    }


    initUI(view) {

        
        this.localPath = location.href;

        view.regInSystem.onclick = function () {
            document.getElementById("placeLogin").style.display = "none";
            document.getElementById("placeRegist").style.display = "";
        };

        view.logInSystem.addEventListener("click",  () => {
            const id = 'logIn';
            const log = document.getElementById("login_enter").value;
            const pass = document.getElementById("pass_enter").value;
            var xhr = new XMLHttpRequest();

            let query = '/login/?login=' + log + '&password=' + pass + '&id=' + id;
            xhr.open('POST', query, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
            xhr.send(query);
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) { // Ответ пришёл
                    if (xhr.status == 200) { // Успішний вхід в систему!
                        console.log(xhr.responseText);
                        let arrResponseTextSplit = xhr.responseText.split(" ");
                        let lname = arrResponseTextSplit[6];
                        let fname = arrResponseTextSplit[7];
                        let post = arrResponseTextSplit[8];
                        let email = arrResponseTextSplit[9];
                        
                        document.cookie = "name="+lname+' '+fname+";  path=/; max-age=3600";
                        document.cookie = "post="+post+";  path=/; max-age=3600";
                        document.cookie = "email="+email+";  path=/; max-age=3600";
                        let date = new Date();
                        document.cookie = "time="+date.getHours()+':'+date.getMinutes()+";  path=/; max-age=3600";
                        document.cookie = "date="+date.toDateString()+";  path=/; max-age=3600";
                        
                        window.location.href = "/controlPanel.html";
                    } else if(xhr.status == 401){//Вхід в систему не успішний!
                        alert(xhr.responseText)
                    }else {
                        alert(xhr.status + ': ' + xhr.statusText);//Если код не 200
                    }
                }
            }
        });
 

    view.regist.onclick = function () {

        let fnameRegist = document.getElementById('fname_regist').value;
        let lnameRegist = document.getElementById('lname_regist').value;
        let emailRegist = document.getElementById('position_regist').value;
        let registLogin = document.getElementById('login_regist').value;
        let registPassword = document.getElementById('pass_regist').value;

    
        const id = 'registration';

        var xhr = new XMLHttpRequest();
        
        let query = '/login/?login=' + registLogin + '&password=' + registPassword + '&id=' + id +'&fname='+fnameRegist+'&lname='+lnameRegist+'&email='+emailRegist;
        xhr.open('POST', query, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xhr.send(query);
        xhr.onreadystatechange = () => { // Ждём ответа от сервера
            if (xhr.readyState == 4) { 
                if (xhr.status == 200) { 
                    alert(xhr.responseText);
                    let arrResponseTextSplit = xhr.responseText.split(" ");
                    for(let index in arrResponseTextSplit){
                        console.log(arrResponseTextSplit[index]);    
                    }
                    if(false !== xhr.responseText.indexOf('Успішна регістрація')){
                        console.log("Зашли в if");
                    }else if(false !== xhr.responseText.indexOf('Ви вже зареєстровані в системі !!!')){
                        alert(xhr.responseText);
                    }else if(false !== xhr.responseText.indexOf('Невдала спроба реєстрації!')){
                        alert(xhr.responseText);
                    }

                    alert(xhr.status + ': ' + xhr.statusText);//если код не 200   
                } else if(xhr.status == 401){//Невдала спроба реєстрації
                    alert(xhr.responseText);
                } else if(xhr.status == 406){//Ви вже зареєстровані в системі
                    alert(xhr.responseText);
                }
            }
        };

    };


}
}