var myPieChart = null;
var myLineChart = null;

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
            type: 'POST',
            url: 'http://localhost:9999/login',
            data: JSON.stringify(loginData),
            contentType: 'application/json',
            success: function(data) {
                alert('Login successful');
                localStorage.setItem("username", loginData.username);
                //alert("NAVIGATING TO HOME PAGE, REMOVE THIS LATER");
                window.location.href = "home.html";
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

        //alert("http://localhost:9999/register");
        //alert(JSON.stringify(registerData));

        $.ajax({
            type: 'POST',
            url: 'http://localhost:9999/register',
            data: JSON.stringify(registerData),
            contentType: 'application/json',
            success: function(data) {
                localStorage.setItem("username", registerData.username);
                alert('Registration successful');
                window.location.href = "home.html";
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

    if (window.location.pathname.endsWith('home.html')){
        // Get username from localStorage
        var username = localStorage.getItem("username");

        //alert(username);

        // Call the getHomeStatData function and handle the result using a Promise
        getHomeStatData()
            .then(emissionsData => {
                // These functions will run only after getHomeStatData() has completed
                updateStatBox("month", emissionsData);
                drawPieChart(emissionsData);
                drawLineChart(emissionsData)
            })
            .catch(error => {
                console.error("Error:", error);
            });

        
        function getHomeStatData() {
            return new Promise(function(resolve, reject) {
                endDate = new Date();
        
                if ($("#monthly-button").hasClass('active')) {
                    startDate = luxon.DateTime.local(endDate.getFullYear(), endDate.getMonth() + 1, 1).toJSDate();
                  } else if ($("#yearly-button").hasClass('active')) {
                    startDate = luxon.DateTime.local(endDate.getFullYear(), 1, 1).toJSDate();
                  }
        
                formattedStartDate = startDate.toISOString().split('T')[0];
                formattedEndDate = endDate.toISOString().split('T')[0];
        
                //alert(JSON.stringify('http://localhost:9999/emissions/' + username + '/' + formattedStartDate + '/' + formattedEndDate));

                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:9999/emissions/' + username + '/' + formattedStartDate + '/' + formattedEndDate,
                    success: function(emissionsData) {
                        // Resolve the promise with the received data
                        resolve(emissionsData);
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
                .then(emissionsData => {
                    // these functions will run only after getHomeStatData() has completed
                    updateStatBox("month", emissionsData);
                    drawPieChart(emissionsData);
                    drawLineChart(emissionsData)
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        });

        $("#yearly-button").click(function() {
            $(this).addClass('active');
            $("#monthly-button").removeClass('active');
        
            getHomeStatData()
                .then(emissionsData => {
                    // These functions will run only after getHomeStatData() has completed
                    updateStatBox("year", emissionsData);
                    drawPieChart(emissionsData);
                    drawLineChart(emissionsData)
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        });
        

        function updateStatBox(period, emissionsData) {

            if(period == "year"){
                $("#home-stat-box-first-line").html(`Your carbon footprint for this year is:`);
                $("#home-stat-box-second-line").html(emissionsData.total);
                $("#home-stat-box-third-line").html('Kg of CO<sub>2</sub>');
                $("#home-stat-box-fourth-line").html(`That's <b>` + emissionsData.total/100 + `</b> plane trip(s) from Paris to New York`);
                $("#home-stat-box-fifth-line").html(`or <b>` + emissionsData.total/10 + `</b> km driven by an average car`);
                $("#home-stat-box-sixth-line").html(`or <b>` + emissionsData.total/5 + `</b> trees worth of carbon capture per year`);
            }else if(period == "month"){
                $("#home-stat-box-first-line").html(`Your carbon footprint for this month is:`);
                $("#home-stat-box-second-line").html(emissionsData.total);
                $("#home-stat-box-third-line").html('Kg of CO<sub>2</sub>');
                $("#home-stat-box-fourth-line").html(`That's <b>` + emissionsData.total/100 + `</b> plane trip(s) from Paris to New York`);
                $("#home-stat-box-fifth-line").html(`or <b>` + emissionsData.total/10 + `</b> km driven by an average car`);
                $("#home-stat-box-sixth-line").html(`or <b>` + emissionsData.total/5 + `</b> trees worth of carbon capture per year`);
            }
        }
    }else if (window.location.pathname.endsWith('profile.html')){

        var username = localStorage.getItem("username");

        function getProfileData() {
            return new Promise(function(resolve, reject) {

                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:9999/profile/' + username,
                    success: function(userData) {
                        // Resolve the promise with the received data
                        resolve(userData);
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

        getProfileData()
            .then(userData => {
                // These functions will run only after getProfileData() has completed

                $(".user-details #name").val(userData.name);
                $(".user-details #username").val(userData.username);
                $(".user-details #email").val(userData.email);
                $(".user-details #dob").val(userData.dob);
                $(".user-details #mobile").val(userData.mobile);
                $(".user-details #address").val(userData.address);
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

});

function drawPieChart(emissionsData) {
    var ctx = document.getElementById('myPieChart').getContext('2d');

    // If myChart is not null, destroy it before drawing a new one
    if(myPieChart != null){
        myPieChart.destroy();
    }

    myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Train', 'Plane', 'Heavy Car', 'Small Car'],
            datasets: [{
                label: 'Kg CO2',
                data: [parseFloat(emissionsData.byTrain), parseFloat(emissionsData.byPlane), parseFloat(emissionsData.byHeavyCar), parseFloat(emissionsData.bySmallCar)],
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

function drawLineChart(emissionsData) {

    timeSeriesData = emissionsData.timeSeriesData;
    timeSeriesLabels = emissionsData.timeSeriesLabels;
    timeSeriesLabels = timeSeriesLabels.map(dateStr => new Date(dateStr));

    var ctx = document.getElementById('myLineChart').getContext('2d');

    // If myLineChart is not null, destroy it before drawing a new one
    if(myLineChart != null){
        myLineChart.destroy();
    }

    myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: timeSeriesLabels, // these should be dates
            datasets: [{
                label: 'Cumulative Kg CO2',
                data: timeSeriesData,
                backgroundColor: 'rgba(75, 192, 192, 0.5)', // for the area under the line
                borderColor: 'rgba(75, 192, 192, 1)', // for the line itself
                fill: true, // fill the area under the line
            }]
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    type: 'time',
                    time: {
                        unit: 'day',
                        displayFormats: {
                            day: 'MMM D'
                        }
                    },
                    title: {
                        display: true,
                        text: 'Date'
                    }
                },
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}
