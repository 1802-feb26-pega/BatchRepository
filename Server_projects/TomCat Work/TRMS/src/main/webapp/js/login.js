window.onload = function(){
	loadLanding();
}

var $  = require('jquery');
var dt = require('datatables.net')();

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
function loadNav(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			$('#home_page').click(function() {loadHome(employee);});
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
			$('#Department').html("Current Department: " + employee.dept);
			$('#form_start_button').click(function(){	loadForm(employee);	});
			$('#check_form').click( function(){	checkForms(employee);	});
			$('#approve_forms').click( function() {checkotherforms(employee)});
			if(employee.dept == "BenCo" & employee.sup == "NO"){
				$('#all_approve').show();
				$('#all_approve').click( function() {checkallforms(employee)});
			} if(employee.sup == "YES"){
				
				$('#approve_forms').show()
			}


			// after text is loaded, add event listeners/functionality to view
		}
	}
}


//loads check form view
function checkForms(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadcheckForm.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			loadClaims();
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

//Loads the request form
function loadForm(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadForm.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#header').html("Set up information here");
			$('#formcomplete').click( function(){
				formComplete(employee);
			});
			getEvents();
			// after text is loaded, add event listeners/functionality to view
		}
	}
}

function checkotherforms(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadcheckForm.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			otherforms(employee);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}
function checkallforms(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadcheckForm.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			check_all(employee);
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
				loadNav(employee);
				loadHome(employee);
			}
		}
	}
}

//Sumbits the form to database
function formComplete(employee){
	var evdt = $('#eventStartDate').val();
	var loc = $('#location').val();
	var event = $('#event').val();
	var cost = $('#cost').val();
	var reason = $('#reason').val();
	var evst = $('#eventStartTime').val();
	var passing = $('#currentGrade').val();
	var gF = $('#gradingFormat').val();
	var desc = $('#description').val();
	var workedmissed = $('#timedmissed').val();
	var notes = $('#comments').val();

//	var evdt = "2018/03/20";
//	var loc = "ATL";
//	var cost = 500;
//	var reason = "JS Training rampup for new role";
//	var evst = "9:30";
//	var passing = 80
//	var gF = 50
//	var desc = "JS Training"
//		var workedmissed = 5;
//	var notes = "Please pay me my money";

	var claim = {
			eventStartdate: evdt,
			loc: loc,
			event_type:event,
			cost: cost,			
			reason: reason,
			eventStarttime: evst,
			passing: passing,
			gradingFormat: gF,
			daysmissed: workedmissed,
			description: desc,
			comments: notes//{comments: notes}
	};



	var xhr = new XMLHttpRequest();
	xhr.open("POST", "form", true);
	xhr.send(JSON.stringify(claim));

	xhr.onreadystatechange = function(){
		var check = JSON.parse(xhr.responseText);

		if(xhr.readyState == 4 && xhr.status == 200){
			if(check != "0"){
				alert("Error has occured please try again");
				loadNav(employee);
				loadHome(employee);
			}
			else{
				alert("Form has been sumbitted");
				loadNav(employee);
				loadHome(employee)
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
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			window.location.replace("index.html");
		}

	}
}

//Grabs lists of events from event table dyanamically
function getEvents()
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "getEvents", true);
	xhr.send();

	xhr.onreadystatechange = function(){
		var events = JSON.parse(xhr.responseText);
		var item = [];
		if(xhr.readyState == 4 && xhr.status == 200){
			for (i = 1; i < events.length; i++) {
				item[i] = '<option> ' + events[i] + '</option>';
			} 
			$('#event').html(item);
		}

	}
}

//THIS CONTAINS THE  EMP DATATABLES
function loadClaims(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "checkforms" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var claims = JSON.parse(xhr.responseText);
			var data = claims_data(claims);
			$(document).ready( function () {
				$('#table_id').dataTable({
					destroy: true,
					data : data,
					columns: [
					          { data: 'Request ID', "defaultContent": ""  },
					          { data : 'Status', "defaultContent": ""  },
					          { data: 'Creation Date', "defaultContent": ""  },
					          { data: 'Event Date', "defaultContent": ""  },
					          { data: 'Amount given', "defaultContent": "0" },
					          { data: 'Location', "defaultContent": ""  },
					          { data: 'Event', "defaultContent": ""  },
					          { data: 'Cost', "defaultContent": ""  },
					          { data: 'Reason', "defaultContent": ""  },					        
					          { data: 'Event Start Time', "defaultContent": ""  },
					          { data: 'Grade', "defaultContent": ""  },
					          { data: 'Needed to pass', "defaultContent": ""  },
					          { data: 'Work Days missed', "defaultContent": ""  },
					          { data: 'Description of Class', "defaultContent": ""  },
					          { data: 'Comments', "defaultContent": ""  },
					          ]

				});
			} );
			// after text is loaded, add event listeners/functionality to view
		}
	}
}
function claims_data(claims){

	var data = [];
	for(let x = 0; x < claims.length; x++){
		let t = new Object();

		t["Request ID"] = claims[x].claim_id;
		t["Status"] = claims[x].status;
		t["Creation Date"] = claims[x].created;
		t["Event Date"] = claims[x].eventStartdate;
		t["Amount given"] = claims[x].amount_given;
		t["Location"] = claims[x].loc   	
		t["Event"] = claims[x].event_type;
		t["Cost"] = claims[x].cost;
		t["Reason"] = claims[x].reason;
		t["Event Start Time"] = claims[x].eventStarttime;
		t["Grade"] = claims[x].passing;
		t["Needed to pass"] = claims[x].gradingFormat;       
		t["Work Days missed"] = claims[x].daysmissed;
		t["Description of Class"] = claims[x].description;
		t["Comments"] = claims[x].comments;

		data.push(t);
	}

	return data;

}

