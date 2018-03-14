
const btnAdd = document.getElementById('add');
const students = document.getElementById('students');
btnAdd.addEventListener('click', add, false);

const ids = [];

function add() {
	const id = createId();
	ids.push(id);
	// Get data from input fields
	const name = document.getElementById('name');
	const major = document.getElementById('major');
	
	// Create row
	const row = document.createElement('tr');
	
	// Create row cells
	const cell1= document.createElement('td');
	const cell2 = document.createElement('td');
	const cell3 = document.createElement('td');
	
	// add data to cells
	cell1.innerHTML = id;
	cell2.innerHTML = name.value;
	cell3.innerHTML = major.value;
	
	// append elements
	row.appendChild(cell1)
	row.appendChild(cell2);
	row.appendChild(cell3);

	students.appendChild(row);	
}

function createId() {
	let id = 0;
	let unique = true;
	do {
		id = randomId();
		for (let i in ids) {
			if (ids[i] == id) {
				unique = false;
				break;
			}
		}
	} while (!unique);

	return id;
}

function randomId() {
	return randomInt(1, 10001 + ids.length);
}

function randomInt(floor, roof) {
	return floor + (Math.floor(Math.random() * roof));
}
