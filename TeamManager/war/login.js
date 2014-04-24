var count = 3;

//simple function to validate information
function validate(objForm) {
var usernameVal = $("#username").val();
	
var password1Val = $("#pword1").val();
var password2Val = $("#pword2").val();

var firstNameVal = $("#firstName").val();
var lastNameVal = $("#lastName").val();
var emailVal = $("#email1").val();
	
	if(usernameVal == ""){
		alert("Please provide a username");
	}
	
	//checking to make sure both passwords are correct
	if(password1Val != password2Val){
		alert("Passoword does not match");
	}
	
	//checking the value of email
	//alert(emailVal);
	
	if(password1Val == password2Val){
		$.ajax({
		  type: "POST",
		  url: "http://localhost:8888/teammanager?action=newUser",
		  data: {
			username: usernameVal,
			password1: password1Val,
			password2: password2Val,
			firstname: firstNameVal,
			lastname: lastNameVal,
			email: emailVal
		  },
		  success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			alert(data);
			console.log(data);
		  },
		  dataType: 'text'
	});
		
		alert("It worked!")
	}
	
	
}

function createTeam(){
	var teamNameVal = $("#teamName").val();

	//creates a team
	var param = "http://localhost:8888/teammanager?action=newTeam";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		teamName: teamNameVal
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		alert(data);
		console.log(data);
	  },
	  dataType: 'text'
	});

}

function requestJoin(){
	//Sends a request to join a team
	var teamNameVal = $("#teamName").val();

	//creates a team
	var param = "http://localhost:8888/teammanager?action=requestJoin";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		TeamID: teamNameVal
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		alert(data);
		console.log(data);
	  },
	  dataType: 'text'
	});

}



function confirmJoin(){
	//Confirms a join to a team
	var teamNameVal = $("#teamName").val();

	//creates a team
	var param = "http://localhost:8888/teammanager?action=confirmJoin";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		TeamID: teamNameVal
		UserConfirm:
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		alert(data);
		console.log(data);
	  },
	  dataType: 'text'
	});

}
	
}


function login(){
	
	var usernameVal = $("#username").val();
	var password1Val = $("#pword1").val()

	 $.ajax({
            type: "POST",
            url: 'admin/login.php',
            data: {
                username: usernameVal
                password: password1Val
            },
            success: function(data)
            {
                if (data === 'Correct') {
                    window.location.replace('admin/admin.php');
                }
                else {
                    alert(data);
                }
            }
        });

    });

});
	

/*
function getTeam(){
	//Get team
	//get methods
}

function getUser(){
	//Get User
	//get methods
	
}
*/


