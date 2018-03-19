/**
 * 
 */

window.onload = function () {
	loadLogin();
}

function loadLogin() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlogin.view", true);
	xhr.send();
	console.log("load login.")
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
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
	$('#message').hide();
	var username = $('#username').val();
	var password = $('#pass').val();

	var toSend = [username, password];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "login", true);
	xhr.send(JSON.stringify(toSend));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			console.log(user)
			var message = "";
			if(user==null) {
				$('#message').show();
				$("#message").attr("style", "display:inline");
				message = "You have entered the wrong username and/or password. Please try again";
				$('#message').html(message);
			}
			else{
				loadNav();
				loadHome(user);
			}
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
			$('.hide').hide();
			$('#username').blur(function() {
				validate($('#username').val());
			})
			$('#reg_submit').click(register);
			$('#cancel').click(loadLogin);
		}
	}
}

function validate(username){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "validate_username?username="+username, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var resp =xhr.responseText;
			if(resp=="true") {
				$("#un-danger-taken").hide();
				return true;
				//console.log("Took the true option.");
			} else {
				$("#un-danger-taken").show();
				return false;
				//console.log("Took the false option.");
			}
		}
	}
}

function register(){
	$('.hide').hide();
	console.log("register");
	var fn = $('#firstName').val();
	var ln = $('#lastName').val();

	var sID = $('#supID').val();
	if (sID == "") sID = -1;

	var dhID = $('#dhID').val();
	if (dhID == "") dhID = -1;

	var bencoID = $('#BenCoID').val();
	if (bencoID == "") bencoID = -1;

	var dep = $('#department').val();
	var jt = $('#job').val();
	var un = $('#username').val();
	var pass = $('#pass').val();
	var pass2 = $('#passVerify').val();
	var acctype = $('#account_type').val();
	var valid = validate(un)
	var good = (fn&&ln&&un&&pass&&pass2&&(pass==pass2)&&valid) ? true : false;
	var dest = "";
	if (acctype==0) {
		dest = "reg_employee";
	} else if (acctype==1) {
		dest = "reg_supervisor";
	} else if (acctype==2) {
		dest = "reg_dephead";
	} else if (acctype==3) {
		dest = "reg_benco";
	}
	if (good) {
		var user = {
				firstName: fn,
				lastName: ln,
				supervisorId: sID,
				depHeadId: dhID,
				benCoId: bencoID,
				department: dep,
				jobTitle: jt,
				username:un,
				password:pass
		}
		var xhr = new XMLHttpRequest();
		xhr.open("POST", dest, true);
		xhr.send(JSON.stringify(user));
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var loadedUser = JSON.parse(xhr.responseText);
				console.log(loadedUser);
				if (loadedUser.id != -1) {
					login();
				}
			}
		}
	} else {
		if(!fn) {
			$("#fn-danger").show();
		}
		if(!ln) {
			$("#ln-danger").show();
		}
		if(!un) {
			$("#un-danger-needed").show();
		}
		if(!pass) {
			$("#pw-danger").show();
		}
		if(!pass2) {
			$("#pw2-danger-needed").show();
		} else if(pass!=pass2) {
			$("#pw2-danger").show();
		}
	}
}

function loadNav(user){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			$('#home').on('click',reloadHome);
			$('#logout').click(logout);

		}
	}
}

function reloadHome() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "user", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var user = JSON.parse(xhr.responseText);
			loadHome(user);
		}
	}
}

function loadHome(user) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			$("#view").html(xhr.responseText);
			$("#name").html(user.wholeName);
			var emp_type = ((user.type < 2) ? 
					((user.type == 0) ? "Employee": "Supervisor") : 
						((user.type == 2) ? "Department Head" : "Benefits Coodinator"));
			$("#employee_type").html(emp_type);
			$("#sub_rr_button").on("click", loadSubmitReimbursementForm);
			$("#paid_reimbursements").html(user.paidReimbursments);
			$("#remaining_reimbursements").html(1000-user.paidReimbursments);
			if (user.type != 0) {
				$("#approve_rr").show();
				$("#approve_rr").addClass("col-lg-2");
				$("#home_view").addClass("col-lg-10");
				$("#pending_button").on("click", function() {
					loadManageRequests(user);
				})
			}
			//TODO: Populate table
		}
	}
}

function loadSubmitReimbursementForm() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadForm.view", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			$("#view").html(xhr.responseText);
			//TODO: form submission triggers.
			var xhr2 = new XMLHttpRequest();
			xhr2.open("GET", "user", true);
			xhr2.send();
			xhr2.onreadystatechange = function() {
				if (xhr2.readyState == 4 && xhr2.status == 200) {
					var user = JSON.parse(xhr2.responseText);
					$("#name").html(user.wholeName);
					$("#form_submit").on("click", function () {
						submitReimbursementForm(user)
					});
					$("#cancel").on("click", function() {
						console.log("Canceled");
						loadHome(user);
					})
				}
			}
		}
	}
}

function submitReimbursementForm(user) {
	//TODO: submit the form
	console.log("Submitted");
	loadHome(user);
	
}



function loadManageRequests(user) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadManageRequests.view", true);
	xhr.send();

	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var xhr2 = new XMLHttpRequest();
			var t = user.roll;
			var requestAddr = "pending_approval?type="+t+"&id=";
			requestAddr += (t==1) ? user.dsId : ((t==2) ? user.myDepHeadId : user.myBenCoId);
			console.log(requestAddr);
			xhr2.open("GET", requestAddr, true);
			xhr2.send();
			xhr2.onreadystatechange = function() {
				if (xhr2.readyState == 4 && xhr2.status == 200) {
					$("#view").html(xhr.responseText);
					//TODO: Fill with response values
				}
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