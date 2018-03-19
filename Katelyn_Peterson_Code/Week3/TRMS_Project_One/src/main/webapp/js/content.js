//http://localhost:8099/TRMS_Project_One/index.html

// Create our number formatter.
var formatter = new Intl.NumberFormat('en-US',
{
	style: 'currency',
	currency: 'USD',
	minimumFractionDigits: 2,
	// the default value for minimumFractionDigits depends on the currency
	// and is usually already 2
});

// Window
window.onload = function()
{
	loadLogin();
}

// Login - Loading and Actually Logging In
function loadLogin()
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadLogin.view" , true);
	xhr.send();

	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			$('#view').html(xhr.responseText);

			// Login
			$('#login').on('click', logIn);

			 //submit upon pressing enter from password input
			$('#password').keydown(function(event)
			{
				var keypressed = event.keyCode || event.which;
				if(keypressed == 13)  logIn();
			});

			// Register
		}
	}
}

function logIn()
{
	$('#message').hide();
	var email = $('#email').val();
	var password = $('#password').val();

	//console.log(email);
	//console.log(password);

	// Email Validation?

	var toSend = [email, password];
	//console.log("toSend initialized");

	var xhr = new XMLHttpRequest();
	//console.log("xhr set to XMLHttpRequest");

	xhr.open("POST", "login", true);
	//console.log("xhr open");

	var json = JSON.stringify(toSend);
	//console.log(`HELOOOO ${json}`);

	xhr.send(json);
	//console.log("xhr send");
	//console.log(xhr.readyState);
	//console.log(xhr.status);

	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			//console.log(xhr.responseText);

			// Need Employee to get passed from code to code, also...need to eliminate the uncaught referenced error on events/grades
			var employee = JSON.parse(xhr.responseText);
			var message = "";
			//console.log(employee);
			if(employee == null)
			{
				$('#message').show();
				$('#message').attr("style", "display:inline");
				message = "You have entered the wrong email and/or password. Please try again";
				//console.log("User is Null");
				$('#message').html(message);
			}
			else
			{
				//alert("success");
				loadNav(employee);
				loadHome(employee);
			}
		}
	}
}

// Loading Navigation and links to other areas
function loadNav(employee)
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadNav.view" , true);
	xhr.send();

	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			$('#navbar').html(xhr.responseText);
			$('#home').click(function()
			{
				loadHome(employee);
			});

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			// Change new Reimbursement to have employee
			$('#newReimburse').click(function()
			{
				loadNew(employee);
			});
			$('#logout').click(logout);
		}
	}
}

// Loading Home page
function loadHome(employee)
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadHome.view" , true);
	xhr.send();

	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			$('#view').html(xhr.responseText);
			$('#name').html(employee.firstName);

			// ADD LISTENERS TO NAV BAR TO GO TO VARIOUS VIEWS AND LOGOUT
			getEmployeeForms();
			$('#formTable').hide();
		}
	}
}

// Getting Form data from database
function getEmployeeForms()
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "forms" , true);
	xhr.send();

	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			var forms = JSON.parse(xhr.responseText);

			if(forms.length == 0)
			{
				console.log("you have no reimbursement requests");
			}
			else
			{
				//console.log("testing----");
				//	accounts = JSON.stringify("data") + ":" + accounts;
				//console.log(forms);

				// If time, change table to show employee last name...this is so superiors and BenCo can distiguish between requests
				// If so, may need to expand wrapper again
				var data = formatTable(forms);

				$('#formTable').DataTable
				({
					destroy: true,
					data : data,
					columns: [
						{data : "Form ID" },
						{data : "Date Submitted" },
						{data : "Start Date" },
						{data : "Location" },
						{data : "Training Cost" },
						{data : "Grading Format" },
						{data : "Supervisor Approval" },
						{data : "Department Approval" },
						{data : "BenCo Approval" },
						{data : "Grade Earned" },
						{data : "Reimbursement Awarded" }
						]
				});
				//console.log("this should have worked");
				$('#formTable').show();
			}
		}
	}
}

// Formatting table for viewing
function formatTable(forms)
{
	//console.log("formatting table");
	var data = [];
	for(let x = 0; x < forms.length; x++)
	{
		let temp = new Object();
		//console.log("Incoming Data: " + forms[x]);
		temp["Form ID"] = forms[x].formId;
		temp["Date Submitted"] = forms[x].submitDate;
		temp["Start Date"] = forms[x].startDate;
		temp.Location = forms[x].address + "<br />" + forms[x].city + ", " + 
			forms[x].state + " " + forms[x].postal;
		temp["Training Cost"] = formatter.format(forms[x].cost);
		temp["Grading Format"] = forms[x].gradeFormat;
		temp["Supervisor Approval"] = boolToHuman(forms[x].dsApproval);
		temp["Department Approval"] = boolToHuman(forms[x].dhApproval);
		temp["BenCo Approval"] = boolToHuman(forms[x].benCoApproval);
		temp["Grade Earned"] = forms[x].grade;
		temp["Reimbursement Awarded"] = formatter.format(forms[x].award);
		//console.log("Outgoing Data" + temp);
		data.push(temp);
	}
	console.log(data);
	return data;
}

