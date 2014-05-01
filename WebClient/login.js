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
			firstName: firstNameVal,
			lastName: lastNameVal,
			email1: emailVal
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
function add() {

//Create an input type dynamically.
var element = document.createElement("input");

//Create Labels
var label = document.createElement("label");
label.innerHTML = "New Label";     

//Assign different attributes to the element.
element.setAttribute("type", "text");
element.setAttribute("value", "");
element.setAttribute("name", "Test Name");
element.setAttribute("style", "width:200px");

label.setAttribute("style", "font-weight:normal");

// 'foobar' is the div id, where new fields are to be added
var foo = document.getElementById("body");

//Append the element in page (in span).
alert(element);
foo.appendChild(label);
foo.appendChild(element);
}


