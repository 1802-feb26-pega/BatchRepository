window.onload = function(){
    console.log("window loaded")
    $('#submit').on('click', getInfo);
}

function getInfo(){
    var id = $('#id').val();
    
    //use AJAX in four steps
    //Step 1: Create XHR
     var xhr = new XMLHttpRequest();

    //Step 2: OPEN
    xhr.open("GET", `https://swapi.co/api/people/${id}/`, true);

    //Step 3: SEND
    xhr.send();

    //Step 4: Function to handle response once processed; "callback" function
    xhr.onreadystatechange = function(){
        console.log(xhr.readyState);
        if(xhr.readyState == 4 && xhr.status == 200){
            //Here is where you process your response!
            var jedi = JSON.parse(xhr.responseText);
            console.log("THIS IS THE RESPONSE TEXT");
            console.log(xhr.responseText);
            console.log("WHERE AS THIS IS THE JS OBJECT");
            console.log(jedi);

            //manipulate DOM
            $('#name').html(jedi.name);
        }
    }
}