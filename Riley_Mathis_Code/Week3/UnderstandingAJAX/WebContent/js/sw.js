/**
 * 
 */

window.onload = function(){
	console.log("window loaded");
	$('#submit').on('click', getInfo);
	
}

var jedi; //make jedi global so we can inspect

function getInfo(){
	var id = $('#id').val();
	
	//AJAX IN FOUR STEPS
	//Step 1: create XHR
	var xhr = new XMLHttpRequest();
	
	//Step 2: open
	xhr.open("GET", `https://swapi.co/api/people/${id}/`, true);
	
	//Step 3: send
	xhr.send();
	
	//Step 4: function to handle response once processed; "callback" function
	xhr.onreadystatechange = function(){
		console.log(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			//process your response
			jedi = JSON.parse(xhr.responseText);
			console.log("THIS IS THE RESPONSE TEXT");
			console.log(xhr.responseText);
			console.log("THIS IS THE JS OBJECT");
			console.log(jedi);
			
			//manipulate dom
			$('#name').html(jedi.name);
		}
	}
}