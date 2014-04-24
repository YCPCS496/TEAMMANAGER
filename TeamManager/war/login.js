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
	alert(emailVal);
	
	if(password1Val == password2Val){
		$.ajax({
		  type: "POST",
		  url: "http://localhost:8080/teammanager?action=newUser",
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
			console.log("textStatus is " + textStatus);
		  },
		  dataType: 'text'
	});
		
	}
	
	
}

/*function createTeam(){
	var usernameVal = $("#username").val();
	
	var password1Val = $("#password1").val();
	var password2Val = $("#password2").val();
	
	// TODO: ensure passwords match

	//creates a team
	var param = "http://localhost:8080/teammanager?action=newTeam&TeamName="+;
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		username: usernameVal,
		password1: password1Val,
		// etc.
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		console.log("textStatus is " + textStatus);
	  },
	  dataType: 'text'
	});

}

function requestJoin(){
	//Sends a request to join a team
}

function confirmJoin(){
	//Confirms a join to a team
}

function getTeam(){
	//Get team
	//get methods
}

function getUser(){
	//Get User
	//get methods
	
}
*/


