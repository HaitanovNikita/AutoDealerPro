let btnIconChatBot = document.getElementById('icon-chat-bot');
let chatContainer = document.getElementById('chat-container');
let chat = document.getElementById('chat');
let chatAnswerContainer = document.getElementById('answer-container');
let btnHideChat = document.getElementById('hide-chat');
let body = document.getElementById('body');
let sendMessageBtn = document.getElementById('send_message_btn');
let arr = new Array();
chatContainer.hidden = true;
let section = 1;

function sendRequest(id) {
    console.log("function sendRequest(id)");
    var xhr = new XMLHttpRequest();
    let path = '/autodealer-apigateway/chat-auto-dealer/process/integrationData/get/' + id;
    xhr.open('POST', path, true);
    xhr.setRequestHeader('Access-Control-Allow-Credentials', 'true');
    xhr.send(path);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText + ' no answer');
            } else {
                chatAnswerContainer.innerHTML = ``;
                console.log(xhr.responseText);
                let arrResponseText = JSON.parse(xhr.responseText);
                let dataForInner = ``, dataTemplateAnswerForInner = ``, answerId = "", i = 0;
                while (i < arrResponseText.length) {
                    let obj = Object.values(arrResponseText[i]);
                    dataForInner = ``;
                    dataTemplateAnswerForInner = ``;
                    /*
                    * obj[0] - id
                    * obj[1] - answerId
                    * obj[2] - section
                    * obj[3] - text
                    * */
                    answerId = obj[0] + '.' + obj[1] + '.' + obj[2];
                    if (i === 0) {
                        let firstQuestion = obj[3];
                        dataForInner += `<div class="chat__message question"> ` + firstQuestion + ` (Необхідно обрати відповідь)</div>`;
                    } else {
                        let answerLength = obj[3].length;
                        if (answerLength >= 100) {
                            dataForInner += `<div class="chat__message question"> ` + obj[1] + ") " + obj[3] + `</div>`;
                            dataTemplateAnswerForInner += `<div id=` + answerId + ` class="chat__point" value="` + obj[1] + `)">` + obj[1] + `) </div> `;
                        } else {
                            dataTemplateAnswerForInner += `<div id=` + answerId + ` class="chat__point" value="` + obj[3] + `">` + obj[3] + ` </div> `;
                        }
                    }
                    chatAnswerContainer.innerHTML += dataTemplateAnswerForInner;
                    chat.innerHTML += dataForInner;
                    chat.scrollTop = chat.scrollHeight;
                    let element = document.getElementById(answerId);
                    arr.push(element);
                    i += 1;
                }
            }
        }
    }
}


function sendResponse(id) {
    var xhr = new XMLHttpRequest();
    let path = '/autodealer-apigateway/chat-auto-dealer/process/integrationData/set/' + id;
    xhr.open('POST', path, true);
    xhr.setRequestHeader('Access-Control-Allow-Credentials', 'true');
    xhr.send(path);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText + ' no answer');
            } else {
                let responseText = JSON.parse(xhr.responseText);
                let obj = Object.values(responseText);
                if (obj[1] != 0) {
                    sendRequest(obj[1]);
                } else {
                    chat.innerHTML += `<div class="chat__message question"> ` + obj[3] + `</div>`;
                    chatAnswerContainer.innerHTML = ``;
                    chat.scrollTop = chat.scrollHeight;
                }
            }
        }
    }
}

body.addEventListener('click', event => {
    if (arr != null) {
        arr.forEach(elem => {
            if (elem != null) {
                if (event.target.id == elem.id) {
                    chat.innerHTML += `<div class="chat__message answer"> ` + elem.textContent + ` </div>`;
                    sendResponse(elem.id);
                    arr = new Array();
                }
            }
        });
    }
});

sendMessageBtn.onclick = function () {
    let inputAnswer = document.getElementById("inputAnswer");
    console.log("function sendResponse(id)");
    var xhr = new XMLHttpRequest();
    let sectionId = section - 1;
    let path = '/autodealer-apigateway/chat-auto-dealer/process/integrationData/check/answer/' + inputAnswer.value + '/id/' + sectionId;
    xhr.open('POST', path, true);
    xhr.setRequestHeader('Access-Control-Allow-Credentials', 'true');
    xhr.send(path);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText + ' no answer');
            } else {
                console.log("xhr.responseText: " + xhr.responseText);
                if (xhr.responseText != -1) {
                    chat.innerHTML += `<div class="chat__message answer"> ` + inputAnswer.value + ` </div>`;
                    let answerId = xhr.responseText;
                    let id = "0." + answerId + "." + sectionId;
                    sendResponse(id);
                } else {
                    inputAnswer.value = "";
                    chat.innerHTML += `<div class="chat__message question"> Нажаль, я не розумію вашу відповідь. Оберіть одну з запропонованих відповідей.</div>`;
                    chat.scrollTop = chat.scrollHeight;
                }
            }
        }
    }
};


btnIconChatBot.onclick = function () {
    if (btnIconChatBot.hidden === false) {
        chatContainer.hidden = false;
        section = 1;
        chatAnswerContainer.innerHTML = ``;
        chat.innerHTML = ``;
        sendRequest(section++);
    }
};

btnHideChat.onclick = function () {
    chatContainer.hidden = true;
};



