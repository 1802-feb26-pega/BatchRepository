//INTRO TO DOM MANIPULATION
/*1. Fibonacci
Define function: fib(n) 
Return the nth number in the fibonacci sequence.*/

function fib(n){
	var a = 1;
	var b = 1;
	var c;
	for (i = 0; i < n-2;i++){
		c = a + b;
		a = b;
		b = c;
	}
	return b;
}

function fibRec(n){
	if(n<=1) return n;
	else return (fib(n-1) + fib(n-2));
}

function runFib(){
	//alert("HELLO");
	//console.log(prompt("testing", "number"));
	var input = document.getElementById("fib").value;
	var display = document.getElementById("fibDisplay");
	display.innerHTML = fib(input);

}

document.getElementById("doFib").addEventListener("click", runFib);


//EVENT PROPAGATION AND DRAG DROP
var capture = true; // false = bubbling

/*
document.getElementById("inner").addEventListener("click", function(e) {
	//e.stopPropagation();
	alert("INNER!");
}, capture);

document.getElementById("middle").addEventListener("click", function() {
	alert("MIDDLE");
}, capture);

document.getElementById("outer").addEventListener("click", function() {
	alert("OUTER");
}, capture);
 */
var counter = 0;

function click(event){
	console.log(event.currentTarget + " " + counter++);
	event.stopPropagation();
}

function allowDrag(event){
	console.log(`dragging over: ${event.target.id}`);
	event.preventDefault();
}

function drag(event){
	event.stopPropagation();
	console.log("starting to drag element: "+event.target.id);
	event.dataTransfer.setData("text", event.target.id);
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


