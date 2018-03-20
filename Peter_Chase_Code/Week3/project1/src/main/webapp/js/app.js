$(document).ready(loadLanding);
function loadLanding() {
	$.get('loadlanding.view', (response) => {
		$('#navbar').html('');
		$('#view').html(response);
		$('#login').on('click', login);

		$('#pass').on('keypress', (e) => {
			const code = e.keyCode || e.which;
			if (code == 13) {
				login(e);
			}
		});
		$('#go-to-register').on('click', loadRegister);
	});
}

function login(e) {
	e.preventDefault();
	const email = $('#email').val();
	const pwd = $('#pass').val();

	const toSend = {
		email ,
		pwd
	};

	$.post('login', toSend)
		.done(loadHome)
		.fail(() => alert("Wrong username/password"));

}

function loadHome() {
	$.get('loadnav.view', (response) => {
		$('#navbar').html(response);

		$('#btn-logout').on('click', (e) => {
			$.get('logout', loadLanding);
		});
	});

	$.get('loadhome.view', (response) => {
		$('#view').html(response);
	});

	$.get('ses_emp', (response) => {
		const emp = JSON.parse(response);
		console.log(emp);
		$('#nav-title').text("Welcome back " + emp.email);
		$('#info-id').text(emp.id);
		$('#info-email').text(emp.email);
		$('#info-dep').text(emp.department.name);
		$('#info-sup').text(emp.superVisor.email);
		$('#info-dep-head').text(emp.departmentHead ? 'No' : 'Yes');
		$('#info-country').text(emp.location.country);
		$('#info-city').text(emp.location.city);
		$('#info-province').text(emp.location.province);
		$('#info-postal').text(emp.location.postalCode);
		$('#info-addr1').text(emp.location.address1);
		$('#info-addr2').text(emp.location.address2);
		$('#info-phone').text(emp.location.phone);
		$('#info-balance').text('$' + emp.avaliableReimbursment.toFixed(2));
	});
}

function loadRegister() {
	$.get('loadregister.view', (response) => {
		$('#view').html(response);

		$.get('departments', (deps) => {
			for (let i in deps) {
				const $dep = $('<div class="dropdown-item"></div>');
				$dep.attr('depId', deps[i].id);
				$dep.text(deps[i].name);
				$('#depmenu').append($dep);
			}
		});

		$.get('supervisors', (sups) => {
			for (let i in sups) {
				const $sup = $('<div class="dropdown-item"></div>');
				$sup.attr('supId', sups[i].id);
				$sup.text(sups[i].email);
				$('#supmenu').append($sup);
			}
		});


		$('#go-to-login').on('click', loadLanding);

		$('#depmenu').on('click', (e) => {
			const $selected = $(e.target);
			$('#dep-text').val($selected.text());
			$('#dep-text').attr('depId', $selected.attr('depId'));
		});

		$('#supmenu').on('click', (e) => {
			const $selected = $(e.target);
			$('#sup-text').val($selected.text());
			$('#sup-text').attr('supId', $selected.attr('supId'));
		});

		$('#register').on('click', (e) => {
			registerUser();
		});
	});
}

function registerUser() {
	const info = {
		email : $('#email-field').val(),
		pass : $('#pass-field').val(),
		passConfirm : $('#pass-confirm').val(),
		depId : $('#dep-text').attr('depId'),
		supId : $('#sup-text').attr('supId'),
		country : $('#country-field').val(),
		city : $('#city-field').val(),
		province : $('#province-field').val(),
		address1 : $('#addr1-field').val(),
		address2 : $('#addr2-field').val(),
		phone : $('#phone-field').val(),
		postal : $('#postal-field').val()
	};

	let msg = '';
	if (info.pass !== info.passConfirm) {
		msg += "Passwords don't match\n";
	} else if (!info.pass) {
		msg += "Password cannot be empty\n";
	}
	if (!info.depId) {
		msg += "Department cannot be empty\n";
	}
	if (!info.supId) {
		msg += "Supervisor cannot be empty\n";
	}
	if (!info.country) {
		msg += "Country cannot be empty\n";
	}
	if (!info.city) {
		msg += "City cannot be empty\n";
	}
	if (!info.province) {
		msg += "Province cannot be empty\n";
	}
	if (!info.address1) {
		msg += "Address cannot be empty\n";
	}

	if (msg) {
		alert(msg);
		return;
	}

	$.post('register', info)
		.done(() => alert('Account creation success'))
		.fail(() => alert('Account create failed'))
		.always(loadLanding);

}