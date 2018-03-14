window.onload = function(){
	initialize();
	$('#add').on('click', addUser);
}
var users;


function addUser(){
	
}

function initialize(){
	//GET DATA FROM SERVLET
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "users", true);
	xhr.send();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			users = JSON.parse(data);
			console.log(users);
			populateData(users);
		}
	}
}

function populateData(users){
	for(let i = 0; i < users.length; i++){
		let name = users[i].name;
		let bio = users[i].bio;
		let email = users[i].email;
		
		let temp = document.createElement('tr');
		let cell1 = document.createElement('td');
		let cell2 = document.createElement('td');
		let cell3 = document.createElement('td');
		
		cell1.innerHTML = name;
		cell2.innerHTML = bio;
		cell3.innerHTML = email;
		
		temp.append(cell1);
		temp.append(cell2);
		temp.append(cell3);
		
		$('#users').append(temp);
	}
	
}
