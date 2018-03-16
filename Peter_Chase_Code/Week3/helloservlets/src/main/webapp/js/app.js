window.onload = loadLanding;

let emailInput;
let passwordInput;

function loadLanding() {
    ajax('GET', 'loadhome', (response) => {
        const view = document.getElementById('view');
        view.innerHTML = response;

        const btnLogin = document.getElementById('login');
        btnLogin.addEventListener('click', login);
    });
}

function login() {
    emailInput = emailInput || document.getElementById('email');
    passwordInput = passwordInput || document.getElementById('pass');
    const email = emailInput.value;
    const password = passwordInput.value;

    ajax('POST', 'login', (response) => {
        console.log(response);
    }, [email, password]);
}

function ajax(method, url, callback, sendItem) {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4 || xhr.status != 200) {
            return;
        }
        callback(xhr.responseText);
    };
    
    sendItem = sendItem || "";
    
    xhr.send(JSON.stringify(sendItem));
}

function ajaxJSON(method, url, callback, sendItem) {
    ajax(method, url, (response) => {
        callback(JSON.parse(response));
    }, sendItem);
}
