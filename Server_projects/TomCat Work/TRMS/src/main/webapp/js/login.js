window.onload = function(){
	loadLanding();
}

var employee;

//Creates the basic templete for Dynamic one page site
function loadLanding(){
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
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

//Loads the Nav bar
function loadNav(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			$('#home_page').on('click',loadHome);
			$('#logout').on('click',logout);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

//Loads home screen after log in
function loadHome(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#homeheader').html("Welcome " + employee.firstname);
			$('body').on('click','#form_start_button',loadForm);
			$('body').on('click', '#check_form',checkForms);

			// after text is loaded, add event listeners/functionality to view
		}
	}
}


//loads check form view
function checkForms(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadcheckForm.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			//$('#header').html("Set up information here");
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

//Loads the request form
function loadForm(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadForm.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#header').html("Set up information here");
			$('#formcomplete').on('click',formComplete)
			// after text is loaded, add event listeners/functionality to view
		}
	}
}


//---------------------------------------------FUNCTIONS THAT POST----------------------------------------------------





//login method to handle user information
function login(){
	//$('#username').on('click', $('#message').hide());
	var email = $('#email').val();
	var password = $('#pass').val();

	var toSend = [email, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var employee = JSON.parse(xhr.responseText);
			if(employee==null) {
				$('#message').show();
				$('#message').attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			}
			else{
				//alert(employee.firstname + employee.lastname);
				loadNav();
				loadHome(employee);
			}
		}
	}
}

//Sumbits the form to database
function formComplete(){
//	var evdt = $('#eventStartDate').val();
//	var loc = $('#location').val();
//	var event = $('#event').val();
//	var cost = $('#cost').val();
//	var reason = $('#reason').val();
//	var evst = $('#eventStartTime').val();
//	var passing = $('#currentGrade').val();
//	var gF = $('#gradingFormat').val();
//	var desc = $('#description').val();
//	var workedmissed = $('#timedmissed').val();
	
	var evdt = "2018/03/20";
	var loc = "ATL";
	var event = "Seminar";
	var cost = 500;
	var reason = "JS Training rampup for new role";
	var evst = "9:30";
	var passing = 80
	var gF = 50
	var desc = "JS Training"
	var workedmissed = 2;
	var note = "Please pay me my money";
	
	var claim = {
			eventStartdate: evdt,
			loc: loc,
			event_type:event,
			cost: cost,			
			event_type: event,
			reason: reason,
			eventStarttime: evst,
			passing: passing,
			gradingFormat: gF,
			daysmissed: workedmissed,
			description: desc
	};
	

	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "form", true);
	xhr.send(JSON.stringify(claim));

	xhr.onreadystatechange = function(){
		var check = JSON.parse(xhr.responseText);

		if(xhr.readyState == 4 && xhr.status == 200){
			if(check != "0"){
				alert("Error has occured please try again");
				loadNav();
				loadHome(employee);
			}
			else{
				alert("Form has been sumbitted");
				loadNav();
				loadHome(employee);
			}
		}

	}
}

//Log out code
function logout(){

	//Here, we need o invalidate session and go back to login page
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout" , true);
	xhr.send();
}

