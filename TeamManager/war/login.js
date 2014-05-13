var count = 3;

//simple function to validate information
function validate(objForm) {
var usernameVal = $("#username").val();
	
var password1Val = $("#pword1").val();
var password2Val = $("#pword2").val();

var firstNameVal = $("#firstName").val();
var lastNameVal = $("#lastName").val();
var emailVal = $("#email1").val();
	
	if((usernameVal||password1Val) == ""){
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
		
		alert("Validate Worked")
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

//TODO:needs fixing (data)
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
		TeamID: teamNameVal,
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		alert(data);
		console.log(data);
	  },
	  dataType: 'text'
	});

}



function login(){
	//checks the database to see if the user is there and logs the user in 
	var usernameVal = $("#username1").val();
	var password1Val = $("#pword").val();

	if ((usernameVal|| password1Val)== ""){
		event.preventDefault();
	}
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

function getTeamList(){
	
	$.ajax({
	      type: "GET",
		  url: "http://localhost:8080/teammanager/teams/",
		  success: function(data){
		  // do something to let the user know what happened
			 alert(data);
		},
		  dataType: 'text'
		});
	
}

//TODO: finsh
function getTeam(TeamName){
	//Get team
	var teamdata=TeamName;
	
	//get methods
		$.ajax({
	      type: "GET",
		  url: "http://localhost:8080/teammanager/teams/"+teamdata,
		  data: teamdata,
		  success: function(data, textStatus, jqXHR) {
		  // do something to let the user know what happened
		  alert(data);
		  console.log(data);
		},
		  dataType: 'text'
		});
}
/*
function getUser(){
	//Get User
	//get methods
	
}
*/

function createTeamView(){
	$('body').html(
			'<p>Team Name<input type="text" id="TeamName"></p>'+
			'<button onclick="createTeam()">Create Team</button>'+
			'<button onclick="getTeamList()">Find Team</button>'
			);
}
function loginView(){
	//changes view to login screen 
	
	$('body').html(
				'<p>Login with username <input type="text" id="username1"></p>'+
				'<p>Password<input type="password" id="pword"></p>'+
				'<button onclick="login()">Login</button>'+
				'<button onclick="createTeamView()">Create Team</button>'
				 );
	
		  $("button").click(function(){
			  $("p").insertAfter("p");
			  
			  $("body").html(
			   '<body>'+
			   '<p>Here is a list of things you can do</p>'+
			   '<button onclick="confirmJoin()">Confirm Join</button>'+
			   '</body'
			   );
		  });

	
}

function clearScreen(){
	//changes view to create a user and exposes login button 
	$('body').html(
		      '<p>Enter Username<input type="text" id="username"></p>'+
		      '<p>Enter Password<input type="password" id="pword1"></p>'+
			  '<p>Re-enter Password<input type="password" id="pword2"></p>'+	
			  '<p>First Name<input type="text" id="firstName"></p>'+
			  '<p>Last Name<input type="text" id="lastName"></p>'+
			  '<p>Enter Email<input type="text" id="email1"></p>'+
			  '<button onclick="validate()">Create User</button>'+
			  '<button onclick="loginView()">Login</button>'
			  );;
}

function tester(){
	
	$("button").click(function(){
		  $("p").insertAfter("p");
		  
		  $("body").html(
		   '<body>'+
		   '<p>Here is a list of things you can do</p>'+
		   '<button onclick="confirmJoin()">Confirm Join</button>'+
		   '</body'
		   );
	  });
}


