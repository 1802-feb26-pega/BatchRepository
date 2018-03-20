/** 1. Fibonacci
 *  Define function: fib(n)
 *  Return the nth number in the fibonacci sequence
 */

function fib(n) {
	  var a = 1, b = 0, temp;

	  while (num >= 0){
	    temp = a;
	    a = a + b;
	    b = temp;
	    num--;
	  }

	  return b;
}

function runFib() {
	//alert("HELLO");
	var input = document.getElementById("fib").value;
	var display = document.getElementById("fibDisplay");
	display.innerHTML = fib(input);
}

document.getElementById("doFib").addEventListener("click", runFib);