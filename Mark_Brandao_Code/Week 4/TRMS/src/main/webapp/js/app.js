window.onload = function(){ //1 
	loadLanding();
}

function loadLanding(){ //2 
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$('#login').on('click', login); 
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			$('#register').on('click', loadRegister);
			$("#message").hide();
			// after text is loaded, add event listeners/functionality to view
		}
	}
}


function login(){
	$("#message").hide();
	var email = $("#email").val();
	var password = $("#pass").val();

	var toSend = [email, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			var message = "";
			if(employee == null){
				$("#message").show();
				message = "You have entered the wrong username and/or password. Please try again.";
				$("#message").html(message);
			} else {
				//alert("success");
				loadNav(employee);
				loadHome(employee);
			}
		}
	}
}

function loadRegister(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadregister.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			getDepartments();

			$("#landing").click(loadLanding);
			$("#deptmessage").hide();
			$("#supmessage").hide();
			$("#emailmessage").hide();
			$("#passmessage").hide();
			$("#success").hide();

			$("#dept").blur(validateDept);
			$("#suplastname").blur(validateSup);
			$("#email").blur(validateEmail);
			$("#vpass").blur(validatePass);
			$("#doregister").click(register);
		}
	}
}

function validateDept(){
	if($("#dept").val() == ""){
		$("#deptmessage").html("You must select a department.");
		$("#deptmessage").show();
		$("#doregister").attr("disabled", true);
	} else {
		$("#deptmessage").hide();
		$("#doregister").attr("disabled", false);
	}
}

function validateSup(){
	var xhr = new XMLHttpRequest();
	var supname = [$("#supfirstname").val(), $("#suplastname").val()];
	xhr.open("POST", "supervisor", true);
	xhr.send(JSON.stringify(supname));
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var supCondition = JSON.parse(xhr.responseText);
			if(supCondition == -2){
				$("#supmessage").html("Sorry, that person cannot be your supervisor. Please try again.");
				$("#supmessage").show();
				$("#doregister").attr("disabled", true);
			} else if(supCondition == -1){
				$("#supmessage").html("Sorry, that doesn't look like a real name. Please try again.");
				$("#supmessage").show();
				$("#doregister").attr("disabled", true);
			} else {
				$("#supmessage").data("supid", supCondition);
				$("#supmessage").hide();
				$("#doregister").attr("disabled", false);
			}
		}
	}
}

function validatePass(){
	if($("#vpass").val() != $("#pass").val()){
		$("#passmessage").html("Passwords do not match.");
		$("#passmessage").show();
		$("#doregister").attr("disabled", true);
	} else {
		$("#passmessage").hide();
		$("#doregister").attr("disabled", false);
	}
}



function validateEmail(){
	var email = $("#email").val();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validateemail", true);
	xhr.send(JSON.stringify(email));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var emailCondition = JSON.parse(xhr.responseText);
			if(emailCondition == 2){
				$("#emailmessage").html("Sorry, that email is already registered. Please try again.");
				$("#emailmessage").show();
				$("#doregister").attr("disabled", true);
			} else if(emailCondition == 1){
				$("#emailmessage").html("Sorry, that doesn't look like a valid email. Please try again.");
				$("#emailmessage").show();
				$("#doregister").attr("disabled", true);
			} else {
				$("#emailmessage").hide();
				$("#doregister").attr("disabled", false);
			}
		}
	}
}

function register(){
	console.log("registering");
	var fn = $("#firstname").val();
	var ln = $("#lastname").val();
	var em = $("#email").val();
	var password = $("#pass").val();
	var dept = $("#dept").val();
	var sup = $("#supmessage").data("supid");
	var level = 1;
	if($("#supervisor").prop("checked")){
		level = 2;
	} 
	console.log(dept);


	var employee = {
		firstname: fn,
		lastname: ln,
		email: em,
		pass: password,
		supervisorId: sup,
		departmentId: dept,
		levelId: level,
		availableReimbursement: 1000,
		pendingReimbursement: 0,
		awardedReimbursement: 0
	};

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "register", true);
	xhr.send(JSON.stringify(employee));
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#success").show();
		}
	}
}


