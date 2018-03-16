window.onload = function(){
	console.log("window loaded");
	$("#submit").on("click", getInfo);
}

function getInfo(){
	var id = $("#id").val();

	// USE AJAX IN 4 STEPS
	// STEP 1: CREATE XHR
	var xhr = new XMLHttpRequest();

	// STEP 2: OPEN
	xhr.open("GET", `https://swapi.co/api/people/${id}/`, true);

	// STEP 3: SEND
	xhr.send();

	// STEP 4: function to handle response once processed; "callback" function
	xhr.onreadystatechange = function(){
		console.log(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			// here is where you process your response
			var jedi = JSON.parse(xhr.responseText);
			console.log("THIS IS THE RESPONSE TEXT");
			console.log(xhr.responseText);
			console.log("WHEREAS THIS IS THE JS OBJECT");
			console.log(jedi)

			// manipulate DOM
			$("#name").html(jedi.name);
		}
	}

}



// function getInfo() {
//     const id = $('#id').val();
//     const method = 'GET';
//     const url = `https://swapi.co/api/people/${id}/`;

//     AJAX(method, url, (response) => {
//         console.log(response);
//         $('#name').text(response.name);
//     });
// }

// function AJAX(method, url, callback) {
//     const xhr = new XMLHttpRequest();
//     xhr.open(method, url, true);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState != 4 || xhr.status != 200) {
//             return;
//         }
//         callback(JSON.parse(xhr.responseText));
//     };
//     xhr.send();
// }