/**
 * 
 */

window.onload = function(){
	populateData();
}
var users;
function populateData(){
	//get data from servlet
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "users", true);
	xhr.send();
	
	xhr.onreadstatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			users = JSON.parse(data);
			console.log(users);
		}
	}
	//add data to table
}