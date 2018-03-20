window.onload = function(){
	loadLanding();
}
//=================================================
function loadLanding(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","loadlanding.view",true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#login').on('click',login);
			//after text is loaded , add event listeners/functionality to view
			$('#register').on('click', loadRegister);
		}
	}
}
//================================================
function loadRegister(){
	//console.log("in loadRegister()");
	var xhr = new XMLHttpRequest();
	//console.log("1");
	xhr.open("GET", "loadregister.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#message').hide();
			//console.log(xhr.responseText);
			$('#uname').blur(validate);
			$('#register').click(register);
			$('#return').click(loadLanding);
		}
	}
}
//===============================================
function loadHome(user){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadhome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#name').html(user.firstname);
			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getUserRequests();
			$('#reqTable').hide();
		}
	}
}
//================================================
function loadNav(){
	//console.log(user.firstname)
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadnav.view" , true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#navbar').html(xhr.responseText);
			//$('#home').on('click',);
			$('#logout').click(logout);
			$('#created').click(loadNewRequest);

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
		}
	}
}
//==================================================
function loadNewRequest(){
	//console.log("in loadRegister()");
	var xhr = new XMLHttpRequest();
	//console.log("1");
	xhr.open("GET", "loadnewrequest.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#submitRequest').click(create);
			//$('#return').click(loadHome(user));
			//$('#submitRequest').click(alert(1));
			//$('#backToEmployee').click(alert(2));
			$('#backToEmployee').click(loadHome);
		}
	}
}
//*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+
function login(){
//	alert('listen button works!');
	$('#message').hide();
	var email=$('#username').val();
	var password=$('#pass').val();

	var toSend = [email,password];					//toSend
	var xhr = new XMLHttpRequest();
	xhr.open("POST","login",true);
	xhr.send(JSON.stringify(toSend));				//stringify

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//console.log(xhr.readyState);
			//console.log(xhr.status);
			user = JSON.parse(xhr.responseText);
			var message = "";
			if(user == null){
				$('#message').show();
				$('#message').attr("style","display:inline");
				message = "Incorrect username or password";
				$('#message').html(message);
			}else{
				//alert("success");
				loadNav();
				loadHome(user);
			}
		}
	}
}
//========================================
function register(){ 
	//alert('register button');
	//console.log("register");
	var fn = $('#fn').val();
	//console.log(fn);
	var ln = $('#ln').val();
	//console.log(ln);
	var uname = $('#uname').val();
	//console.log(username);
	var pass = $('#pass').val();
	//console.log(pass);
	//var ln = $('#bday').val();
	var addr = $('#addr').val();
	var zip = $('#zip').val();
	var title = $('#title').val();
	var departmentId = $('#dept').val();
	var app = $('#app').val();
	var pen = $('#pen').val();

	if(fn=="" || ln=="" || uname=="" || pass==""){
		alert('one or more fields empty');
	}else{

		var user = {
				"firstname": fn, 
				"lastname": ln, 
				"username": uname, 
				"password": pass,
				"address": addr,
				"zipcode": zip,
				"title": title,
				"departmentId":dept,
				"approvedFunds": app,
				"pendingFunds": pen
		};

		var xhr = new XMLHttpRequest();
		xhr.open("POST", "register", true);
		xhr.send(JSON.stringify(user));

		loadNav();
		loadHome(user);
	}
}
//========================================
function validate(){
	//console.log("js validate");
	var username = $('#uname').val();

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "validate", true);
	xhr.send(JSON.stringify(username));

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var exists = JSON.parse(xhr.responseText);
			if(exists){
				$('#message').html('Username taken');
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
//==========================================

function getUserRequests(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "requests" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);
			if(requests.length == 0){
				//console.log("you have no reimbursement requests");
			}
			else{
				//console.log(requests);
				var data = formatTable(requests);

				$('#reqTable').DataTable({
					data : data,
					columns: [
						{data : "requestId" },
						{data : "eventType" },
						{data : "startDate" },
						{data : "endDate" },
						{data : "location" },
						{data : "description" },
						{data : "cost" },
						{data : "gradingStyleId" },
						{data : "grade" },
						{data : "requestDate" },
						{data : "requestTime" },
						{data : "flaggedId" },
						{data : "approvalId" }
						]
				});
				//console.log("this should have worked");
				$('#reqTable').show();
			}
		}
	}
}
//===================================
function formatTable(requests){
	//console.log("formatting table");
	var data = [];
	for(let i = 0; i < requests.length; i++){
		let temp = new Object();
		//console.log(requests[i]);
		temp.requestId = requests[i].requestId;
		temp.eventType = requests[i].eventType;
//		temp.startDate = requests[i].startDate;
		var a = new Date(requests[i].startDate);
		temp.startDate = a.customFormat("#DD#-#MMM#-#YYYY#");
//		temp.endDate = requests[i].endDate;
		var b = new Date(requests[i].endDate);
		temp.endDate = b.customFormat("#DD#-#MMM#-#YYYY#");
		temp.location = requests[i].location;
		temp.description = requests[i].description;
		temp.cost = requests[i].cost;
		temp.gradingStyleId = requests[i].gradingStyleId;
		temp.grade = requests[i].grade;
//		temp.requestDate = requests[i].requestDate;
		var c = new Date(requests[i].requestDate);
		temp.requestDate = c.customFormat("#DD#-#MMM#-#YYYY#");
//		temp.requestTime = requests[i].requestTime;
		var d = new Date(requests[i].requestTime);
		temp.requestTime = d.customFormat("#h#:#mm# #ampm#");
		temp.flaggedId = requests[i].flaggedId;
		temp.approvalId = requests[i].approvalId;

		console.log(temp);
		data.push(temp);
	}
	//console.log(data);
	return data;
}

