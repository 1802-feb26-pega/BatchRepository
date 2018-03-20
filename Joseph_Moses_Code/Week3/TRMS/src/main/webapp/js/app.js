window.onload = function(){
	loadLanding();
	console.log("window on load");
}


function loadLanding(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#login').on('click', login);
			$('#password').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			$('#register').on('click', loadRegister);
			// after text is loaded, add event listeners/functionality to view
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
			$('#fNameMessage').hide();
			$('#sfNameMessage').hide();
			$('#lNameMessage').hide();
			$('#slNameMessage').hide();
			$('#sExistMessage').hide();
			$('#phoneMessage').hide();
			$('#emailMessage').hide();
			$('#passwordMessage').hide();
			$('#fName').blur(validateFName);
			$('#lName').blur(validateLName);
			$('#phone').blur(validatePhone);
			$('#email').blur(validateEmail);
			$('#password').blur(validatePassword);
			$('#sfName').blur(validateSupervisor);
			$('#slName').blur(validateSupervisor);
			$('#submit').click(register);
		}
	}
}

function validateFName(){

	var name = $('#fName').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validateName", true);
	console.log($('#fName').val());
	console.log(name);
	console.log(JSON.stringify(name));
	xhr.send(JSON.stringify(name));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var isValid = JSON.parse(xhr.responseText);
			console.log(isValid);
			if(!isValid){
				$('#fNameMessage').html('Sorry, please only use Letters in your name.');
				$('#fNameMessage').show();
				$('#submit').attr('disabled', true);
			}
			else{
				$('#fNameMessage').hide();
				$('#submit').attr('disabled', false);
			}
		}
	}
}

function validateLName(){

	var name = $('#lName').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validateName", true);
	console.log($('#lName').val());
	console.log(name);
	console.log(JSON.stringify(name));
	xhr.send(JSON.stringify(name));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var isValid = JSON.parse(xhr.responseText);
			console.log(isValid);
			if(!isValid){
				$('#lNameMessage').html('Sorry, please only use Letters in your name.');
				$('#lNameMessage').show();
				$('#submit').attr('disabled', true);
			}
			else{
				$('#lNameMessage').hide();
				$('#submit').attr('disabled', false);
			}
		}
	}
}

function validatePhone(){
	var phone = $('#phone').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validatePhone", true);
	console.log($('#phone').val());
	console.log(phone);
	console.log(JSON.stringify(phone));
	xhr.send(JSON.stringify(phone));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var isValid = JSON.parse(xhr.responseText);
			console.log(isValid);
			if(!isValid){
				$('#phoneMessage').html('Sorry, you have entered an invalid phone number.');
				$('#phoneMessage').show();
				$('#submit').attr('disabled', true);
			}
			else{
				$('#phoneMessage').hide();
				$('#submit').attr('disabled', false);
			}
		}
	}
}
function validateEmail(){
	var email = $('#email').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validateEmail", true);
	console.log($('#email').val());
	console.log(email);
	console.log(JSON.stringify(email));
	xhr.send(JSON.stringify(email));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var response = JSON.parse(xhr.responseText);
			var responseArray = response.split(":");
			console.log(responseArray[0]);
			console.log(responseArray[1]);
			
			if(responseArray[0] == "false"){
				console.log(responseArray[0] == "false");
				console.log('are we here?');
				$('#emailMessage').html('Sorry, you have entered an invalid email.');
				$('#emailMessage').show();
				$('#submit').attr('disabled', true);
			}
			else {
				if(responseArray[1] == "true"){
					$('#emailMessage').html('Sorry, you have entered an email that is already exists in the system.');
					$('#emailMessage').show();
					$('#submit').attr('disabled', true);
				}
				else{
					$('#emailMessage').hide();
					$('#submit').attr('disabled', false);
				}
			}
		}
	}
}
function validatePassword(){
	var password = $('#password').val();
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validatePassword", true);
	console.log($('#password').val());
	console.log(password);
	console.log(JSON.stringify(password));
	xhr.send(JSON.stringify(password));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var isValid = JSON.parse(xhr.responseText);
			console.log(isValid);
			if(!isValid){
				$('#passwordMessage').html('Sorry, you have entered an invalid password. Make sure it includes at least one letter, one number, and 8 characters long.');
				$('#passwordMessage').show();
				$('#submit').attr('disabled', true);
			}
			else{
				$('#passwordMessage').hide();
				$('#submit').attr('disabled', false);
			}
		}
	}
}

