/**
 * 
 */

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
			//$('#register').on('click', loadRegister);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

function login(){
	console.log("logging in");
	$('#message').hide();
	var username = $('#username').val();
	var password = $('#pass').val();

	var toSend = [username, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			var message = "";
			if(employee==null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			}
			else{
				//alert("success");
				loadNav(employee);
				loadHome(employee);
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


function loadNav(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//$('#approvals').hide();
			$('#navbar').html(xhr.responseText);
			$('#mybrand').on('click', loadHome);
			$('#home').on('click', loadHome);
			$('#logout').on('click',logout);
			$('#events').on('click', loadEmployeeEvents);
			$('#reimbursements').on('click', loadEmployeeReimbursements);
			console.log(employee.position);
			if(employee.position>1){
				$('#approvals').show();
				$('#approvals').on('click', loadApprovals);
			}
			else{
				$('#approvals').hide();
			}
			

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}

function loadEmployeeReimbursements(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadEmployeeReimbursements.view", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//document.getElementById('view').innerHTML(xhr.responseText);
			//$('#name').html(employee.firstname);
			//$('#submitReimbursement').on('click', function(){submit(employee)})
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getEmployeeReimbursements();
			//$('#eventTable').hide();
		}
	}
}

function getEmployeeReimbursements(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "reimbursements" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var reimbursements = JSON.parse(xhr.responseText);
			if(reimbursements.length == 0){
				console.log("you have no reimbursements");
				$('#view').html("You have no reimbursements")
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				console.log(reimbursements);
				var data = formatEmpReTable(reimbursements);

				$('#reimbursementTable').DataTable({
					data : data,
					columns: [
						{data : "reimbursementId" },
						{data : "employeeId" },
						{data : "eventId"},
						{data : "justification" },
						{data : "requestedAmount" },
						{data : "alternateAmount"},
						{data : "reStatus"}
						]
				});
				console.log("this should have worked");
				$('#reimbursementTable').show();
			}
		}
	}
}


function formatEmpReTable(reimbursements){
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < reimbursements.length; i++){
		let temp = new Object();
		console.log(reimbursements[i]);
		console.log(reimbursements[i]);
		temp.reimbursementId = `000${reimbursements[i].reimbursementId}`;
		temp.employeeId = reimbursements[i].employeeId;
		temp.eventId = reimbursements[i].eventId;;
		temp.justification = reimbursements[i].justification;
		temp.requestedAmount = `$${reimbursements[i].requestedAmount}`;
		temp.alternateAmount = `$${reimbursements[i].alternateAmount}`;
		temp.reStatus = reimbursements[i].reStatus;

		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}

function loadApprovals(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadApprovals.view", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//document.getElementById('view').innerHTML(xhr.responseText);
			//$('#name').html(employee.firstname);
			//$('#submitReimbursement').on('click', function(){submit(employee)})
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getPendingReimbursements();
			//$('#eventTable').hide();
		}
	}
}

function getPendingReimbursements(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "apprReimbursements" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var reimbursements = JSON.parse(xhr.responseText);
			if(reimbursements.length == 0){
				console.log("you have no events");
				$('#view').html("No reimbursements awaiting approval");
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				console.log(reimbursements);
				var data = formatReTable(reimbursements);

				$('#reimbursementAppTable').DataTable({
					data : data,
					columns: [
						{data : "reimbursementId" },
						{data : "employeeId" },
						{data : "eventId"},
						{data : "justification" },
						{data : "requestedAmount" }
						]
				});
				console.log("this should have worked");
				$('#reimbursementAppTable').show();
			}
		}
	}
}


function formatReTable(reimbursements){
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < reimbursements.length; i++){
		let temp = new Object();
		console.log(reimbursements[i]);
		temp.reimbursementId = `000${reimbursements[i].reimbursementId}`;
		temp.employeeId = reimbursements[i].employeeId;
		temp.eventId = reimbursements[i].eventId;;
		temp.justification = reimbursements[i].justification;
		temp.requestedAmount = `$${reimbursements[i].requestedAmount}`;

		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}


