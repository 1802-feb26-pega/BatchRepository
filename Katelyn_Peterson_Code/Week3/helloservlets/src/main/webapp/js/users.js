window.onload = function()
{
    populateData();
}

var users;
function populateData()
{
    // Get data from servlet
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "users", true);
    xhr.send();

    xhr.onreadystatechange = function()
    {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
                // Add data to table
            var data = xhr.responseText;
            users = JSON.parse(data);

            console.log(users);
        }
    }
}