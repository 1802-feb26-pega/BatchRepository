/**
 * 
 */



function fib(n) {
	
	if (n % 2 == 0) {
		return (n+1) * (n/2);
	} else {
		return ((n/2) + 0.5 +((n+1) * (0.5 + (n/2))))
	}
	
	for (var i = 0; i < n; i++) {
		
	}
	
	return 0;
}