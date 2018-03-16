window.onload = function(){
	initialize();
	$("#add").on("click", addUser);
}
var users;

function addUser(){
	var user = {};
	user.name = $("#name").val();
	user.bio = $("#bio").val();
	user.email = $("#email").val();

	var data = JSON.stringify(user);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "users", true);
	xhr.send(data);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let u = [user];
			populateData(u);
		}
	}
}





function initialize(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "users", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			users = JSON.parse(data);
			populateData(users);
		}
	}
}

function populateData(u){
	for(let i = 0; i < u.length; i++){
		let name = u[i].name;
		let bio = u[i].bio;
		let email = u[i].email;

		let temp = document.createElement("tr");

		let cell1 = document.createElement("td");
		let cell2 = document.createElement("td");
		let cell3 = document.createElement("td");

		cell1.textContent = name;
		cell2.textContent = bio;
		cell3.textContent = email;

		temp.appendChild(cell1);
		temp.appendChild(cell2);
		temp.appendChild(cell3);
		$("#users").append(temp);
	}	
}