//==========================================
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
//============================================

function create(){ 
	var type = $('#type').val();
	//console.log(fn);
	var start = $('#start').val();
	//console.log(ln);
	var end = $('#end').val();
	//console.log(username);
	var loc = $('#location').val();
	//console.log(pass);
	//var ln = $('#bday').val();
	var desc = $('#description').val();
	var cost = $('#cost').val();
	var grade = $('#grade').val();

	var email = user.email;

	//console.log((fn=="" || ln=="" || uname=="" || pass==""));
	var t = new Date(Date.now());
	var s = new Date(Date.now());
	tt = t.customFormat("#YYYY#-#MM#-#DD#");
	ss = s.customFormat("#YYYY#-#MM#-#DD#T#hh#:#mm#:#ss#");
	var newData = {
			"eventType": type, 
			"startDate": start, 
			"endDate": end, 
			"location": loc,
			"description": desc,
			"cost": cost,
			"gradingStyleId": null,
			"grade": grade,
			"requestDate": tt,
			"requestTime": ss,
			"flaggedId":0,
			"approvalId":0,
			"u":user.id
	};
	console.log('1');
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "newrequest", true);
	//console.log(JSON.stringify(user));
	xhr.send(JSON.stringify(newData));
	console.log(JSON.stringify(newData));

	loadNav();
	loadHome(user);

}

//============================================
//*** This code is copyright 2002-2016 by Gavin Kistner, !@phrogz.net
//*** It is covered under the license viewable at http://phrogz.net/JS/_ReuseLicense.txt
Date.prototype.customFormat = function(formatString){
	var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
	YY = ((YYYY=this.getFullYear())+"").slice(-2);
	MM = (M=this.getMonth()+1)<10?('0'+M):M;
	MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
	DD = (D=this.getDate())<10?('0'+D):D;
	DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
	th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
	formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
	h=(hhh=this.getHours());
	if (h==0) h=24;
	if (h>12) h-=12;
	hh = h<10?('0'+h):h;
	hhhh = hhh<10?('0'+hhh):hhh;
	AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
	mm=(m=this.getMinutes())<10?('0'+m):m;
	ss=(s=this.getSeconds())<10?('0'+s):s;
	return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
};

/*
 token:     description:             example:
#YYYY#     4-digit year             1999
#YY#       2-digit year             99
#MMMM#     full month name          February
#MMM#      3-letter month name      Feb
#MM#       2-digit month number     02
#M#        month number             2
#DDDD#     full weekday name        Wednesday
#DDD#      3-letter weekday name    Wed
#DD#       2-digit day number       09
#D#        day number               9
#th#       day ordinal suffix       nd
#hhhh#     2-digit 24-based hour    17
#hhh#      military/24-based hour   17
#hh#       2-digit hour             05
#h#        hour                     5
#mm#       2-digit minute           07
#m#        minute                   7
#ss#       2-digit second           09
#s#        second                   9
#ampm#     "am" or "pm"             pm
#AMPM#     "AM" or "PM"             PM

var now = new Date;
console.log( now.customFormat( "#DD#/#MM#/#YYYY# #hh#:#mm#:#ss#" ) );
 */