var supervisorId;
function validateSupervisor(){
	var sfName = $('#sfName').val();
	var slName = $('#slName').val();
	
	var request = [sfName, slName];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validateSupervisor", true);
	console.log(JSON.stringify(request));
	xhr.send(JSON.stringify(request));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var response = JSON.parse(xhr.responseText);
			var responseArray = response.split(":");
			console.log(responseArray[0]);
			console.log(responseArray[1]);
			console.log(responseArray[2]);
			if(responseArray[0] == "false"){
				console.log('in first if block');
				$('#sfNameMessage').html('Sorry, please only use Letters in your supervisors name.');
				$('#sfNameMessage').show();
				$('#submit').attr('disabled', true);
			}
			else {
				$('#sfNameMessage').hide();
				$('#submit').attr('disabled', false);
				if(responseArray[1] == "false"){
					$('#slNameMessage').html('Sorry, please only use Letters in your supervisors name.');
					$('#slNameMessage').show();
					$('#submit').attr('disabled', true);
				}
				else {
					$('#slNameMessage').hide();
					$('#submit').attr('disabled', false);
					if(responseArray[2] == "false"){
						$('#sExistMessage').html('Sorry, the supervisor you entered does not exist');
						$('#sExistMessage').show();
						$('#submit').attr('disabled', true);
					}
					else{
						$('#sExistMessage').hide();
						$('#submit').attr('disabled', false);
						supervisorId = responseArray[3];
					}
				}
			}
			
			
		}
	}
}
function register(){
	console.log("register");
	var fn = $('#fName').val();
	var ln = $('#lName').val();
	var phone = $('#phone').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var department = $('#departmentSelector').val();
	var empLevel = $('#empLevelSelector').val();
	
	var employee = {
			fName: fn, 
			lName: ln,
			phone: phone,
			email: email, 
			password: password,
			superId: supervisorId,
			deptId: department,
			empLevel: empLevel,
			amountAvailable: 1000.0,
			maxAvailable: 1000.0,
			pending: 0.0,
			awarded: 0.0
	};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(employee));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			alert("Registration Successful!")
			window.location.replace("index.html");
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

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}

function loadHome(emp){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(emp.fName);
			$('#amountAvailable').html(emp.amountAvailable);
			$('#maxAvailable').html(emp.maxAvailable);
			$('#amountPending').html(emp.pending);
			$('#amountAwarded').html(emp.awarded);
			$('#submitRequest').click(loadRequestForm);
			getAllPendingRequests();
			if(emp.empLevel < 2){
				$('#approvalDenialTable').hide();
			}
			getAllRequestsForReview();
			// ADD LISTENERS TO HOME PAGE STUFF
		}
	}
}

function getAllPendingRequests(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "getAllPendingRequests", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);
			console.log(requests);
			if(requests.length == 0){
				console.log("you have no requests");
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				var data = formatPendingTable(requests);

				$('#pendingRequests').DataTable({
					data : data,
					columns: [
						{data : "requestId" },
						{data : "description" },
						{data : "startDate" },
						{data : "cost" },
						{data : "expectedReimbursement" },
						{data : "status" }
						]
				});
				console.log("this should have worked");
			}
			
		}
	}
}

