var studentid = 1;

document.getElementById("add").addEventListener("click", add, false);

function add(){
	// get data from input fields
	// var id = document.getElementById("id").value;
	var name = document.getElementById("name").value;
	var major = document.getElementById("major").value;

	// create table row for new data
	var row = document.createElement("tr");

	// create row cells
	var cell1 = document.createElement("td");
	var cell2 = document.createElement("td");
	var cell3 = document.createElement("td");

	cell1.textContent = studentid++;
	cell2.textContent = name;
	cell3.textContent = major;

	// append elements
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);

	// add to table
	document.getElementById("students").appendChild(row);

	// clear input fields
	document.getElementById("name").value = "";
	document.getElementById("major").value = "";
}