//THIS CONTAINS DEPT HEAD DATATABLES
function otherforms(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "checkothers", true);
	xhr.send();

	xhr.onreadystatechange = function(){		
		if(xhr.readyState == 4 && xhr.status == 200){
			var claims = JSON.parse(xhr.responseText);
			var data = claims_data(claims);
			var d;
			$('#approve').click(function() {approve(d,employee);});
			$('#deny').click(function() {deny(d,employee);});
			$(document).ready( function () {
				
				var table = $('#table_id').DataTable({
					destroy: true,
					data : data,
					columns: [			    	
					          { data: 'Request ID', "defaultContent": ""  },
					          { data :'Status', "defaultContent": ""  },
					          { data: 'Creation Date', "defaultContent": ""  },
					          { data: 'Event Date', "defaultContent": ""  },
					          { data: 'Amount given', "defaultContent": "0" },
					          { data: 'Location', "defaultContent": ""  },
					          { data: 'Event', "defaultContent": ""  },
					          { data: 'Cost', "defaultContent": ""  },
					          { data: 'Reason', "defaultContent": ""  },
					          { data: 'Event Start Time', "defaultContent": ""  },
					          { data: 'Grade', "defaultContent": ""  },
					          { data: 'Needed to pass', "defaultContent": ""  },
					          { data: 'Work Days missed', "defaultContent": ""  },
					          { data: 'Description of Class', "defaultContent": ""  },
					          { data: 'Comments', "defaultContent": ""  },
					          ]
				});
				$('#table_id tbody').on('click','tr', function(){
					d = table.row(this).data();
					console.log("Data is " + d["Request ID"]);
					$('#approve').show();
					$('#deny').show();
				});
			} );
		}
	}
}

//Approves the claims
function approve(d,employee){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "approve", true);
	xhr.send(d["Request ID"]);

	xhr.onreadystatechange = function(){		
		if(xhr.readyState == 4 && xhr.status == 200){
			var check = xhr.responseText;
			if(check != "0"){
				alert("CLAIM: " + d["Request ID"] + " has been updated");
			}else{
				alert("Error has occured please try again");
			}
			checkotherforms(employee);
		}
	}
}


//Denies the claims
function deny(d,employee){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "denied", true);
	xhr.send(d["Request ID"]);

	xhr.onreadystatechange = function(){		
		if(xhr.readyState == 4 && xhr.status == 200){
			var check = xhr.responseText;
			if(check != "0"){
				alert("CLAIM: " + d["Request ID"] + " has been updated");
			}else{
				alert("Error has occured please try again");
			}
			checkotherforms(employee);
		}
	}
}

//CONTAINS THE BENCO DATATABLES
function check_all(employee){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "checkall", true);
	xhr.send();

	xhr.onreadystatechange = function(){		
		if(xhr.readyState == 4 && xhr.status == 200){
			var claims = JSON.parse(xhr.responseText);
			var data = claims_data(claims);
			var d;
			$('#approve').click(function() {approve_all(d,employee);});
			$('#deny').click(function() {deny_all(d,employee);});
			$(document).ready( function () {
				
				var table = $('#table_id').DataTable({
					destroy: true,
					data : data,
					columns: [			    	
					          { data: 'Request ID', "defaultContent": ""  },
					          { data :'Status', "defaultContent": ""  },
					          { data: 'Creation Date', "defaultContent": ""  },
					          { data: 'Event Date', "defaultContent": ""  },
					          { data: 'Amount given', "defaultContent": "0" },
					          { data: 'Location', "defaultContent": ""  },
					          { data: 'Event', "defaultContent": ""  },
					          { data: 'Cost', "defaultContent": ""  },
					          { data: 'Reason', "defaultContent": ""  },
					          { data: 'Event Start Time', "defaultContent": ""  },
					          { data: 'Grade', "defaultContent": ""  },
					          { data: 'Needed to pass', "defaultContent": ""  },
					          { data: 'Work Days missed', "defaultContent": ""  },
					          { data: 'Description of Class', "defaultContent": ""  },
					          { data: 'Comments', "defaultContent": ""  },
					          ]
				});
				$('#table_id tbody').on('click','tr', function(){
					d = table.row(this).data();
					console.log("Data is " + d["Request ID"]);
					$('#approve').show();
					$('#deny').show();
				});
			} );
		}
	}
}
//Approves all  the claims
function approve_all(d,employee){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "approve", true);
	xhr.send(d["Request ID"]);

	xhr.onreadystatechange = function(){		
		if(xhr.readyState == 4 && xhr.status == 200){
			var check = xhr.responseText;
			if(check != "0"){
				alert("CLAIM: " + d["Request ID"] + " has been updated");
			}else{
				alert("Error has occured please try again");
			}
			checkallforms(employee);
		}
	}
}


//Denies all the claims
function deny_all(d,employee){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "denied", true);
	xhr.send(d["Request ID"]);

	xhr.onreadystatechange = function(){		
		if(xhr.readyState == 4 && xhr.status == 200){
			var check = xhr.responseText;
			if(check != "0"){
				alert("CLAIM: " + d["Request ID"] + " has been updated");
			}else{
				alert("Error has occured please try again");
			}
			checkallforms(employee);
		}
	}
}

