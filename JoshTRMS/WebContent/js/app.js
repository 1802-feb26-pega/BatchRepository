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
			$('#register').on('click', loadRegister);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

function loadReimb(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('appview').innerHTML = xhr.responseText;
			$('#submit').on('click', processReimb);
		}
	};	
	xhr.open("GET", "reimb.view" , true);
	xhr.send();
}

function processReimb(){
	console.log("Processing Reimbursement");
	var amount = $('#amount').val();
	var type = $('#type').val();
	var description = $('#description').val();
	// add password validation and second input confirmation?

	var toSend = [amount, type, description];
	console.log(toSend);
	var reimbJSON = JSON.stringify(toSend);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			console.log("added user");
		}
	}};



function loadRegister(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			
			$('#register').click(register);
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
			user_id: 0,
			username:uname,
			email: "email",
			first_name: fn, 
			last_name: ln, 
			role_id : 1,
			password: pass
	};

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(user));
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

function loadHome(user){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(user.first_name);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		
		}
	}
}

function getUserAccounts(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "accounts" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var accounts = JSON.parse(xhr.responseText);
			if(accounts.length == 0){
				console.log("you have no accounts");
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				console.log(accounts);
				var data = formatTable(accounts);

				$('#accTable').DataTable({
					data : data,
					columns: [
						{data : "Id" },
						{data : "Balance" }
						]
				});
				console.log("this should have worked");
				$('#accTable').show();
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


function login(){
	console.log("logging in");
	$('#message').hide();
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
				//alert("success");
				loadNav();
				loadHome(user);
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
			$('#view').html(xhr.responseText);
			$('#logout').on('click', logout);
			
				loadLanding();	
			
			
		};

	}
	}

