window.onload = function(){ //1 
	loadLanding();
}

function loadLanding(){ //2 
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadlanding.view" , true);
	xhr.send();

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$("#view").html(xhr.responseText);
			$('#login').on('click', login); 
			$('#pass').keydown(function(event){
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  login();
			}); //submit upon pressing enter from password input
			$('#register').on('click', loadRegister);
			// after text is loaded, add event listeners/functionality to view
		}
	}
}


function login(){console.log("clicked login");}
function loadRegister(){console.log("clicked register");}