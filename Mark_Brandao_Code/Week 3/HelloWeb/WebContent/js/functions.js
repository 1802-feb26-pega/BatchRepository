/*1. Fibonacci
 * Define function: fib(n)
 * return the nth number in the fibonacci sequence.
 */
 
function fib(n){
	if(n <= 1){
		return 1;
	} else {
		return fib(n-1) + fib(n-2);
	}
}

for(var i = 1; i < 25; i++){
	console.log(fib(i));
}

// INTO TO DOM MANIPULATION


document.getElementById("fib").addEventListener("keydown", function(e){
    if (e.keyCode === 13) {
        document.getElementById("fibDisplay").textContent = fib(document.getElementById("fib").value);
    }
});

document.getElementById("doFib").addEventListener("click", function(e){
    document.getElementById("fibDisplay").textContent = fib(document.getElementById("fib").value);
})


// EVENT PROPAGATION
var capture = true;

// document.getElementById("outer").addEventListener("click", function(e){
// 	// e.stopPropagation();
// 	alert("OUTER!");
// }, !capture);
// document.getElementById("middle").addEventListener("click", function(e){
// 	// e.stopPropagation();
// 	alert("MIDDLE!");
// }, capture);
// document.getElementById("inner").addEventListener("click", function(e){
// 	// e.stopPropagation();
// 	alert("INNER!");
// }, !capture);



//
var counter = 0;
function click(e){
	console.log(e.currentTarget + " " + counter++);
	e.stopPropagation();
}

function allowDrag(e){
	console.log(`Dragging over: ${e.target.id} `);
	e.preventDefault();
}

function drag(e){
	e.stopPropagation();
	console.log(`Start to drag element ${e.target.id}`);
}

function drop(e){
	e.preventDefault();
	var data = e.dataTransfer.getData("text");
	e.target.insertBefore(document.getElementById(data), e.target.firstChild);
}

document.getElementById("1").addEventListener("click", click, capture);
document.getElementById("1").addEventListener("drop", drop, capture);
document.getElementById("1").addEventListener("dragover", allowDrag, capture);
document.getElementById("1").addEventListener("dragstart", drag, capture);

document.getElementById("2").addEventListener("click", click, capture);
document.getElementById("2").addEventListener("drop", drop, capture);
document.getElementById("2").addEventListener("dragover", allowDrag, capture);
document.getElementById("2").addEventListener("dragstart", drag, capture);

document.getElementById("3").addEventListener("click", click, capture);
document.getElementById("3").addEventListener("drop", drop, capture);
document.getElementById("3").addEventListener("dragover", allowDrag, capture);
document.getElementById("3").addEventListener("dragstart", drag, capture);












