/**
 * 
 */

var jedi; //make jedi global scope so we can inspect

window.onload = function() {
	console.log("window loaded");
	$('#submit').on('click', getInfo);
	
}

function getInfo(){
	var id= $('#id').val();
	
	//USE AJAX IN FOUR STEPS
	//STEP 1: CREATE XHR
	
	var xhr = new XMLHttpRequest();
	
	//STEP 2: OPEN
	xhr.open("GET", `https://swapi.co/api/people/${id}/`, true);
	
	//STEP 3: SEND
	xhr.send();
	
	//STEP 4: function to handle response once processed; "callback" function
	xhr.onreadystatechange = function() {
		console.log(xhr.readyState);
		if (xhr.readyState == 4 && xhr.status == 200) {
			//HERE IS WHERE YOU PROCESS YOUR RESPONSE!
			jedi = JSON.parse(xhr.responseText);
			console.log("THIS IS THE RESPONSE TEXT");
			console.log(xhr.responseText);
			console.log("WHEREAS THIS IS THE JS OBJECT");
			console.log(jedi);
			
			//MANIPULATE DOM
			$('#name').html(jedi.name);
		}
			
	}
}