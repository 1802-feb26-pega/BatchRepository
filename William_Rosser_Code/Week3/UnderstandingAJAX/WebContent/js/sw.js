/**
 * 
 */

window.onload = function (){
	console.log("Window Loaded");
	$('#submit').on('click', getInfo);
}

var jedi = null;

function getInfo() {
	var id = $('#id').val();
	
	//USE AJAX IN FOUR STEPS
	
	//STEP 1: CREATE XHR
	var xhr = new XMLHttpRequest();
	
	//Step 2: Open - request type, address, asynchronous
	xhr.open("GET", `https://swapi.co/api/people/${id}/`, true);
	
	//Step 3: Send
	xhr.send(); //Request body would go here.
	
	//Step 4: Function to handle response once processed;
	//Callback function
	xhr.onreadystatechange = function() {
		console.log(xhr.readyState);
		console.log(xhr.status);
		if (xhr.readyState == 4 && xhr.status == 200) {
			// Here is where the response is processed.
			jedi = JSON.parse(xhr.responseText);
			console.log("THIS IS THE RESPONSE TEXT");
			console.log(xhr.responseText);
			console.log("THIS IS THE JS OBJECT");
			console.log(jedi);
			
			$('#name').html(jedi.name);
		} else if (xhr.readyState == 4 && xhr.status == 404) {
			console.log("You dun 404'd.");
		}
	}
}