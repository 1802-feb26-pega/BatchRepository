//Intro to DOM manipulation

/**
 * 1. Fibonacci
 * Define function fib(n)
 * Return the nth number in the fibonacci sequence.
 */

function fib(n){
	
	if(n <= 1){
		return n;
	}
	else{
		return (fib(n-1) + fib(n-2));
	}
}

function runFib(){
	//alert("hello");
	
	var input = document.getElementById("fib").value;
	var display = document.getElementById("fibDisplay");
	display.innerHTML = fib(input);
	
}

function increaseCount(){
	var currentNumber = document.getElementById("counter").innerHTML;
	var counter = document.getElementById("counter");
	counter.innerHTML = 1 + Number(currentNumber);
}

document.getElementById("doFib").addEventListener("click", runFib);
document.getElementById("counter").addEventListener("mouseover", increaseCount)


//Event Propagation and Drag Drop

var capture = true; //false = bubbling
/*
document.getElementById("inner").addEventListener("click", function(e) {
	e.stopPropagation();
	alert("Inner!");
}, capture);
document.getElementById("middle").addEventListener("click", function() {
	alert("Middle!");
}, capture);
document.getElementById("outer").addEventListener("click", function() {
	alert("Outer!");
}, capture);

*/
function click(event){
	console.log(event.currentTarget + " " + counter++);
	event.stopPropagation();
}

function allowDrag(event){
	console.log(`dragging over: ${event.target.id} `);
	event.preventDefault();
}

function drag(event){
	event.stopPropagation();
	console.log(`starting to drag element: ${event.target.id}`);
}

function drop(event){
	event.preventDefault();
	var data = event.dataTransfer.getData("text");
	event.target.insertBefore(document.getElementById(data), event.target.firstChild);
}

document.getElementById("outer").addEventListener("click", click, capture);
document.getElementById("outer").addEventListener("drop", drop, capture);
document.getElementById("outer").addEventListener("dragover", allowDrag, capture);
document.getElementById("outer").addEventListener("dragstart", drag, capture);

document.getElementById("middle").addEventListener("click", click, capture);
document.getElementById("middle").addEventListener("drop", drop, capture);
document.getElementById("middle").addEventListener("dragover", allowDrag, capture);
document.getElementById("middle").addEventListener("dragstart", drag, capture);

document.getElementById("inner").addEventListener("click", click, capture);
document.getElementById("inner").addEventListener("drop", drop, capture);
document.getElementById("inner").addEventListener("dragover", allowDrag, capture);
document.getElementById("inner").addEventListener("dragstart", drag, capture);