function loadHome(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	var coverage = 1;
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//document.getElementById('view').innerHTML(xhr.responseText);
			$('#firstname').val(employee.firstname);
			$('#lastname').val(employee.lastname);
			
			$('#cost').keypress(function(e){
				if(e.which != 8 && e.which !=0 && (e.which < 48 || e.which > 57)){
					return false;
				}
			});
			$('#cost').keyup(function(){
				console.log("keyup");
				switch($('#event-type').val()){
					case "1" : coverage = 0.8;break;
					case "2" : coverage = 0.6;break;
					case "3" : coverage = 0.75;break;
					case "4" : coverage = 1;break;
					case "5" : coverage = 0.9;break;
					case "6" : coverage = 0.3;
				}
				console.log(coverage);
				$('#requested-amount').val('$'+(parseInt($('#cost').val())*coverage).toFixed(2));
			});
			$('#event-type').on('change',function(){
				console.log("keyup");
				switch($('#event-type').val()){
					case "1" : coverage = 0.8;break;
					case "2" : coverage = 0.6;break;
					case "3" : coverage = 0.75;break;
					case "4" : coverage = 1;break;
					case "5" : coverage = 0.9;break;
					case "6" : coverage = 0.3;
				}
				console.log(coverage);
				$('#requested-amount').val('$'+(parseInt($('#cost').val())*coverage).toFixed(2));
			});
			//$('#view').hide();
			$('#submitReimbursement').click(function(){submitReimbursement(employee)});
			//$('#submitReimbursement').html("Hello");
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
//			getEmployeeEvents();
//			$('#eventTable').hide();
		}
	}
}

function toTimeStamp(strDate){
	var datum = Date.parse(strDate);
	return datum/1000;
}

function submitReimbursement(employee){
	console.log("submit");
	var superApp = 0;
	var depHeadApp = 0;
	
	var date = toTimeStamp(($('#date').val()+' '+$('#time').val()));
	var location= $('#location').val();
	var description = $('#description').val();
	var cost = $('#cost').val();
	var eventTypeId = $('#event-type').val();
	var justification = $('#justification').val();
	if($('#check1').is(":checked")){
		superApp = 1;
	}
	if($('#check2').is(":checked")){
		depHeadApp = 1;
	}
	var requestedAmount = $('#requested-amount').val();

	var eventForm = {
			dateScheduled: date, 
			eventLocation: location, 
			//eventDescription: description, 
			eventCost: cost,
			eventTypeId: eventTypeId,
			employeeId: employee.employeeId
	};
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "submitEvent", true);
	xhr.send(JSON.stringify(eventForm));
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(xhr.responseText);
			var json = JSON.parse(xhr.responseText);
			console.log(json);
			var eventId = json.eventId;
			var reForm = {
					employeeId: employee.employeeId,
					eventId: eventId,
					justification: justification,
					superApp: superApp,
					depHeadApp: depHeadApp,
					requestAmount: requestedAmount
			};
			var rxhr = new XMLHttpRequest();
			rxhr.open("POST", "submitRe", true);
			rxhr.send(JSON.stringify(reForm));
			
			rxhr.onreadystatechange = function(){
				if(rxhr.readyState == 4 && rxhr.status == 200){
					loadEmployeeEvents();
				}
			}
		}
	}
}

function loadEmployeeEvents(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadEmployeeEvents.view", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//document.getElementById('view').innerHTML(xhr.responseText);
			//$('#name').html(employee.firstname);
			//$('#submitReimbursement').on('click', function(){submit(employee)})
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getEmployeeEvents();
			//$('#eventTable').hide();
		}
	}
}

function getEmployeeEvents(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "events" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var events = JSON.parse(xhr.responseText);
			if(events.length == 0){
				console.log("you have no events");
				$('#view').html("You have no events")
			}
			else{
				console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				console.log(events);
				var data = formatTable(events);

				$('#eventTable').DataTable({
					data : data,
					columns: [
						{data : "eventId" },
						{data : "dateCreated",
							render : function(data){
								var date = new Date(data);
								var month = date.getMonth()+1;
								return (month.length>1?month:'0'+month)+'/'+date.getDate()+'/'+date.getFullYear()
									+' - '+(date.getHours()<10?'0':'')+date.getHours()+':'+(date.getMinutes()<10?'0':'')+date.getMinutes();
							}},
						{data : "dateScheduled",
							render : function(data){
								var date = new Date(data);
								var month = date.getMonth()+1;
								return (month.length>1?month:'0'+month)+'/'+date.getDate()+'/'+date.getFullYear()
									+' - '+(date.getHours()<10?'0':'')+date.getHours()+':'+(date.getMinutes()<10?'0':'')+date.getMinutes();
							}},
						{data : "eventLocation" },
						{data : "eventCost" },
						{data : "eventTypeId" },
						{data : "employeeId" },
						{data : "grade" }
						]
				});
				console.log("this should have worked");
				$('#eventTable').show();
			}
		}
	}
}


function formatTable(events){
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < events.length; i++){
		let temp = new Object();
		console.log(events[i]);
		temp.eventId = `000${events[i].eventId}`;
		temp.dateCreated = events[i].dateCreated;
		temp.dateScheduled = events[i].dateScheduled;;
		temp.eventLocation = events[i].eventLocation;
		temp.eventCost = `$${events[i].eventCost}`;
		temp.eventTypeId = events[i].eventTypeId;
		temp.employeeId = `1000${events[i].employeeId}`;
		temp.grade = events[i].grade;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}


