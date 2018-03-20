window.onload = function(){
	console.log("loading landing");
	loadLanding();
}

function loadLanding(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view", true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			
			$('#login').on('click', login);
			
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			
			console.log()
			$('#register').on('click', loadRegister);
		}
	}
}
function loadRegister(){
	var xhr = new XMLHttpRequest();
	console.log(xhr);
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			$('#email').blur(validate);
			$('#register').click(register);
		}
	}
}

function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view", true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			$('#home').click(loadHome);
			$('#submit').click(loadRequest);
			$('#logout').click(logout);
			
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			
		}
	}
}

function loadHome(emp){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view", true)
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(emp.firstName);

			getEmployeeRequests();
			$('#reqTable').hide();
		}
	}
}

function loadRequest(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadrequest.view", true)
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			$('#submit').click(request);
		}
	}
}

function loadMessages(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadmessages.view", true)
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
		}
	}
}

function validate(){
	console.log($('#email').val());
	var employee = $('#email').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(employee));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			console.log(exists);
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
	var email = $('#email').val();
	var date = $('#dob').val(); 
	var dept = $('#dept').val();
	var address = $('#address').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var password = $('#pass').val();
	var passConfirm = $('#passConfirm').val();

	
	var emp = {
			firstName: fn, 
			lastName: ln, 
			email: email, 
			dob: date,
			department: dept,
			address: address,
			city: city,
			state: state,
			password: password
	};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(emp));
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			loadNav();
			loadHome(emp);
		}
	}
}

function request(){
	console.log("request");
	
	var event_date = $('#date');
	var city = $('#event_city');
	var state = $('#event_state');
	var cost = $('#cost');
	var event_type = $('event_type');
	var format = $('format');
	
	var req = {
			event_date: event_date, 
			city: city, 
			state: state, 
			cost: cost,
			event_type: event_type,
			format: format,
	};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "request", true);
	xhr.send(JSON.stringify(req));
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("request happened correctly");
			loadNav();
			loadHome();
		}
	}
}

function getEmployeeRequests() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "emprequests", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var requtests = JSON.parse(xhr.responseText);
			if(requests.length == 0){
				console.log("You have no pending requests.");
			}
			else{
				console.log("testing----");
								//	accounts = JSON.stringify("data") + ":" + accounts;
								var data = formatTable(requests);
				
								$('#reqTable').DataTable({
									data : data,
									columns: [
										
										{data : "Reimbursement ID"},
										{data : "First Name"},
										{data : "Last Name"},
							    		{data : "Date of Event"},
							    		{data : "City"},
							    		{data : "State"},
							    		{data : "Cost"},
							    		{data : "Projected Reimbursement"},
							    		{data : "Format"},
										{data : "Approval"}
										]
								});
								
								$('#reqTable').show();
			}
		}
	}
}

function formatTable(accounts){
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < accounts.length; i++){
		let temp = new Object();
		console.log(accounts[i]);
		temp.Id = `1000${accounts[i].id}`;
		temp.Balance = accounts[i].balance;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}


function login() {
	console.log("logging in");
	$('message').hide();
	var email = $('#email').val();
	var password = $('#pass').val();
	
	var toSend = [email, password];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			var message = "";
			if(user==null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			}
			else{
				loadNav();
				loadHome(user);
			}
		}
	}
}

function logout(){
	console.log('logging out');
	//invalidate the session
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("attempting to redirect");
			window.location.replace("index.html");
		}
	}
}