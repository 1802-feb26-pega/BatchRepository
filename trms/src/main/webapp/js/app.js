let curU = null;

window.onload = function() {
	loadLanding();
};

function AJAX(method, url, callback, sendItem) {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4 || xhr.status != 200) {
            return;
        }
        callback(xhr.responseText);
    };
    
    sendItem = (method === 'POST' || method === 'PUT') ? (JSON.stringify(sendItem || "")) : null;
    xhr.send(sendItem);
}

function loadNav(user) {
	AJAX('GET', 'nav.view', function(response) {
		$('#navbar').html(response);
		$('#viewclaims').on('click', loadHome);
		$('#newclaim').on('click', loadClaimForm);
		if(user.type > 1)
			$('#approvelist').on('click', loadApprovableClaims);
		else
			$('#approvelist').hide();
		$('#logout').on('click', logout);
	});
}

function loadApprovableClaims(user){
	AJAX('GET', 'appr_requests.view', function(response) {
		$('#view').html(response);
		$('#approvablerequests').DataTable({
			ajax: 'claims/approvable',
			select: true,
			"columns":[
				{"data":"id"},
				{"data":"submitted"},
				{"data":"statusStr"},
				{"data":"description"},
				{"data":"eventTypeStr"},
				{"data":"amount"},
				{"data":"justification"},
				{"data":"gradeTypeStr"},
				{"data":"minPassingGrade"},
				{"data":"comments"}
			]
		});
		$('#submitdecision').on('click', sendDecision);
	});
}

function sendDecision(){
	let claimNum = parseInt($('#claimnum').val()),
		claimDecision = $('#decision').val();
	
	let out = [claimNum, claimDecision];
	
	console.log(out);
	
	AJAX('POST', 'claims/update', function(response){
		loadApprovableClaims();
	}, out);
}

function loadHome(user) {
	AJAX('GET', 'home.view', function(response) {
		$('#view').html(response);
		$('#ownrequests').DataTable({
			ajax: 'claims/personal',
			select: true,
			"columns":[
				{"data":"id"},
				{"data":"submitted"},
				{"data":"statusStr"},
				{"data":"description"},
				{"data":"eventTypeStr"},
				{"data":"amount"},
				{"data":"justification"},
				{"data":"gradeTypeStr"},
				{"data":"minPassingGrade"},
				{"data":"comments"}
			]
		});
	});
}

function loadLanding(){
	AJAX('GET', 'landing.view', function(response) {
		$('#view').html(response);
		$('#login').on('click', login);
		$('#pw').keydown(function(ev) {
			let key = ev.keyCode || ev.which;
			if(key === 13) login();
		});
		$('#reg').on('click', loadRegister);
	});
}

function loadClaimForm() {
	AJAX('GET', 'request.view', function(response) {
		$('#view').html(response);
		$('#submit').on('click', sendClaimRequest);
	});
}
function sendClaimRequest(){
	let eventdate = $('#evdate').val(),
		eventtime = $('#evtime').val(),
		eventlocation = $('#evloc').val(),
		eventcost = $('#evcost').val(),
		eventtype = $('#evtype').val(),
		eventdesc = $('#evdesc').val(),
		gradingformat = $('#gf').val(),
		minpassinggrade = $('#mpg').val(),
		requestreason = $('#reqreason').val(),
		requestcomments = $('#comments').val();
	
	if(!eventdate || !eventtime     || !eventlocation 
			      || !eventcost     || !eventtype 
			      || !eventdesc 
			      || !gradingformat || !minpassinggrade 
			      || !requestreason || !requestcomments){
		alert('fill out all of the fields, goit');
		return;
	}
	
	let claim = {
		employeeId: curU.id,
		location: eventlocation,
		eventType: parseInt(eventtype),
		eventDate: eventdate,
		minPassingGrade: minpassinggrade,
		description: eventdesc,
		comments: requestcomments,
		amount: parseFloat(eventcost),
		justification: requestreason,
		gradeType: gradingformat
	};
	
	console.log(claim);
	
	AJAX('POST', 'claims/submit', function(response) {
		console.log(response);
		loadHome();
	}, claim);
}


function checkEmail() {
	
}

function loadRegister(){
	AJAX('GET', 'register.view', function(response){
		$('#view').html(response);
		$('#register').click(register);
	});
}

function login(){
	
	let email = $('#email').val();
	let pw = $('#pw').val();
	let reqbody = [email, pw];
	
	AJAX('POST', 'login', function(responseText){
		let user = JSON.parse(responseText);
		let msg = '';
		if(!user) {
			console.log('login failed');
		} else {
			curU = user;
			loadNav(user);
			loadHome(user);
			console.log('logged in');
		}
	}, reqbody);
}

function logout() {
	AJAX('GET','logout', function() {
		console.log('later, goit');
		window.location.replace('/trms');
	});
}

function register() {
	let fn = $('#fname').val(),
		ln = $('#lname').val(),
		em = $('#email').val(),
		pw = $('#pw').val(),
		cpw = $('#cpw').val(),
		etype = $('#emptype').val(),
		sfn = $('#sfname').val(),
		sln = $('#slname').val(),
		dpn = $('#deptnumber').val();
	
	if(!fn || !ln    || !em 
		   || !pw    || !cpw 
		   || !etype || !sfn 
		   || !sln   || !dpn){
		alert('Please complete all fields');
		return;
	}
	
	if(pw !== cpw){
		alert('Passwords must match');
		return;
	}
	
	let user = {
		firstname: fn,
		lastname: ln,
		email: em,
		password: pw,
		type: etype,
		supervisorf: sfn,
		supervisorl: sln,
		departmentId: dpn
	};
	
	AJAX('POST', 'register', function(response) {
		let user = JSON.parse(response);
		console.log(user);
		loadLanding();
	}, user);
}