function getAllRequestsForReview(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "getAllRequestsForReview", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);
			console.log(requests);
			if(requests.length == 0){
				console.log("you have no requests");
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				var data = formatReviewTable(requests);

				var approvalDenialTable = $('#approvalDenialTable').DataTable({
					"scrollX": true,
					retreive: true,
					data : data,
					columns: [
						{data : "rrId" },
						{data : "fName" },
						{data : "lName" },
						{data : "description" },
						{data : "startDate" },
						{data : "location" },
						{data : "typeOfEvent" },
						{data : "cost" },
						{data : "expectedReimbursement" },
						{data : "justification" },
						{data : "workTimeMissed" },
						{data : "priority" },
						{data : "status" },
						{defaultContent: '<button class="btn btn-primary btn-block approveButton">Approve</button>'},
						{defaultContent: '<button class="btn btn-primary btn-block denyButton">Deny</button>'}
						]
				});
				
				$('#approvalDenialTable tbody').on('click', '.approveButton', function(){
					var data = approvalDenialTable.row( $(this).parents('tr') ).data();
					console.log(data.rrId);
					var xhr = new XMLHttpRequest();
					xhr.open("POST", "approveRequest", true);
					xhr.send(JSON.stringify(data.rrId));
					
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4 && xhr.status == 200){
							approvalDenialTable.row( $(this).parents('tr') ).remove();
						}
					}
				});
				
				
			}
			
		}
	}
}

function approveRequest(){
	var data = approvalDenialTable.row( $(this).parents('tr') ).data();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "approveRequest", true);
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
		}
	}
	
}

function formatPendingTable(requests){
	var data = [];
	for(let i = 0; i < requests.length; i++){
		let temp = new Object();
		console.log(requests[i]);
		temp.requestId = requests[i].rrId;
		temp.description = requests[i].description;
		temp.startDate = requests[i].startDate;
		temp.cost = requests[i].cost;
		temp.expectedReimbursement = requests[i].expectedReimbursement;
		temp.status = requests[i].status.status;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}


function formatReviewTable(requests){
	var data = [];
	for(let i = 0; i < requests.length; i++){
		let temp = new Object();
		console.log(requests[i]);
		temp.rrId = requests[i].rrId;
		temp.fName = requests[i].emp.fName;
		temp.lName = requests[i].emp.lName;
		temp.description = requests[i].description;
		temp.startDate = requests[i].startDate;
		temp.location = requests[i].location;
		temp.typeOfEvent = requests[i].typeOfEvent.typeOfEvent;
		temp.cost = requests[i].cost;
		temp.expectedReimbursement = requests[i].expectedReimbursement;
		temp.justification = requests[i].justification;
		temp.workTimeMissed = requests[i].workTimeMissed;
		temp.priority = requests[i].priority.priority;
		temp.status = requests[i].status.status;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}

function loadRequestForm(){
	console.log('please show up!!!!');
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadrequestform.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#submitRequestForm').click(submitRequest);
			$('#cost').blur(expectedReimbursement);
			// ADD LISTENERS TO REQUEST PAGE STUFF
		}
	}
}

function expectedReimbursement(){
	console.log('blurred');
	var typeOfEvent = $('#eventTypeSelector').val();
	var cost = $('#cost').val();
	var toSend =[typeOfEvent, cost];
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "expectedReimbursement" , true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#expectedReimbursement').val(xhr.responseText);
			// ADD LISTENERS TO REQUEST PAGE STUFF
		}
	}
}

function submitRequest(){
	var requestInfo = [
		$('#location').val(),
		$('#description').val(),
		$('#eventTypeSelector').val(),
		$('#cost').val(),
		$('#workTimeMissed').val(),
		$('#expectedReimbursement').val(),
		$('#requestDate').val(),
		$('#startDate').val(),
		$('#gradingFormatSelector').val(),
		$('#passingGrade').val(),
		$('#justification').val(),
	];
	
	console.log(requestInfo);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "requestSubmission", true);
	xhr.send(JSON.stringify(requestInfo));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var emp = JSON.parse(xhr.responseText);
			alert("Submission Successful!")
			loadHome(emp);
		}
	}
}

function login(){
	$('#message').hide();
	var email = $('#email').val();
	var password = $('#password').val();

	var toSend = [email, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var emp = JSON.parse(xhr.responseText);
			var message = "";
			if(emp==null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			}
			else{
				loadNav();
				loadHome(emp);
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
			console.log("attempting to redirect");
			window.location.replace("index.html");
		}

	}
}
