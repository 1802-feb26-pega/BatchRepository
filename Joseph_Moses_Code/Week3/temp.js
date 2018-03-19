window.onload = function(){
    loadLandingPage();
}

function loadLanding(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadhome" , true);
    xhr.send();

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            $('#view').html(xhr.responseText);
            $('#login').on('click', login);
             //after text is loaded, add event listeners/functionality to view
        }
    }
}

function login(){
    var email = $('#email').val();
    var password = $('#password').val();

    var toSend = [email, password];
}