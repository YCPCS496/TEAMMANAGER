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
		event.preventDefault();
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
	var teamNameVal = $("#TeamName").val();

	//creates a team
	var param = "http://localhost:8888/teammanager?action=newTeam";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		  TeamName: teamNameVal
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

/*
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
		UserConfirm:teamNameVal
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		alert(data);
		console.log(data);
	  },
	  dataType: 'text'
	});

}
*/


function login(){
	
	var usernameVal = $("#username1").val();
	var password1Val = $("#pword").val();

	var parm = "http://localhost:8888/teammanager?action=login";
	$.ajax({
		  type: "POST",
		  url: parm,
		  data: {
			username: usernameVal,
			password: password1Val
		  },
		  success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			alert(data);
			console.log(data);
		  },
		  dataType: 'text'
		});

}
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
function loginTest(){
	$('body').html(
				'<p>Login with username <input type="text" id="username1"></p>'+
				'<p>Password<input type="password" id="pword"></p>'+
				'<button onclick="validate()">Login</button>'
				);
}

function clearScreen(){
	$('body').html(
		      '<p>Enter Username<input type="text" id="username"></p>'+
		      '<p>Enter Password<input type="password" id="pword1"></p>'+
			  '<p>Re-enter Password<input type="password" id="pword2"></p>'+	
			  '<p>First Name<input type="text" id="firstName"></p>'+
			  '<p>Last Name<input type="text" id="lastName"></p>'+
			  '<p>Enter Email<input type="text" id="email1"></p>'+
			  '<button onclick="validate() ">Create User</button>'+
			  '<button onclick="loginTest() ">Login</button>'
			  );
	
	
	alert("Test");
}


