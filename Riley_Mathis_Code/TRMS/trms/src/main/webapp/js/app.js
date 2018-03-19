/**
 * 
 */

window.onload = function(){ //1 
	loadLanding();
}

function loadLanding(){ //1 
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#login').on('click', login); 
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			//$('#register').on('click', loadRegister);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

//function loadRegister(){
//	var xhr = new XMLHttpRequest();
//	xhr.open("GET", "loadregister.view" , true);
//	xhr.send();
//
//	xhr.onreadystatechange = function(){
//		if(xhr.readyState == 4 && xhr.status == 200){
//			$('#view').html(xhr.responseText);
//			$('#message').hide();
//			$('#username').blur(validate);
//			$('#register').click(register);
//		}
//	}
//}

//function validate(){
//	console.log($('#username').val());
//	var username = $('#username').val();
//
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", "validate", true);
//	xhr.send(JSON.stringify(username));
//
//	xhr.onreadystatechange = function(){
//		if(xhr.readyState == 4 && xhr.status == 200){
//			var exists = JSON.parse(xhr.responseText);
//			if(exists){
//				$('#message').html('Sorry, that username exists, please try again');
//				$('#message').show();
//				$('#register').attr('disabled', true);
//			}
//			else{
//				$('#message').hide();
//				$('#register').attr('disabled', false);
//			}
//		}
//	}
//
//
//}


//function register(){ 
//	console.log("register");
//	var fn = $('#fn').val();
//	var ln = $('#ln').val();
//	var uname = $('#username').val();
//	var pass = $('#pass').val();
//
//	var user = {
//			firstname: fn, 
//			lastname: ln, 
//			email: uname, 
//			password: pass
//	};
//
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", "register", true);
//	xhr.send(JSON.stringify(user));
//}


function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			//$('#home').on('click', loadHome);
			$('#logout').on('click',logout);
			$('#events').on('click', loadEmployeeEvents);

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}


function loadHome(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//document.getElementById('view').innerHTML(xhr.responseText);
			$('#name').html(employee.firstname);
			//$('#submitReimbursement').on('click', function(){submit(employee)})
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
//			getEmployeeEvents();
//			$('#eventTable').hide();
		}
	}
}

//function submit(employee){
//	console.log("submit");
//	var fn = $('#fn').val();
//	var ln = $('#ln').val();
//	var uname = $('#username').val();
//	var pass = $('#pass').val();
//
//	var form = {
//			firstname: fn, 
//			lastname: ln, 
//			email: uname, 
//			password: pass
//	};
//
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", "submit", true);
//	xhr.send(JSON.stringify(form));
//}

function loadEmployeeEvents(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadEmployeeEvents.view", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//document.getElementById('view').innerHTML(xhr.responseText);
			//$('#name').html(employee.firstname);
			//$('#submitReimbursement').on('click', function(){submit(employee)})
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getEmployeeEvents();
			//$('#eventTable').hide();
		}
	}
}

function getEmployeeEvents(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "events" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var events = JSON.parse(xhr.responseText);
			if(events.length == 0){
				console.log("you have no events");
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				console.log(events);
				var data = formatTable(events);

				$('#eventTable').DataTable({
					data : data,
					columns: [
						{data : "eventId" },
						{data : "dateCreated" },
						{data : "dateScheduled" },
						{data : "eventLocation" },
						{data : "eventCost" },
						{data : "eventTypeId" },
						{data : "employeeId" },
						{data : "grade" }
						]
				});
				console.log("this should have worked");
				$('#eventTable').show();
			}
		}
	}
}


function formatTable(events){
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < events.length; i++){
		let temp = new Object();
		console.log(events[i]);
		temp.eventId = `1000${events[i].eventId}`;
		temp.dateCreated = events[i].dateCreated;
		temp.dateScheduled = events[i].dateScheduled;
		temp.eventLocation = events[i].eventLocation;
		temp.eventCost = events[i].eventCost;
		temp.eventTypeId = events[i].eventTypeId;
		temp.employeeId = events[i].employeeId;
		temp.grade = events[i].grade;
		//temp.dateCreated = events[i].dateCreated;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}


function login(){
	console.log("logging in");
	$('#message').hide();
	var username = $('#username').val();
	var password = $('#pass').val();

	var toSend = [username, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			var message = "";
			if(employee==null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			}
			else{
				//alert("success");
				loadNav();
				loadHome(employee);
			}
		}
	}
}

function logout(){
	console.log("logging out");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout" , true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("attempting to redirect");
			window.location.replace("index.html");
		}

	}

}