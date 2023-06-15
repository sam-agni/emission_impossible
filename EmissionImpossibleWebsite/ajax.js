$(document).ready(function(){

    // Login
    $("#loginForm").on("submit", function(event){
        event.preventDefault();

        var loginData = {
            email: $("#email").val(),
            password: $("#password").val(),
        };

        alert(JSON.stringify(loginData));
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:7777/login',
            data: JSON.stringify(loginData),
            contentType: 'application/json',
            success: function(data) {
                alert('Login successful');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.responseJSON) {
                    alert('Error: ' + jqXHR.responseJSON.message);
                } else {
                    alert('An unknown error occurred.');
                }
            }
        })
    });

    // Create new account
    $(document).on('click', '.create-account-button', function(){
        
        event.preventDefault();

        if($("#password").val() != $("#confirmPassword").val()){
            alert("Passwords do not match!")
            return
        }
        
        var registerData = {
            name: $("#name").val(),
            username: $("#username").val(),
            email: $("#email").val(),
            dob: $("#dob").val(),
            mobile: $("#mobile").val(),
            address: $("#address").val(),
            password: $("#password").val()
        };

        alert(JSON.stringify(registerData));
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:7777/register',
            data: JSON.stringify(registerData),
            contentType: 'application/json',
            success: function(data) {
                alert('Registration successful');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.responseJSON) {
                    alert('Error: ' + jqXHR.responseJSON.message);
                } else {
                    alert('An unknown error occurred.');
                }
            }
        })
    });
});