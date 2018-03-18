window.onload = function(){
	loadLanding();
}

function loadLanding(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			// after text is loaded, add event listeners/functionality to view
			$('#login').on('click', login); 
			$("#register").on("click", loadRegister);
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			$('#register').on('click', loadRegister);
		}
	}
}


function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#navigation").html(xhr.responseText);
			// $("#home").on("click", );
			$("#logout").on("click", logout);
		}
	}
}

function hideNav(){
	$("#navigation").html("");
}


function loadHome(user){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$("#name").html(user.firstname);
			getUserAccounts();
			// $("#accTable").hide();
		}
	}
}


function getUserAccounts(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","accounts", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var accounts = JSON.parse(xhr.responseText);
			if(accounts.length == 0){
				console.log("you have no accounts");
			} else{
				console.log(accounts)
				var data = formatTable(accounts);

				$("#accTable").DataTable({
					data: data,
					columns: [
						{data : "accountId" },
						{data : "balance" }
					]

				})
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
		temp.id = `1000${accounts.accoundId}`;
	}
}


function logout(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("attempting to redirect");
			loadLanding();
			hideNav();
		}
	}
}

function login(){
	var username = $("#email").val();
	var password = $("#password").val();
	//
	var toSend = [username, password];

	var xhr = new XMLHttpRequest();
	
	
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			var message = "";
			if(user==null){
				$("#message").show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again.";
				$("#message").html(message);
			} else{
				loadNav();
				loadHome(user);
			}
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
			$('#registration').on("click", register);
		}
	}
}

function register(){
	console.log("register");
	var fn = $('#fn').val();
	var ln = $('#ln').val();
	var uname = $('#username').val();
	var pass = $('#pass').val();
	
	var user = {
			firstname: fn, 
			lastname: ln, 
			username: uname, 
			password: pass
	};
	var blankFields = anyBlank();
	if(blankFields){
		// do nothing
	} else {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "register", true);
		xhr.send(JSON.stringify(user));
	}
}

function anyBlank(){
	if($("#fn").val() == ""){return true;}
	if($("#ln").val() == ""){return true;}
	if($("#username").val() == ""){return true;}
	if($("#pass").val() == ""){return true;}
	return false;
}


function validate(){
	console.log("blurred username");
	var username = $("#username").val();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(username));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			if(exists){
				$("#message").html("Sorry, that username exists, please try again.");
				$("#message").show();
				$("#registration").attr('disabled', true)
			} else {
				$("#message").hide();
				$("#registration").attr('disabled', false);
			}
		}
	}
}