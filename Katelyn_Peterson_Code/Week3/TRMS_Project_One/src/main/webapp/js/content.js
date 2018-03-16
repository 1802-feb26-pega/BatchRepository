// http://localhost:8099/TRMS_Project_One/index.html
window.onload = function()
{
    loadLogin();
}

function loadLogin()
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadLogin.view" , true);
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
    var password = $('#password').val();

    console.log(email);
    console.log(password);

    // Email Validation?

    var toSend = [email, password];

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "login", true);
    xhr.send(JSON.stringify(toSend));

    xhr.onreadystatechange = function()
    {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            var user = JSON.parse(xhr.responseText);
            var message = "";
            console.log(user);
            if(user == null)
            {
                $('#message').show();
                $('#message').attr("style", "display:inline");
                message = "You have entered the wrong email and/or password. Please try again";
                console.log("User is Null");
                $('#message').html(message);
            }
            else
            {
                //alert("success");
                //loadNav();
                //loadHome(user);
            }
        }
    }
}