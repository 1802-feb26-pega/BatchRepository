/**
 * fib
 */
var it = 0;
function fib(n){
	var n1 = 1;
	var n2 = 1;
	var temp;
	for(i = 0; i < n-2; i++){
		temp = n2;
		n2 = n1 + n2;
		n1 = n2;
	}
	return n2;
}

function it(){
	it += 2;
	return it;
}

function runFib(){
	//alert("Hello");
	//var input = document.getElementById("fib").value;
	var display = document.getElementById("fibDisplay");
	display.innerHTML = it();
}

document.getElementById("doFib").addEventListener("mouseover", runFib);



//EVENT PROPAGATION and drag n drop
var capture = true; // makes it start with outer div and go in (capturing). if false it bubbles from inner to outer instead
//e.stopPropagation with function(e)
//document.getElementById("inner").addEventListener("click", function(){
//	alert("INNER");
//}, capture);
//
//var capture = true;
//document.getElementById("middle").addEventListener("click", function(){
//	alert("MIDDLE");
//}, capture);
//
//var capture = true;
//document.getElementById("outer").addEventListener("click", function(){
//	alert("OUTER");
//}, capture);
var counter = 0;

function click(event){
	console.log(event.currentTarget + " " + counter++);
	event.stopPropagation();
}

function allowDrag(){
	console.log(`dragging over $(event.target.id}`);
	event.preventDefualt();
}
function drag(event){
	event.stopPropagation();
	console.log(`starting to drag ${event.target.id}`)
}
function drop(event){
	event.preventDefault();
	var data = event.dataTransfer.getData("text");
	event.target.insertBefore(document.getElementById(data), event.target.firsChild);
}

document.getElementById("outer").addEventListener("click", click, capture);
document.getElementById("outer").addEventListener("drop", drop, capture);
document.getElementById("outer").addEventListener("dragover", allowDrag, caputure);
document.getElementById("outer").addEventListener("dragstart", drag, caputure);

document.getElementById("middle").addEventListener("click", click, capture);
document.getElementById("middle").addEventListener("drop", drop, capture);
document.getElementById("middle").addEventListener("dragover", allowDrag, caputure);
document.getElementById("middle").addEventListener("dragstart", drag, caputure);

document.getElementById("inner").addEventListener("click", click, capture);
document.getElementById("inner").addEventListener("drop", drop, capture);
document.getElementById("inner").addEventListener("dragover", allowDrag, caputure);
document.getElementById("inner").addEventListener("dragstart", drag, caputure);
