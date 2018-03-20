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
			$('#username').blur(validate);
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
			//$('#home').on('click',)
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
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
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