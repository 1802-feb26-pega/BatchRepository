window.onload = function()
{
    loadLanding();
}

function loadLanding()
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadlanding.view" , true);
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
    //$('#message').hide();
    //$('#username').on('click', $('#message').hide());
    var email = $('#email').val();
    var password = $('#pass').val();

    //console.log(email);
    //console.log(password);

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
                message = "You have entered the wrong username and/or password. Please try again";
                console.log("User is Null");
                $('#message').html(message);
            }
            else
            {
                //alert("success");
                loadNav();
                loadHome(user);
            }
        }
    }
}

function loadNav()
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadnav.view" , true);
    xhr.send();

    xhr.onreadystatechange = function()
    {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            $('#navbar').html(xhr.responseText);
            //$('#home').on('click',);
            $('#logout').on('click', logout);

            // Add Listeners to Nav Bar to go to various views and logout
        }
    }
}

function loadHome(user)
{
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadhome.view" , true);
    xhr.send();

    xhr.onreadystatechange = function()
    {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            $('#view').html(xhr.responseText);
            $('#name').html(user.firstname);
        }
    }
}

function logout()
{
    console.log("logging out");
    // here, we need to invalidate session and go back to login page
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "loadlanding.view" , true);
    xhr.send();
}