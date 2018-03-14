/*AJAX is useful because it allows developers to:
	- Update a web page without reloading the page
	- Request data from a server - after the page has loaded
	- Receive data from a server - after the page has loaded
	- Send data to a server in the background 
About AJAX
	- Not a programming language, but a technique for accessing web servers from a web page
	- AJAX stands for Asynchronous JavaScript and XML
	- Uses a combination of 
		○ A browser built-in XMLHttpRequest object (to request data from a web server)
		○ JS and HTML DOM (to display or use the data)
	- AJAX is a misleading name. AJAX apps might use some XML to transport data but it is equally common to transport data as plain text or JSON text
	- Allows web pages to be updated asynchronously by exchanging data with a web server behind the scenes. This means that it is possible to update parts of a webpage without reloading the whole page
*/

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