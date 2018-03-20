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
				loadNav();
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


function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			$('#mybrand').on('click', loadHome);
			$('#home').on('click', loadHome);
			$('#logout').on('click',logout);
			$('#events').on('click', loadEmployeeEvents);
			

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
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
			$('#name').html(employee.firstname);
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
			$('#submitReimbursement').click(submit);
			$('#submitReimbursement').html("Hello");
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
//			getEmployeeEvents();
//			$('#eventTable').hide();
		}
	}
}

function submit(){
	console.log("submit");
	var superApp = 0;
	var depHeadApp = 0;
	var coverage;
	
	var id = $('#id').val();
	var date = ($('#date').val()).add($('#time').val());
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
	$('#cost').keyup(function(){
		switch($('#eventTypeId').val()){
			case 1 : coverage = .8;break;
			case 2 : coverage = .6;break;
			case 3 : coverage = .75;break;
			case 4 : coverage = 1;break;
			case 5 : coverage = .9;break;
			case 6 : coverage = .3;
		}
		$('#requested-amount').val($('#cost').val()*coverage);
	});
	var requestedAmount = $('#requested-amount').val();
//
//	var form = {
//			firstname: fn, 
//			lastname: ln, 
//			email: uname, 
//			password: pass
//	};
//
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", "submit", true);
//	xhr.send(JSON.stringify(form));
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


