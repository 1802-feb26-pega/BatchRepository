/**
 * 
 */
window.onload = function () {
	populateData();
}

var users;
var count = 1;
function populateData() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET","users",true);
	xhr.send();
	xhr.onreadystatechange = function() {
		console.log(count);
		count += 1;
		if (xhr.readyState == 4 && xhr.status == 200) {
			var data = xhr.responseText;
			users = JSON.parse(data);
			console.log(users);
		}
	}
}