var loadct = 0;

var user;
var requested;
var requests;

window.onload = function(){
			loadLanding();	

		/*if (window.location.href == "http://localhost:8088/bank/index.html") {
			console.log("loading request portal");
			loadNav();
		}*/
}

function loadLanding(){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#login').on('click', function (event) {
				event.stopPropagation();
				console.log("login was clicked");
				login();
			});
			$('#register').on('click', loadRegister);
			$('#pass').keydown(function(event) {
				var keypressed = event.keycode || event.which;
				if(keypressed==13) login();
			});
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

function fillApprovals() {
	var toSend = [user.email, user.password];
	var please = [];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "approvals", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var data = [];
			req = JSON.parse(xhr.responseText)
		    for(let x = 0; x < req.length; x++){
		        let temp = new Object();
		        temp["Request No"] = req[x].request_id;
		        console.log(temp["Request No"]);
		        console.log(req[x].request_id);
		        temp["Request Date"] = req[x].dateOfRequest;
		        temp["Reim Amount"] = req[x].reimAmount;
		        temp["Status"] = req[x].status;       

		        data.push(temp);
		    }
		    console.log(data);
		    $(document).ready(function() {
				console.log(data);
				var table = $('#requesttable').DataTable({
					destroy: true,
					data: data,
					columns: [
						{data: "Request No"},
						{data: "Request Date"},
						{data: "Reim Amount"},
						{data: "Status"}
					]
				});
				$('#requesttable tbody').on('click','tr', function(){
                    d = table.row(this).data();
                    console.log("Request No. is " + d["Request No"]);
                    //$('#approve')
                    //$('#deny')
                });
			});
			
		}
	}
}

function fillRequested() {
	var toSend = [user.email, user.password];
	var please = [];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "requests", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var data = [];
			req = JSON.parse(xhr.responseText)
		    for(let x = 0; x < req.length; x++){
		        let temp = new Object();
		        temp["Request No"] = req[x].request_id;
		        console.log(temp["Request No"]);
		        console.log(req[x].request_id);
		        temp["Request Date"] = req[x].dateOfRequest;
		        temp["Reim Amount"] = req[x].reimAmount;
		        temp["Status"] = req[x].status;       

		        data.push(temp);
		    }
		    console.log(data);
		    $(document).ready(function() {
				console.log(data);
				$('#requesttable').DataTable({
					destroy: true,
					data: data,
					columns: [
						{data: "Request No"},
						{data: "Request Date"},
						{data: "Reim Amount"},
						{data: "Status"}
					]
				});	
			});
			
		}
	}
}




function loadNav(){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			
			$('#home').on('click', function() {
				loadHome(user);
			});
			$('#requests').on('click', function() {
				loadRequests(user)
			});
			$('#documentation').on('click', function() {
				loadDocs();
			});
			$('#logout').on('click', logout);	
			// after loaded add listeners
		}
	}
}

function submitRequest() {
	
	var employee_id = user.employeeId;
	var startdate = $('#startdate').val();
	var eventtime = $('#eventtime').val();
	var description = $('#description').val();
	var cost = $('#Cost').val();
	var gradeformat = $('#gradeformat').val();
	var eventtype = $('#eventType').val();
	
	var toSend = [employee_id, startdate, eventtime, description, cost, gradeformat, eventtype];
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "createrequest", true);
	xhr.send(JSON.stringify(toSend));
	
	console.log("Created Event");
	loadHome(user);
	loadNav();

}

//where the user will be able to add and approve requests
function loadRequests() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "loadrequests.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		console.log("loading requests?");
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("IN REQUEST")
			$('#view').html(xhr.responseText);
			
			//write event listeners so I can submit a request form
			$('#submit').on('click', submitRequest);
			
		}
	}
}

//Was going to put documentation here but...
function loadDocs() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			fillApprovals();
			// after loaded add listeners
		}
	}
}

function logout() {
	console.log('Logging out');
	//here we invalidate the session and go back to the login page
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "logout" , true);
	xhr.send();
	
	xhr.onreadstatechange = function() {
		console.log("logged out");
		if(xhr.readyState == 4 && xhr.status == 200) {
			window.location.replace("http://localhost:8088/bank/");
		}
	}
}

function loadHome(user){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(user.firstName);
			fillRequested();
			// after loaded add listeners
		}
	}
}

function loadRegister(){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			$('#username').blur(validate);
			$('#register').on('click', register);
		}
	}
}

function register(){
	console.log('register');
	var fn = $('#fn').val();
	var ln = $('#ln').val();
	var email = $('#email').val();
	var pass = $('#pass').val();
	var pass2 = $('#pass2').val();
	
	if (pass != pass2) {
		$("#message").show();
		$("#message").attr("style", "display:inline");
		message = "Your passwords do not match";
		$("#message").html(message);
	}
	
	else {
		
		var toSend = [fn, ln, email, pass];
		
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "register", true);
		xhr.send(JSON.stringify(toSend));
		
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200) {
				user = JSON.parse(xhr.responseText);
				console.log("Registered");
				loadHome(user);
				loadNav();
			}
		}
	}
	
}

function validate() {
	console.log("blurred username");
}

function login(){
	$('#username').on('click', $('#message').hide());
	var email = $('#email').val();
	var password = $('#pass').val();
	
	var toSend = [email, password];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			user = JSON.parse(xhr.responseText);
			var message = "";
			if (user == null) {
				$("#message").show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username or password";
				$("#message").html(message);
			} else {
				console.log("We made it");
				loadHome(user);
				loadNav();
			}
		}
	}
}