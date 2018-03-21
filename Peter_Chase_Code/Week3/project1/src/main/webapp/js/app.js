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

		$('#nav-btns').on('click', function (e) {
			if (!$(this).hasClass('active')) {
				$('.nav-item').removeClass('active');
				$(this).addClass('active');
			}
		});
		
		$('#btn-home').on('click', loadHome);
		
		$('#btn-logout').on('click', (e) => {
			$.get('logout', loadLanding);
		});
		
		loadProfile();
	});
}

function loadProfile() {
	$.get('loadhome.view', (response) => {
		$('#view').html(response);
		
		$.get('ses_emp', (response) => {
			const emp = JSON.parse(response);
			const isDepHead = !emp.departmentHead;
			const isSuperVisor = !emp.superVisor;
			
			$('#btn-approvals').on('click', loadApprovals);
			
			if (isDepHead || isSuperVisor) {
				$('#btn-requests').on('click', loadRequests);
			} else {
				$('#btn-requests').on('click', () => {
					alert('Must be department head or supervisor')
				});
			}
			
			
			$('#btn-events').on('click', loadEvents);
			
			$('#nav-title').text("Welcome back " + emp.email);
			$('#info-id').text(emp.id);
			$('#info-email').text(emp.email);
			$('#info-dep').text(emp.department.name);
			
			if (emp.superVisor) {
				$('#info-sup').text(emp.superVisor.email);
			}
			
			$('#info-dep-head').text(isDepHead ? 'Yes' : 'No');
			$('#info-country').text(emp.location.country);
			$('#info-city').text(emp.location.city);
			$('#info-province').text(emp.location.province);
			$('#info-postal').text(emp.location.postalCode);
			$('#info-addr1').text(emp.location.address1);
			$('#info-addr2').text(emp.location.address2);
			$('#info-phone').text(emp.location.phone);
			$('#info-balance').text('$' + emp.avaliableReimbursment.toFixed(2));
		});
	});
}

function loadEvents() {
	$.get('events', (events) => {
		$('#view').html('');
		for (let i in events) {
			const evt = events[i];
			$.get('event.view', (content) => {
				const $content = $(content);
				$content.find('.btn-apply').on('click', (e) => {
					$.post('apply', JSON.stringify(evt), (response) => {
						$content.remove();
					});
				});
				
				$content.find('#evt-title').text(evt.type.name);
				$content.find('.info-cost').text('$' + evt.cost.toFixed(2));
				$content.find('.info-amount').text('$' + (evt.cost * evt.type.coverage).toFixed(2));
				$content.find('.info-grad').text(evt.gradingFormat.name);
				$content.find('.info-type').text(evt.type.name);
				$content.find('.info-desc').text(evt.description);
				$content.find('.info-date').text(evt.eventDate);
				$content.find('.info-start').text(evt.courseDate);
				$content.find('.info-dead').text(evt.submissionDate);
				$content.find('.info-country').text(evt.eventLocation.country);
				$content.find('.info-city').text(evt.eventLocation.city);
				$content.find('.info-province').text(evt.eventLocation.province);
				$content.find('.info-postal').text(evt.eventLocation.postalCode);
				$content.find('.info-addr1').text(evt.eventLocation.address1);
				$content.find('.info-addr2').text(evt.eventLocation.address2);
				$content.find('.info-phone').text(evt.eventLocation.phone);
				$('#view').append($content);
			});
		}
	});
}


function loadApprovals() {	
	$.get('approvals', (response) => {
		const approvals = JSON.parse(response);
		$('#view').html('');
		for (let i in approvals) {
			const apvl = approvals[i];
			$.get('approval.view', (content) => {
				const $content = $(content);
				$content.find('#evt-apvl-title').text(apvl.reimbursment.description);
				$content.find('.info-apvl-email').text(apvl.approver.email);
				$content.find('.info-apvl-dep').text(apvl.approver.department.name);
				$content.find('.info-apvl-req').text(apvl.type.status);
				$('#view').append($content);
			});
		}
	});
}

function loadRequests() {
	$.get('requests', (response) => {
		$('#view').html('');
		const requests = JSON.parse(response);
		for (let i in requests) {
			const req = requests[i];
			$.get('request.view', (content) => {
				const $content = $(content);

				$content.find('.btn-approve').on('click', (e) => {
					$.post('submit_approval', { id: req.id, approved: true }, (response) => {
						$content.find('.info-req-status').text('Accepted');
					});
				});
				
				$content.find('.btn-deny').on('click', (e) => {
					$.post('submit_approval', { id: req.id, approved: false }, (response) => {
						$content.find('.info-req-status').text('Denied');
					});
				});
				
				$content.find('.info-email').text(req.recipient.email);
				$content.find('.info-balance').text(req.recipient.avaliableReimbursment);
				$content.find('.info-dep').text(req.recipient.department.name);
				$content.find('.info-cost').text('$' + req.reimbursment.cost.toFixed(2));
				$content.find('.info-amount').text('$' + (req.reimbursment.cost * req.reimbursment.type.coverage).toFixed(2));
				$content.find('.info-grad').text(req.reimbursment.gradingFormat.name);
				$content.find('.info-type').text(req.reimbursment.type.name);
				$content.find('.info-desc').text(req.reimbursment.description);
				$content.find('.info-date').text(req.reimbursment.eventDate);
				$content.find('.info-start').text(req.reimbursment.courseDate);
				$content.find('.info-dead').text(req.reimbursment.submissionDate);
				$content.find('.info-req-status').text(req.type.status);
				$('#view').append($content);
			});
		}
		
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

	$.post('register', info, (response) => {
		if (response) {
			alert(response);
		} else {			
			alert('Account creation success');
			loadLanding();
		}
	})
	.fail(() => alert('Account create failed'))

}