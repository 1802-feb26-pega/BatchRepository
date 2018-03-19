window.onload = function(){ //1 
	loadLanding();
}

function loadLanding(){ //2 
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
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


function login(){
	console.log("logging in");
	$("#message").hide();
	var email = $("#email").val();
	var password = $("#pass").val();

	var toSend = [email, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			var message = "";
			if(employee == null){
				$("#message").show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again.";
				$("#message").html(message);
			} else {
				//alert("success");
				loadNav();
				loadHome(employee);
			}
		}
	}
}

function loadRegister(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$("#landing").click(loadLanding);
			$("#message").hide();
			$("#email").blur(validateEmail);
			$("#doregister").click(register);
		}
	}
}

function validateEmail(){
	var email = $("#email").val();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(email));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			if(exists){
				$("#message").html("Sorry, that email is already registered. Please try again.");
				$("#message").show();
				$("#doregister").attr("disabled", true);
			} else {
				$("#message").hide();
				$("#doregister").attr("disabled", false);
			}
		}
	}
}

function register(){
	console.log("registering");
	var fn = $("#firstname").val();
	var ln = $("#lastname").val();
	var em = $("#email").val();
	var pass = $("#pass").val();

	var employee = {
		firstname: fn,
		lastname: ln,
		email: em,
		password: pass 
	};

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(employee));
}


function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#navbar").html(xhr.responseText);
			//$("#home").on("click", ...);
			$("#logout").click(logout);
		}
	}
}

function loadHome(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$("#name").html(employee.firstname);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			// getEmployeeRequests();
			// $("#reqTable").hide();
		}
	}
}

function logout(){
	console.log("logging out");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("Attempting to redirect");
			window.location.replace("index.html");
		}
	}
}