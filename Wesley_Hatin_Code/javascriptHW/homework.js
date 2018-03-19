/*
1. Fibonacci
Define function: fib(n) 
Return the nth number in the fibonacci sequence.
*/

function fib(n){
	function fib(n){
		var a = 1;
		var b = 1;
		var c;
		for(i=0; i<n-2; i++){
			c = a + b;
			a = b;
			b = c;
		}
		return b;
	}
}

/*
2. Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array.
Return the sorted array.
*/

function bubbleSort(numArray){
	var noSwaps = false;
	var temp = 0;
	
	while(noSwaps == false){
		for(i=0; i+1<numArray; i++){
			if(numArray[i]>numArray[i+1]){
				temp = numArray[i+1];
				numArray[i+1]=numArray[i];
				numArray[i]=temp;
			}
		}
	}
	return numArray;
}

/*
3. Reverse String
Define function: reverseStr(someStr)
Reverse and return the String.
*/

function reverseStr(someStr){
	var revStr = "";
	var strArray = someStr.split("");
	
	for(i=1; i<=strArray.length; i++){
		revStr=revStr.concat(strArray[someStr.length-i]);
	}
	return revStr.;
}

/*
4. Factorial
Define function: factorial(someNum)
Use recursion to compute and return the factorial of someNum.
*/

function factorial(someNum){
	if(someNum == 0){
		return 1;
	}
	else if(someNum == 1){
		return 1;
	}
	else {
		return factorial(someNum-1)*someNum;
	}
}

/*
5. Substring
Define function substring(someStr, length, offset)
Return the substring contained between offset and (offset + length) inclusively.
If incorrect input is entered, use the alert function and describe why the input was incorrect.
*/

function substring(someStr, length, offset){
	if(length>someStr.length){
		alert.("Requested substring is too long.");
	}
	else if(offset+length>someStr.length){
		alert.("End of requested substring is out of range.");
	}
	else if(offset<0 || offset>someStr.length){
		alert.("Requested offset out of range.");
	}
	else {
		return someStr.substr(offset, length);
	}
	return null;
}

/*
6. Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/

function isEven(someNum){
	var divNum = someNum/2;
	
	if(someNum>=0){
		for(i=0; i<someNum; i++){
			if(i==divNum){
				return true;
			}
		}
	}
	else{
		for(i=0; i<someNum; i--){
			if(i==divNum){
				return true;
			}
		}
	}
	return false;
}

/*
7. Palindrome
Define function isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false
*/

function isPalindrome(someStr){
	var front = someStr.substring(0,substring.length/2).split("");
	var back = someStr.substring(substring.length/2).split("");
	
	for(i=0; i<front.length; i++){
		if(front[i]!=back[back.length-i]){
			return false;
		}
	}
	return true;
}

/*
8. Shapes
Define function: printShape(shape, height, character)
shape is a String and is either "Square", "Triangle", "Diamond".
height is a Number and is the height of the shape. Assume the number is odd.
character is a String that represents the contents of the shape. Assume this String contains just one character.
Use a switch statement to determine which shape was passed in.
Use the console.log function to print the desired shape.
Example for printShape("Square", 3, "%");
%%%
%%%
%%%
Example for printShape("Triangle", 3, "$");
$
$$
$$$
Example for printShape("Diamond", 5, "*");
  *
 ***
*****
 ***
  *
*/

function printShape(shape, height, character){
	var output = "";
	
	for(i=0; i<height; i++){
		output = "";
		for(j=0; j<height; j++){
			switch(shape){
			case 'Square':
				output = output.concat(character);
				break;
			case 'Triangle':
				if(j<=i){
					output = output.concat(character);
				}
				break;
			case 'Diamond':
				
			}
		}
		console.log(output);
	}
}

/*
9. Object Literal
Define function traverseObject(someObj)
Print every property and its value.
*/

function traverseObject(someObj){
	for(property in someObject){
		console.log(property + ", " + someObject[property]);
	}
}

/*
10. Delete Element
Define function deleteElement(someArr)
Print length
Delete the third element in the array.
Print length
The lengths should be the same.
*/

function deleteElement(someArr){
	console.log(someArr.length);
	delete someArr[2];
	console.log(someArr.length);
}

/*
11. Splice Element
Define function spliceElement(someArr)
Print length
Splice the third element in the array.
Print length
The lengths should be one less than the original length.
*/

function spliceElement(someArr){
	console.log(someArr.length);
	someArr.splice(0,1);
	console.log(someArr.length);
}

/*
12. Defining an object using a constructor
Define a function Person(name, age)
The following line should set a Person object to the variable john:
	var john = new Person("John", 30);
*/

function Person(name, age){
	var person = new Object();
	person.name = name;
	person.age = age;
	return person;
}

/*
13. Defining an object using an object literal
Define function getPerson(name, age)
The following line should set a Person object to the variable john:
	var john = getPerson("John", 30);
 */

function getPerson(name, age){
	var person = {name:name, age:age};
	return person;
}