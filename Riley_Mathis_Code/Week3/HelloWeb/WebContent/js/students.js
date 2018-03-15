/**
 * 
 */

document.getElementById("add").addEventListener("click", add, false);
var count = 1000;
function add(){
	//var id = document.getElementById("id").value;
	var name = document.getElementById("name");
	var major = document.getElementById("major");
	
	//create row
	var row = document.createElement("tr");
	
	//create row cells
	var cell1 = document.createElement("td");
	var cell2 = document.createElement("td");
	var cell3 = document.createElement("td");
	
	
	cell1.innerHTML = count++;
	cell2.innerHTML = name.value;
	cell3.innerHTML = major.value;
	
	name.value = "";
	major.value = "";
	
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	
	document.getElementById("students").appendChild(row);
	

}
