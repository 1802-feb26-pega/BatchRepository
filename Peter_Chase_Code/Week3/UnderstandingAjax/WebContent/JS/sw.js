
$(document).ready(main);
function main() {
    console.log('document ready');

    $('#submit').on('click', getInfo);
}

function getInfo() {
    const id = $('#id').val();
    const method = 'GET';
    const url = `https://swapi.co/api/people/${id}/`;

    AJAX(method, url, (response) => {
        console.log(response);
        $('#name').text(response.name);
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


function getInfoManual() {
    const id = $('#id').val();
    // Use AJAX in 4 steps
    // Step 1: Create XHR object
    const xhr = new XMLHttpRequest();
    // Step 2: Open
    xhr.open('GET', `https://swapi.co/api/people/${id}/`, true);
    
    // Step 3: SEND
    xhr.send();

    // Step 4: function to handle responce once processed; callack function
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // HERE IS WHEN YOU PROCESS YOUR RESPONSE
            const jedi = JSON.parse(xhr.responseText);
            console.log(jedi);

            $('#name').text(jedi.name);
        }
    }
}
