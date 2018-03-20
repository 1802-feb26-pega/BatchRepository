window.onload = function(){
	loadLanding();
}

function loadLanding() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();

	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);
			$('#login').on('click', login);
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			$('#register').on('click', loadRegister);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

function loadRegister(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			$('#username').blur(validate);
			$('#register').click(register);
		}
	}
}

function validate(){
	console.log($('#username').val());
	var username = $('#username').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(username));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			if(exists){
				$('#message').html('Sorry, that username exists, please try again');
				$('#message').show();
				$('#register').attr('disabled', true);
			}
			else{
				$('#message').hide();
				$('#register').attr('disabled', false);
			}
		}
	}	
}

function register(){ 
	console.log("register");
	var fn = $('#fn').val();
	var ln = $('#ln').val();
	var uname = $('#username').val();
	var pass = $('#pass').val();
	var dep = $('#dep').val();
	dep = +dep;
	var dob = $('#dob').val();
	var adr = $('#address').val();
	var zip = $('#zip').val();
	
	var user = {
			firstname: fn, 
			lastname: ln, 
			email: uname, 
			password: pass,
			departmentId: dep,
			dob: dob,
			address: adr,
			zip: zip
	};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(user));
	loadNav();
	loadHome();
}

function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			//$('#home').on('click',);
			$('#logout').click(logout);

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}

function loadHome(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//$('#name').html(user.firstname);
			$('#event').on('click', loadEvent);
			$('#subgrade').on('click', submitGrade);
			$('#subapprove').on('click', submitApproval);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getUserEvents();
			$('#eventTable').hide;
		}
	}
}

function submitGrade() {
	var form = $('#search').val();
	var grade = $('#grading').val();
	var toSend = [form, grade];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "submitGrade", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			
		}
	}
}

function submitApproval() {
	var form = $('#searchall').val();
	var approval = $('#approve').val();
	var toSend = [form, approval];
	console.log(toSend);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "submitApproval", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var response = JSON.parse(xhr.responseText);
			var message = "";
			if (response == null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "Request cannot be approved";
				$('#message').html(message);
			}
		}
	}
}

function getUserEvents() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "event" , true);
	xhr.send();

	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) { 
			var events = JSON.parse(xhr.responseText);
			
			if(events.length == 0) {
				console.log("you have no pending events");
				$('#eventTable').hide();
			} else {
				console.log("testing----");
				console.log(events);
				var data = formatTable(events);

				$('#eventTable').DataTable({
					data : data,
					columns: [
						{data : "Id" },
						{data : "Event" },
						{data : "Grading" },
						{data : "Cost" },
						{data : "Grade" },
						{data : "Approval"},
						//{data : "Loc" },
						//{data : "Desc" }
						]
				});
				$('#eventTable').show();
			}
		}
	}
}

function formatTable(events){
	//console.log("formatting table");
	var data = [];
	
	for(let i = 0; i < events.length; i++) {
		let temp = new Object();
		//console.log(events[i]);
		temp.Id = `1000${events[i].id}`;
		temp.Event = `${events[i].type}`;
		temp.Grading = `${events[i].format}`;
		temp.Cost = `${events[i].cost}`;
		temp.Grade = `${events[i].grade}`;
		temp.Approval = `${events[i].approval}`;
		//temp.Loc = `${events[i].location}`;
		//temp.Desc = `${events[i].description}`;
		//console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}

function login(){
	console.log("logging in");
	$('#message').hide();
	var email = $('#email').val();
	var password = $('#pass').val();
	console.log(email);
	var toSend = [email, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var user = JSON.parse(xhr.responseText);
			var message = "";
			if(user == null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			} else {
				loadNav();
				loadHome();
			}
		}
	}
}

function logout(){
	console.log("logging out");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout" , true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("attempting to redirect");
			window.location.replace("index.html");
		}

	}

}

function loadEvent() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadevent.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);
			$('#message').hide();
			$('#complete').click(event);
		}
	}
}

function event() {
	console.log("event");
	
	var type = $('#type').val();
	var format = $('#format').val();
	var cost = $('#cost').val();
	var date = $('#date').val();
	var time = $('#time').val();
	var loc = $('#location').val();
	var desc = $('#description').val();
	
	var event = {
			type: type, 
			format: format, 
			cost: cost, 
			date: date,
			time: time,
			location: loc,
			description: desc
	};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "event", true);
	xhr.send(JSON.stringify(event));
	loadNav();
	loadHome();
}


