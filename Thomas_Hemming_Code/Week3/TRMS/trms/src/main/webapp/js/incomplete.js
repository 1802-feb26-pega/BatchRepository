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