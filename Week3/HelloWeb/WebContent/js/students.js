document.getElementById("add").addEventListener("click", add , false);
var count = 1000;
function add(){
	//get data from input fields 
	//var id = document.getElementById("id").value;
	var name = document.getElementById("name").value;
	var major = document.getElementById("major").value;
	
	//create row 
	var row = document.createElement("tr");
	
	//create row cells
	var cell1 = document.createElement("td");
	var cell2 = document.createElement("td");
	var cell3 = document.createElement("td");
	
	//add data to cells
	cell1.innerHTML = count++;
	cell2.innerHTML = name;
	cell3.innerHTML = major;
	
	//clear out input elements
	document.getElementById("name").value = "";
	document.getElementById("major").value = "";
	
	//append elements
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	document.getElementById("students").appendChild(row);	
}