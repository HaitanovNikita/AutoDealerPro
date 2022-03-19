let btnIconChatBot = document.getElementById('icon-chat-bot');
let chatContainer = document.getElementById('chat-container');
let chat = document.getElementById('chat');
let chatAnswerContainer = document.getElementById('answer-container');
let btnHideChat = document.getElementById('hide-chat');
let body = document.getElementById('body');
let sendMessageBtn = document.getElementById('send_message_btn');
let arr = new Array();
let arrForSlider = new Array();
let arrSliderResult = new Array();
chatContainer.hidden = true;
let section = 0;

function sendRequest(id) {
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

                    let id = obj[0];
                    let defAnswerId = obj[1];
                    let section = obj[2];
                    let text = obj[3];

                    answerId = id + '.' + defAnswerId + '.' + section;
                    if (i === 0) {
                        if (section === 0) {
                            dataForInner += `<div class="chat__message question"> ` + text + ` (Необхідно обрати відповідь за допомогою повзунка та клікнути відправити)</div>`;
                            chat.style.height = '450px';
                            chatAnswerContainer.hidden=true;
                        } else {
                            dataForInner += `<div class="chat__message question"> ` + text + ` (Необхідно обрати відповідь)</div>`;
                            chat.style.height = '300px';
                            chatAnswerContainer.hidden=false;
                        }
                    } else {
                        if (section === 0) {
                            dataForInner += `<div class="chat__message question"> ` + text + `</div>`;
                            dataForInner += `<div class="chat__message answer">
                                <div class="slidecontainer">
                                    <input id = ` + answerId + ` type="range" min="1" max="100" value="50" class="slider">
                                </div>
                            </div>`;
                        } else {
                            let answerLength = text.length;
                            if (answerLength >= 100) {
                                dataForInner += `<div class="chat__message question"> ` + defAnswerId + ") " + text + `</div>`;
                                dataTemplateAnswerForInner += `<div id=` + answerId + ` class="chat__point" value="` + defAnswerId + `)">` + defAnswerId + `) </div> `;
                            } else {
                                dataTemplateAnswerForInner += `<div id=` + answerId + ` class="chat__point" value="` + text + `">` + text + ` </div> `;
                            }
                        }
                    }
                    chatAnswerContainer.innerHTML += dataTemplateAnswerForInner;
                    chat.innerHTML += dataForInner;
                    chat.scrollTop = chat.scrollHeight;
                    let element = document.getElementById(answerId);
                    if (section === 0) {
                        console.log('arrForSlider.push(element);' + element);
                        arrForSlider.push(element);
                    } else {
                        console.log('arr.push(element);');
                        arr.push(element);
                    }
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
    console.log("function sendResponse(id)");
    let sectionId = section - 1;
    console.log("sectionId: ", sectionId);
    if (sectionId !== 0) {
        var xhr = new XMLHttpRequest();
        console.log('if');
        let inputAnswer = document.getElementById("inputAnswer");
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
    } else {
        let resultString = '';
        arrForSlider.forEach((resultValue) => {
            if (resultValue != null) {
                resultString += resultValue.value + '&';
            }
        });
        let id = "0." + resultString + "." + sectionId;
        sendResponse(id);
        arrForSlider = null;
    }
};


btnIconChatBot.onclick = function () {
    if (btnIconChatBot.hidden === false) {
        chatContainer.hidden = false;
        section = 0;
        chatAnswerContainer.innerHTML = ``;
        chat.innerHTML = ``;
        sendRequest(section++);
    }
};

btnHideChat.onclick = function () {
    chatContainer.hidden = true;
};



