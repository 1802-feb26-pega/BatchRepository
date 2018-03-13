/**
 * 
 */

var func = function(){
	test = 0; //hoisted to global scope
	return test;
}

function getValue(condition){
	if(condition){
		var x = 0; //implicitly declared as function scope. also hoisting
		return value;
	}
	else{
		return null;
	}
}