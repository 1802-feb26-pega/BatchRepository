window.onload = function(){
    initialize();
    $('#add').on('click', addUser);
}
var users

function addUser(){
    var user = {};
    user.name = $('#name').val();
    user.bio = $('#bio').val();
    user.email = $('#email').val();

    var data = JSON.stringify(user);
    console.log(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "users", true);
    xhr.send(data);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            let u = [user];
            populateData(u);

        }
    }

}

function initialize(){
    //Get data from the servlet
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "users", true);
    xhr.send();

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            //ADD data
            var data = xhr.responseText;
            users = JSON.parse(data);
            console.log(users);
            populateData(users);
        }
    }
}

function populateData(user){
    for(let i = 0; i < user.length; i++){
        let name = user[i].name;
        let bio = user[i].bio;
        let email = user[i].email;

        let temp = document.createElement('tr');
        let cell1 = document.createElement('td');
        let cell2 = document.createElement('td');
        let cell3 = document.createElement('td');

        cell1.innerHTML = name;
        cell2.innerHTML = bio;
        cell3.innerHTML = email;

        temp.append(cell1);
        temp.append(cell2);
        temp.append(cell3);

        $('#users').append(temp);

    }
}