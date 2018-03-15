/**
 * 
 */

window.onload = function() {
	loadLanding();	
}

function loadLanding() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadHome",true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$("#view").html(xhr.responseText);
			$("#login").on("click", login);
			$("#register").on("click", register);
		}
	}
}

function login() {
	//alert("Login clicked.");
	var email = $("#email").val();
	var pass = $("#pass").val();
	var toSend = [email, pass];
	var json = JSON.stringify(toSend);
	console.log(toSend, json);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(json);
	xhr.onreadystatechange = function () {
		if(xhr.readyStat == 4 && xhr.status == 200) {
			$("#view").html("YOU ARE LOGED IN.");
			console.log(xhr.response);
		}
	}
	
}

function register() {
	alert("Register clicked.");
	$("#reg_div").show();
}