function loadNav(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#navigation").html(xhr.responseText);
			$("#gohome").on("click", function(){
				$("li").removeClass("active");
				$(this).parent().addClass("active");
				loadHome(employee);
			});

			$("#reqform").on("click", function(){
				$("li").removeClass("active");
				$(this).parent().addClass("active");
				loadReqForm(employee);
			})

			$("#emplevel").on("click", function(){
				$("li").removeClass("active");
				$(this).parent().addClass("active");
				loadReqApprovals(employee);
			})

			$("#logout").click(logout);
			if(employee.levelId >1){
				$("#emplevel").show();
			} else {
				$("#emplevel").hide();
			}
		}
	}
}

function getDepartments(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "department", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var depts = JSON.parse(xhr.responseText);	
			$("#dept").append('<option value="">Department</option');
			for(let i = 0; i < depts.length; i++){
				$("#dept").append('<option value="' + depts[i].departmentId + '">' + depts[i].departmentName + '</option>');
			}
		}
	}
}

function loadReqForm(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadreqform.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$("#submitRequestForm").on("click",submitRequestForm);
			$("#submitmessage").hide();
			$("#cost").keyup(calcExpected)
		}
	}
}

function calcExpected(){
	var cost = $("#cost").val();
	var type = $("#eventTypeSelector").val();
	var multiplier = 0;
	switch(type){
		case "1":
			multiplier = .8;
			console.log(multiplier);
			break;
		case "2":
			multiplier = .6;
			console.log(multiplier);
			break;
		case "3":
			multiplier = .75;
			console.log(multiplier);
			break;
		case "4":
			multiplier = 1;
			console.log(multiplier);
			break;
		case "5":
			multiplier = .9;
			console.log(multiplier);
			break;
		case "6":
			multiplier = .3;
			console.log(multiplier);
			break;
	}
	$("#expectedReimbursement").val(cost*multiplier);
}

function submitRequestForm(){
	var location = $("#location").val();
	var description = $("#description").val();
	var type = $("#eventTypeSelector").val();
	var cost = $("#cost").val();
	var wmissed = $("#workTimeMissed").val();
	var expected = $("#expectedReimbursement").val();
	var date = $("#startDate").val();
	var grade = $("#gradingFormatSelector").val();
	var just = $("#justification").val();

	var info = [location, description, type, cost, wmissed, expected, date, grade, just];

	console.log(info);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "requestform", true);
	xhr.send(JSON.stringify(info));
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			alert("Submission successful!")
			loadHome(employee);
		}
	}
}

function loadReqApprovals(employee){

}

function loadHome(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$("#name").html(employee.firstname);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getEmployeeRequests();
			$("#reimTable").hide();
		}
	}
}

function logout(){
	console.log("logging out");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("Attempting to redirect");
			window.location.replace("index.html");
		}
	}
}

function getEmployeeRequests(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "requests", true);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var reims = JSON.parse(xhr.responseText);
			if(reims.length == 0){
				console.log("You have no accounts");
			} else {
				console.log("testing---");
				// reims = JSON.stringify("data") + ":" + accounts;
				console.log(reims);
				var data = formatTable(reims);

				$("#reimTable").DataTable({
					data : data,
					columns: [
						{data : "Id" },
						{data : "Status"}
					]
				});
				console.log("This should have worked");
				$("#reimTable").show();
			}
		}
	}

	xhr.send();
}

function formatTable(reims){
	console.log("formatting table");
	var data = [];
	for(let i = 0; i < reims.length; i++){
		let temp = new Object();
		console.log(reims[i]);
		temp.Id = reims[i].reimbursementId;
		temp.Status = reims[i].statusId;
		console.log(temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}