// Logging out
function logout()
{
	console.log("logging out");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "logout" , true);
	xhr.send();
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			console.log("attempting to redirect");
			window.location.replace("index.html");
		}

	}

}

// Translating Java Booleans into Human Readable
function boolToHuman(boolean)
{
	if(boolean == true)
	{
		return 'Yes'
	}
	else
	{
		return 'No'
	}
}

// New Reimbursement Request
function loadNew(employee)
{
	//console.log("In Load New!");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "loadNew.view" , true);
	xhr.send();

	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			$('#view').html(xhr.responseText);

			// Set up for getting event date
			//$('#datepicker').datepicker();

			// Populate Event and Grade DropDown
			getEvents();
			getGrades();

			// Set up validation
			//$('#username').blur(validate);

			// Hide all the messages
			$('#eDateMessage').hide();
			$('#eAddressMessage').hide();
			$('#eCityMessage').hide();
			$('#eZipMessage').hide();
			$('#eCostMessage').hide();

			$('#request').click(function()
			{
				nRequest(employee);
			});
		}
	}
}

function nRequest(employee)
{
	console.log("Generating New Request");

	// Get values from employee and form
	// Form ID generated by Database
	// Employee ID from employee
	var startDate = $('#e_date').val();
	var address = $('#e_address').val();
	var city = $('#e_city').val();
	var state = $('#e_state').val();
	var postal = $('#e_zip').val();
	var cost = $('#e_cost').val();
	var event = $('#e_type').val();
	var grade = $('#grade_type').val();

	// End of values retrieved from Form...database uses defaults for the other values

	var toSend = [employee.employeeId, startDate, address, city,];

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "newRequest", true);
	xhr.send(JSON.stringify(toSend));

	// Once Request is Submitted
	loadHome(employee);
}

// Change to validate Reimbursement Request Form
function validate()
{
	//console.log($('#username').val());

	// Need validation for date, address, city, zip, and cost
	var e_date = $('#e_date').val();
	var e_address = $('#e_address').val();
	var e_city = $('#e_city').val();
	var e_zip = $('#e_zip').val();
	var e_cost = $('#e_cost').val();

	if(!e_date.value)
	{
		$('#eDateMessage').html('Please fill in an event date');
		$('#eDateMessage').show();
		$('#request').attr('disabled', true);
	}
	else if (!e_address.value)
	{
		$('#eAddressMessage').html('Please fill in an event address');
		$('#eAddressMessage').show();
		$('#request').attr('disabled', true);
	}
	else if (!e_city.value)
	{
		$('#eCityMessage').html('Please fill in an event city');
		$('#eCityMessage').show();
		$('#request').attr('disabled', true);
	}
	// zip code also needs 5 number validation
	else if (!e_zip.value)
	{
		$('#eZipMessage').html('Please fill in an event zip code');
		$('#eZipMessage').show();
		$('#request').attr('disabled', true);
	}
	else if (!e_cost.value)
	{
		$('#eCostMessage').html('Please fill in an event cost');
		$('#eCostMessage').show();
		$('#request').attr('disabled', true);
	}
	else
	{
		$('#eDateMessage').hide();
		$('#eAddressMessage').hide();
		$('#eCityMessage').hide();
		$('#eZipMessage').hide();
		$('#eCostMessage').hide();

		$('#request').attr('disabled', false);
	}

}

function getEvents()
{
		//console.log("In Load New!");
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "events" , true);
		xhr.send();
	
		xhr.onreadystatechange = function()
		{
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				let events = JSON.parse(xhr.responseText);
				
				var items = ""

				for(let x = 0; x < events.length; x++)
				{
					items = items + "<option value = " + events[x] + ">" + events[x] + "</option>";
				}

				//$.each(events, function() 
				//{
					//console.log("This Id " + this.id);
					//console.log("This Text " + this.Text);
					//console.log("This value: " + this
					//items = items + "<option value = " + this.val() + ">" + this + "</option>";
				//});
				$('#e_type').html(items);
			}
		}
}

function getGrades()
{
		//console.log("In Load New!");
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "grades" , true);
		xhr.send();
	
		xhr.onreadystatechange = function()
		{
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				let grades = JSON.parse(xhr.responseText);
				
				var items = ""

				for(let x = 0; x < grades.length; x++)
				{
					if(grades[x] == 'Pass/Fail')
					{
						items = items + "<option value = " + grades[x] + " selected = \"selected\">" + grades[x] + "</option>";
					}
					else
					{
						items = items + "<option value = " + grades[x] + ">" + grades[x] + "</option>";
					}
				}

				$('#grade_type').html(items);
			}
		}
}