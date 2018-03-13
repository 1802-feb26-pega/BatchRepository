// Intro to DOM Manipulation
/*1. Fibonacci
Define function: fib(n)
Return the nth number in the fibonacci sequence.*/

function fib(n)
{
	// functionality
	// Basic Equation Fn = F(n-1) + F(n-2)
	
	var a = 1;
	var b = 1;
	var c;
	
	for (var x = 0; x < n - 2; x++)
	{
		c = a + b;
		a = b
		b = c;
	}
	
	return b;
}

function runFib()
{
	//alert("hello");
	//prompt("testing", "number");
	
	var input = document.getElementById("fib").value;
	var display = document.getElementById("fibDisplay");
	display.innerHTML = fib(input);
}

function changeColor()
{
	  var letters = '0123456789ABCDEF';
	  var color = '#';
	  for (var x = 0; x < 6; x++ )
	  {
	    color = color + letters[Math.floor(Math.random() * 16)];
	  }
	  
		document.getElementById("fibAnswer").style.color = color;
		document.getElementById("text").innerHTML = display;
}

document.getElementById("doFib").addEventListener("click", runFib);
document.getElementById("doFib").addEventListener("mouseover", changeColor);

// Event Propagation and Drag/Drop
var capture = true;  // false = bubbling
/*document.getElementById("inner").addEventListener("click", function()
{
	// e.stopPropagation();
	alert("INNER");
}, capture);

document.getElementById("middle").addEventListener("click", function()
{
	alert("MIDDLE");
}, capture);

document.getElementById("outer").addEventListener("click", function()
{
	alert("OUTER");
}, capture);*/

function click(event)
{
	console.log(event.currentTarget);
	event.stopPropagation();
}

function allowDrag(event)
{
	console.log(`dragging over: ${event.target.id} `);
	event.preventDefault();
}

function drag(event)
{
	event.stopPropagation();
	console.log(`starting to drag element: ${event.target.id} `);
}

function drop(event)
{
	event.preventDefault();
	var data = event.dataTransfer.getData("text");
	event.target.insertBefore(document.getElementById(data), event.target.firstChild);
}

document.getElementById("1").addEventListener("click", click, capture);
document.getElementById("1").addEventListener("drag", drop, capture);
document.getElementById("1").addEventListener("dragover", allowDrag, capture);
document.getElementById("1").addEventListener("dragstart", drag, capture);

document.getElementById("2").addEventListener("click", click, capture);
document.getElementById("2").addEventListener("drag", drop, capture);
document.getElementById("2").addEventListener("dragover", allowDrag, capture);
document.getElementById("2").addEventListener("dragstart", drag, capture);

document.getElementById("3").addEventListener("click", click, capture);
document.getElementById("3").addEventListener("drag", drop, capture);
document.getElementById("3").addEventListener("dragover", allowDrag, capture);
document.getElementById("3").addEventListener("dragstart", drag, capture);






