/**
 * 
 */

window.onload = function () {
	loadLogin();
}

function loadLogin() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlogin.view", true);
	xhr.send();
	console.log("load login.")
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);
			$('#login').on('click', login);
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); 
			$('#register').on('click', loadRegister);
		}
	}
}

function login(){
	$('#message').hide();
	var username = $('#username').val();
	var password = $('#pass').val();

	var toSend = [username, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			console.log(user)
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

function loadRegister(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('.hide').hide();
			$('#username').blur(function() {
				validate(username);
			})
			$('#reg_submit').click(register);
			$('#cancel').click(loadLogin);
		}
	}
}

function validate(username){
	console.log(username);
	//TODO: Username validation
	return true;
}

function register(){
	$('.hide').hide();
	console.log("register");
	var fn = $('#firstName').val();
	var ln = $('#lastName').val();
	var sID = $('#supID').val();
	var dhID = $('#dhID').val();
	var bencoID = $('#BenCoID').val();
	var dep = $('#department').val();
	var jt = $('#job').val();
	var un = $('#username').val();
	var pass = $('#pass').val();
	var pass2 = $('#passVerify').val();
	var acctype = $('#account_type').val();
	var valid = validate(un)
	var good = (fn&&ln&&un&&pass&&pass2&&(pass==pass2)&&valid) ? true : false;
	var dest = "";
	if (acctype==0) {
		 dest = "reg_employee";
	} else if (acctype==1) {
		dest = "reg_supervisor";
	} else if (acctype==2) {
		dest = "reg_dephead";
	} else if (acctype==3) {
		dest = "reg_benco";
	}
	if (good) {
		var user = {
				firstName: fn,
				lastName: ln,
				supervisorId: sID,
				depHeadId: dhID,
				benCoId: bencoID,
				department: dep,
				jobTitle: jt,
				username:un,
				password:pass
		}
		var xhr = new XMLHttpRequest();
		xhr.open("POST", dest, true);
		xhr.send(JSON.stringify(user));
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var loadedUser = JSON.parse(xhr.responseText);
				console.log(loadedUser);
				if (loadedUser.id != -1) {
					login();
				}
			}
		}
	} else {
		if(!fn) {
			$("#fn-danger").show();
		}
		if(!ln) {
			$("#ln-danger").show();
		}
		if(!un) {
			$("#un-danger-needed").show();
		}
		if(!pass) {
			$("#pw-danger").show();
		}
		if(!pass2) {
			$("#pw2-danger-needed").show();
		} else if(pass!=pass2) {
			$("#pw2-danger").show();
		}
	}
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

		}
	}
}

function loadHome(user) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$("#view").html(xhr.responseText);
			$("#name").html(user.wholeName);
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