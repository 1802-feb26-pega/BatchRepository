window.onload = function()
{
    loadLanding();
}

function loadLanding()
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadhome" , true);
    xhr.send();

    xhr.onreadystatechange = function()
    {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            $('#view').html(xhr.responseText);

            // Login
            $('#login').on('click', logIn);

            // Register
        }
    }
}

function logIn()
{
    var email = $('#email').val();
    var password = $('#pass').val();

    //console.log(email);
    //console.log(password);

    var toSend = [email, password];

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "login", true);
    xhr.send(JSON.stringify(toSend));
}