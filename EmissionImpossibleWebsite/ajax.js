var myChart = null;

$(document).ready(function(){

    // Login
    $("#loginForm").on("submit", function(event){
        event.preventDefault();

        var loginData = {
            username: $("#loginUsername").val(),
            password: $("#loginPassword").val(),
        };

        //alert("SAVING USERNAME TO SESSIONSTORAGE WITHOUT CHECKING IF VALID USER, IMPLEMENT LATER");
        //localStorage.setItem("username", loginData.username);


        //alert("http://localhost:9999/login");

        //alert(JSON.stringify(loginData));

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:9999/login',
            data: JSON.stringify(loginData),
            contentType: 'application/json',
            success: function(data) {
                alert('Login successful');
                localStorage.setItem("username", loginData.username);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.responseJSON) {
                    alert('Error: ' + jqXHR.responseJSON.message);
                } else {
                    alert('An unknown error occurred.');
                }
            }
        })

        //alert("NAVIGATING TO HOME PAGE, REMOVE THIS LATER");
        window.location.href = "home.html";

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

        //alert("http://localhost:9999/register");
        //alert(JSON.stringify(registerData));

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:9999/register',
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

        window.location.href = "home.html";
    });

    if (window.location.pathname.endsWith('home.html')){
        // Get username from localStorage
        var username = localStorage.getItem("username");

        // Call the getHomeStatData function and handle the result using a Promise
        getHomeStatData()
            .then(emissionsDataArray => {
                // These functions will run only after getHomeStatData() has completed
                updateStatBox("month", emissionsDataArray);
                drawPieChart(emissionsDataArray);
            })
            .catch(error => {
                console.error("Error:", error);
            });

        function getHomeStatData() {
            return new Promise(function(resolve, reject) {
                endDate = new Date();
        
                if ($("#monthly-button").hasClass('active')){
                    startDate = new Date(Date.UTC(endDate.getUTCFullYear(), endDate.getUTCMonth(), 1));
                } else if ($("#yearly-button").hasClass('active')){
                    startDate = new Date(Date.UTC(endDate.getUTCFullYear(), 0, 1));
                }
        
                formattedStartDate = startDate.toISOString().split('T')[0];
                formattedEndDate = endDate.toISOString().split('T')[0];
        
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:9999/emissions/' + username + '/' + formattedStartDate + '/' + formattedEndDate,
                    success: function(emissionsDataArray) {
                        // Resolve the promise with the received data
                        resolve(emissionsDataArray);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.responseJSON) {
                            alert('Error: ' + jqXHR.responseJSON.message);
                        } else {
                            alert('An unknown error occurred.');
                        }
                        // Reject the promise on error
                        reject(errorThrown);
                    }
                });
            });
        }
        

        $("#monthly-button").click(function() {
            $(this).addClass('active');
            $("#yearly-button").removeClass('active');
        
            getHomeStatData()
                .then(emissionsDataArray => {
                    // these functions will run only after getHomeStatData() has completed
                    updateStatBox("month", emissionsDataArray);
                    drawPieChart(emissionsDataArray);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        });

        $("#yearly-button").click(function() {
            $(this).addClass('active');
            $("#monthly-button").removeClass('active');
        
            getHomeStatData()
                .then(emissionsDataArray => {
                    // These functions will run only after getHomeStatData() has completed
                    updateStatBox("year", emissionsDataArray);
                    drawPieChart(emissionsDataArray);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        });
        

        function updateStatBox(period, emissionsDataArray) {

            if(period == "year"){
                $("#home-stat-box-first-line").text(`Your carbon footprint for this year is:`);
                $("#home-stat-box-second-line").html(emissionsDataArray.total + ' Kg of CO<sub>2</sub>');
                $("#home-stat-box-third-line").text(`That's 10 plane trip(s) from x to y`);
                $("#home-stat-box-fourth-line").text(`or 600 km driven by an average car`);
                $("#home-stat-box-fifth-line").text(`or 60 trees worth of carbon capture per year`);
            }else if(period == "month"){
                $("#home-stat-box-first-line").text(`Your carbon footprint for this month is:`);
                $("#home-stat-box-second-line").html(emissionsDataArray.total + ' Kg of CO<sub>2</sub>');
                $("#home-stat-box-third-line").text(`That's 1 plane trip(s) from x to y`);
                $("#home-stat-box-fourth-line").text(`or 60 km driven by an average car`);
                $("#home-stat-box-fifth-line").text(`or 6 trees worth of carbon capture per year`);
            }
        }
    }

});

function drawPieChart(emissionsDataArray) {
    var ctx = document.getElementById('myChart').getContext('2d');

    // If myChart is not null, destroy it before drawing a new one
    if(myChart != null){
        myChart.destroy();
    }

    myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Car', 'Bus', 'Train', 'Plane'],
            datasets: [{
                label: 'Kg CO2',
                data: [emissionsDataArray.byCar, emissionsDataArray.byBus, emissionsDataArray.byTrain, emissionsDataArray.byPlane],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(153, 102, 255, 0.5)',
                    'rgba(255, 159, 64, 0.5)'
                ],
                hoverOffset: 4
            }]
        },
        options: {
            responsive: true,
        }
    });
}
