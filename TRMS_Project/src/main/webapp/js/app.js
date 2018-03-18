window.onload = function(){
	loadLanding();
}
//=================================================
function loadLanding(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","loadlanding.view",true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#login').on('click',login);
			//after text is loaded , add event listeners/functionality to view
			$('#register').on('click', loadRegister);
		}
	}
}
//================================================
function loadRegister(){
	//console.log("in loadRegister()");
	var xhr = new XMLHttpRequest();
	//console.log("1");
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			//console.log(xhr.responseText);
			$('#username').blur(validate);
			$('#register').click(register);
			$('#return').click(loadLanding);
			//added by me:
			//$('#upload').click(loadUpload());
		}
	}
}
//*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+
function login(){
//	alert('listen button works!');
	$('#message').hide();
	var email=$('#email').val();
	var password=$('#pass').val();

	var toSend = [email,password];					//toSend
	var xhr = new XMLHttpRequest();
	xhr.open("POST","login",true);
	xhr.send(JSON.stringify(toSend));				//stringify

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//console.log(xhr.readyState);
			//console.log(xhr.status);
			var user = JSON.parse(xhr.responseText);
			var message = "";
			if(user == null){
				$('#message').show();
				$('#message').attr("style","display:inline");
				message = "Incorrect username or password";
				$('#message').html(message);
			}else{
				alert("success");
				//loadNav();
				//loadHome(user);
			}
		}
	}
}
//========================================
function register(){ 
	//alert('register button');
	//console.log("register");
	var fn = $('#fn').val();
	console.log(fn);
	var ln = $('#ln').val();
	console.log(ln);
	var uname = $('#username').val();
	//console.log(username);
	var pass = $('#pass').val();
	console.log(pass);
	var ln = $('#bday').val();
	var ln = $('#addr').val();
	var ln = $('#zip').val();
	var ln = $('#title').val();
	var ln = $('#sup').val();
	var ln = $('#head').val();
	var ln = $('#ben').val();
	var ln = $('#app').val();
	var ln = $('#pen').val();

	console.log((fn=="" || ln=="" || username=="" || pass==""));
	
	if(fn=="" || ln=="" || username=="" || pass==""){
		alert('one or more fields empty');
	}else{

		var user = {
				firstname: fn, 
				lastname: ln, 
				username: username, 
				password: pass,
				birthdate: bday,
				address: addr,
				zipcode: zip,
				title: title,
				supervisorId: sup,
				deptHeadId: head,
				benCoId: ben,
				approvedFunds: app,
				pendingFunds: pen

		};

		var xhr = new XMLHttpRequest();
		xhr.open("POST", "register", true);
		xhr.send(JSON.stringify(user));

		//loadNav();
		//loadHome(user);
	}
}
//========================================
function validate(){
	//console.log($('#username').val());
	var username = $('#username').val();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(username));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			if(exists){
				$('#message').html('Username taken');
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







