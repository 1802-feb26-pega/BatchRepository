$(document).ready(loadLanding);
function loadLanding() {
    $.get('loadlanding.view', (response) => {
        $('#view').html(response);
        $('#login').on('click', login); 
        $('#pass').keydown(function(event){
            const keypressed = event.keyCode || event.which;
            if(keypressed == 13) {
                login();
            }
        });
        $('#register').on('click', loadRegister);
    });
}

function login() {
    const email = $('#email').val();
    const pwd = $('#pass').val();
    console.log("SHIT SENDING");

    const toSend = [email, pwd];

    $.post('login', toSend, (response) => {

    });
}

function loadRegister() {

}

