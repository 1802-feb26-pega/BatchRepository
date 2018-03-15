$(document).ready(populateData);

function populateData() {
    AJAX('GET', 'users', (response) => {
        for (let i in response) {
            const user = response[i];
            const row = document.createElement('tr');
            const cell1= document.createElement('td');

            const cell2 = document.createElement('td');
            const cell3 = document.createElement('td');
            
            cell1.innerHTML = user.name;
            cell2.innerHTML = user.bio;
            cell3.innerHTML = user.email;

            row.appendChild(cell1)
            row.appendChild(cell2);
            row.appendChild(cell3);

            users.appendChild(row);
        }
    });
}

function AJAX(method, url, callback) {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4 || xhr.status != 200) {
            return;
        }
        callback(JSON.parse(xhr.responseText));
    };
    xhr.send();
}
