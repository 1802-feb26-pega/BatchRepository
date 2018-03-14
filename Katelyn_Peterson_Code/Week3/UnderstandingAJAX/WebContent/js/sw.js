// Notes - add later

window.onload = function()
{
	console.log("window loaded");
	
	$('#submit').on('click', getInfo)
}

function getInfo()
{
	var id = $('#id').val();
	
	// Use AJAX in four steps
	// Step 1: Create XHR -- more info at https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest
	var xhr = new XMLHttpRequest();

	// Step 2: OPEN
	xhr.open("GET", `https://swapi.co/api/people/${id}/` , true);

	// Step 3: SEND
	xhr.send();

	// Step 4: function to handle response once processed; "callback" function
	//https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
	xhr.onreadystatechange = function()
	{
		console.log(xhr.readyState);

		if(xhr.readyState == 4 && xhr.status == 200)
		{
			// Here is where you process your response!
			jedi = JSON.parse(xhr.responseText);
			console.log("This is the reponse text");
			console.log(xhr.responseText);
			console.log("Whereas this is the JS Object");
			console.log(jedi);

			// Manipulate DOM
			$('#name').html(jedi.name);
		}
	}
}