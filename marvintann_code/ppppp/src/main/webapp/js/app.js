window.onload = function(){ //1 
	loadLanding();
}

function loadLanding(){ //1 
	//alert("landing");
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
			}); 
			$('#register').on('click', loadRegister);
		}
	}
}

function login(){
	//alert("login")
	console.log("logging in");
	$('#message').hide();
	var email = $('#email').val();
	var pwd = $('#pass').val();

	var toSend = [email, pwd];

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
				message = "You have entered the wrong email and/or password. Please try again";
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

function loadRegister(){
	//alert("register");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			$('#email').blur(validate);
			$('#register').click(register);
		}
	}
}

function validate(){
	//alert("validate");
	console.log($('#email').val());
	var email = $('#email').val();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(email));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			if(exists){
				$('#message').html('Sorry, that email exists, please try again');
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

function register(){ 
	console.log("register");
	var fn = $('#firstname').val();
	var ln = $('#lastname').val();
	var email = $('#email').val();
	var pass = $('#pwd').val();
	var deptId = $('#departmentSelect').val();
	var title = $('#employeeTitle').val();
	var supervisor = $('#supervisor').val();

	var employee = {
			titleId: title,
			departmentId: deptId,
			firstName: fn, 
			lastName: ln, 
			email: uname, 
			pwd: pass,
			reportsTo: supervisor
	};

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(employee));
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
			$('#requests').click(loadRequestList);
			//$('#test').click(loadTest);
			$('#home').click(loadHome);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}

function loadTest() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadtest.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(user.firstname);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			
		}
	}
	
}

function loadHome(user){
	//alert("home");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(user.firstname);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			
		}
	}
}

function loadRequestList(){
	//alert("loadRequestList START");
	console.log("loading requestList");
	var xhr = new XMLHttpRequest();
	
	xhr.open("GET", "loadrequestlist.view", true);
	
	//alert("after open, before send");
	xhr.send();
	//alert("after send");
	
	xhr.onreadystatechange = function() {
		//alert(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			
			getRequests();
			$('#accTable').hide();
			$('#createRequest').click(loadRequestsForm);
		}
	}

}

function loadRequestsForm(){
	console.log("loading requestList");
	var xhr = new XMLHttpRequest();
	
	xhr.open("GET", "loadrequestsform.view", true);
	
	//alert("after open, before send");
	xhr.send();
	//alert("after send");
	
	xhr.onreadystatechange = function() {
		//alert(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			
			
			$('#makeRequest').click(submitRequest);
		}
	}
}

function submitRequest() {
	//alert("submitRequest start");
	console.log("register");
	
	var requestsId = `55`;
	var employeeId = `1`;// $('#employeeId').val();
	var requestsStatus = `pending`;//$('#requestsStatus').val();
	var requestsType = $('#requestsType').val();
	var requestsGradingFormat = $('#requestsGradingFormat').val();
	var requestsDate = $('#requestsDate').val();
	var requestsLocation = $('#street').val()+`, `+ $('#city').val()+`, `+ $('#state').val()+` `+ $('#zip').val();
	var requestsCost = $('#requestsCost').val();
	var requestsDescription = $('#requestsDescription').val();
	var requestsJustification = $('#requestsJustification').val();
	var requestsDaysMissed = $('#requestsDaysMissed').val();
	var requestsAttachments = $('#requestsAttachments').val();
	////alert("after variables");
	var requests = {
			requestsId: requestsId,
			//employeeId: employeeId,
			employeeId: `1`,
			requestsStatus: requestsStatus,
			requestsType: requestsType,
			requestsGradingFormat: requestsGradingFormat,
			requestsDate: requestsDate,
			requestsLocation: requestsLocation,
			requestsCost: requestsCost,
			requestsDescription: requestsDescription,
			requestsJustification: requestsJustification,
			requestsDaysMissed: requestsDaysMissed,
			requestsAttachments: requestsAttachments
	};
	//alert("after requests variable");
	var xhr = new XMLHttpRequest();
	//alert("after xhr creation");
	xhr.open("POST", "request", true);
	//alert("after open");
	//lert(JSON.stringify(requests));
	xhr.send(JSON.stringify(requests));
	//alert("after send");
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

function addRequests(){
	var xhr = new XMLHttpRequest();
	
}

function getRequests(){
	//alert("getRequests start");
	var xhr = new XMLHttpRequest();
	//alert('1');
	xhr.open("GET", "requestList" , true);
	//alert('2');
	xhr.send();
	//alert('3');

	xhr.onreadystatechange = function(){
		//alert("3.5");
		if(xhr.readyState == 4 && xhr.status == 200){
			//alert('4');
			var requests = JSON.parse(xhr.responseText);
			//alert("4.5");
			if(requests.length == 0){
				//alert("5 no requests");
				console.log("You have no requests currently:");
			}
			else{
				//alert("5 requests.length not zero");
				//alert(xhr.responseText);
				//alert(requests);
				console.log("testing----");
				//requestList = JSON.stringify("data") + ":" + requestList;
				console.log(requests);
				//alert("6");
				var data = formatTable(requests);
				//alert("7");
				$('#requestTable').DataTable({
					data : data,
					columns: [
						{data : "Request" },
						{data : "Employee"},
						{data : "Status" },
						{data : "Event_Type"},
						{data : "Grading_Format"},
						{data : "Event_Date"},
						{data : "Location"},
						{data : "Cost"},
						{data : "Description"},
						{data : "Justification"},
						{data : "Days_Missed"},
						{data : "Attachments"}
						]
				});
				//alert("8");
				console.log("this should have worked");
				$('#requestTable').show();
			}
		} else{
			//alert("4 else");
		}
		
	}
}


function formatTable(requestList){
	//alert("inside formatTable");
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < requestList.length; i++){
		let temp = new Object();
		
		console.log(requestList[i]);
		temp.Request = `RQID000${requestList[i].requestsId}`;
		temp.Employee = `EMID000${requestList[i].employeeId}`;
		temp.Status = requestList[i].requestsStatus;
		temp.Event_Type = requestList[i].requestsType ;
		temp.Grading_Format = requestList[i].requestsGradingFormat;
		temp.Event_Date = `15-MAR-2018`; //requestList[i].requestsDate
		temp.Location = requestList[i].requestsLocation;
		temp.Cost = requestList[i].requestsCost;
		temp.Description = requestList[i].requestsDescription;
		temp.Justification = requestList[i].requestsJustification;
		temp.Days_Missed = requestList[i].requestsDaysMissed;
		temp.Attachments = requestList[i].requestsAttachments;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}