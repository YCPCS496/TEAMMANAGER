var count = 3;

//simple function to createUser information
function createUser(objForm) {
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
		  url: "/teammanager?action=newUser",
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
		
	}
	
	alert("User Created!");
}

function createTeam(){
	var teamName = $("#TeamName").val();

	//creates a team
	var param = "/teammanager?action=newTeam";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		  TeamName: teamName
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
function requestJoin(teamid){
	//creates a team
	var param = "/teammanager?action=requestJoin";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		TeamID: teamid
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		alert(data);
		console.log(data);
	  },
	  dataType: 'text'
	});

}


function confirmJoin(teamid, userconfirm){
	//Confirms a join to a team

	//creates a team
	var param = "/teammanager?action=confirmJoin";
	$.ajax({
	  type: "POST",
	  url: param,
	  data: {
		TeamID: teamid,
		UserConfirm: userconfirm
	  },
	  success: function(data, textStatus, jqXHR) {
		// do something to let the user know what happened
		//alert(data);
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
	var parm = "/teammanager?action=login";
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
		  url: "/teammanager/Teams",
		  success: function(data){
		  // do something to let the user know what happened
			 alert(data);
			 console.log(data);
		},
			contentType: "application/json",
			dataType: "json"
		});
	
}

function getTeamsId(){
	$.ajax({
	      type: "GET",
		  url: "/teammanager/Teams/teamid",
		  success: function(data){
		  // do something to let the user know what happened
			 console.log(data);
		},
			contentType: "application/json",
			dataType: "json"
		});
	
}


function getUsers(){
	$.ajax({
	      type: "GET",
		  url: "/teammanager/users",
		  success: function(data){
		  // do something to let the user know what happened
			 console.log(data);
		},
			contentType: "application/json",
			dataType: "json"
		});
	

}

function getUserId(){
	$.ajax({
	      type: "GET",
		  url: "/teammanager/users/userid",
		  success: function(data){
		  // do something to let the user know what happened
			 console.log(data);
		},
			contentType: "application/json",
			dataType: "json"
		});
	

}

function newAnnouncement(){
	//make a new announcement
	
	var announcement = JSON.stringify({
		  "usersViewed" : [ ],
		  "usersNotViewed" : [ ],
		  "title" : $("#title").val(),
		  "message" : $("#message").val(),
		  "id" : " "
		});



	var parm = "/teammanager?action=newAnnouncement";
	$.ajax({
		type: "POST",
		url: parm,
		data: workout,
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			console.log(data);
		},
		contentType: "application/json",
		dataType: "json"
	});
}

function viewAnnoucement(annid){
	
	var parm = "/teammanager?action=viewAnnouncement";
	$.ajax({
		type: "POST",
		url: parm,
		data: {
			announcementId: annid
		},
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			console.log(data);
		},
		dataType: 'text'
	});
}

function addWorkout(){
	//add workouts
	var workout = JSON.stringify({
		"title" : $("#title").val(),
		"notes" : $("#notes").val(),
		"durationMin" : $("#duration").val(),
		"intensity" : $("#intensity").val(),
		"reps" : $("#reps").val(),
		"id" : " "
	});



	var parm = "/teammanager?action=newWorkout";
	$.ajax({
		type: "POST",
		url: parm,
		data: workout,
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			console.log(data);
		},
		contentType: "application/json",
		dataType: "json"
	});
	alert("Your workout has been added!");
}

function logout(){

	var parm = "/teammanager?action=logout";
	$.ajax({
		type: "POST",
		url: parm,
		data: {
			announcementId: "test"
		},
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			console.log(data);
		},
		dataType: 'text'
	});
	
	alert("You're loggedout!");
}


function createTeamView(){
	$('body').html(
			'<p>Team Name<input type="text" id="TeamName"></p>'+
			'<button onClick="createTeam()">Create Team</button>'+
			'<button onclick="getTeamList()">Find Team</button>'+
			'<button onClick="logout()">logout</button>'+
			'<button onClick="mainMenu()">Back to the Team Manger Page</button>'+
			'<button onClick="addWorkoutView()">add Workout Page</button>'+
			'<button onClick="confirmUserView()">Confirm A User</button>'
			'<button onClick="requestJoin()">Request A Team</button>'
			
			//'<button onClick="addWorkoutView()">Confirm Join Page</button>'
			//'<button onClick="addWorkoutView()">Request Join Page</button>'
			);
}
function loginView(){
	//changes view to login screen 
	
	$('body').html(
				'<p>Login with username <input type="text" id="username1"></p>'+
				'<p>Password<input type="password" id="pword"></p>'+
				'<button onClick="login(); createTeamView();">Login</button>'+
				'<button onClick="logout()">logout</button>'+
				'<button onClick="addWorkoutView()">Add Workout Page</button>'+
				'<button onClick="mainMenu()">Back to the Team Manger Page</button>'	
				 );
	
//		  $("button").click(function(){
//			  $("p").insertAfter("p");
//			  
//			  $("body").html(
//			   '<body>'+
//			   '<p>Here is a list of things you can do</p>'+
//			   '<button onclick="confirmJoin()">Confirm Join</button>'+
//			   '</body'
//			   );

	
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
			  '<button onClick="createUser()">Create User</button>'+
			  '<button onClick="loginView()">Login</button>'+
			  '<button onClick="logout()">logout</button>'
			  );
}

function mainMenu(){
	
	$('body').html(
		'<h1>TEAM MANGER</h1>'+
		'<p>Login with username <input type="text" id="username1"></p>' +
		'<p>Enter password<input type="password" id="pword"></p>' +
		'<button onClick="login(); initialLoginView();">Login</button>' +
		
		'<button onClick="clearScreen()">Create User</button>' +
		'<button onClick="loginView()">More team options</button>' +
		'<button onClick="logout()">logout</button>'
		);
}

function addWorkoutView(){
	$('body').html(
	'<p>Title<input type="text" id="title"></p>'+
	'<p>Duration in mins<input type="number" id="duration"></p>'+
	'<p>Intensity<input type="number" id="intensity"></p>'+
	'<p>Reps<input type="text" id="reps"></p>'+
	
	'<button onClick="addWorkout()">Add Workout</button>'+
	'<button onClick="logout()">logout</button>'+
	'<button onClick="initialLoginView()">Login</button>'
	
	);
}

function initialLoginView(){
	createTeamView();
}

function userInter(){
	addWorkoutView();
}

function confirmUserView(){
	$('body').html(
			'<p>Team ID to confirm<input type="text" id="teamid"></p>'+
			'<p>User to confirm <input type="text" id="userconfirm"></p>'+
			'<button onClick="confirmJoin()">Confirm Join</button>'
			);
}

function requestJoinView(){
	$('body').html(
			'<p>Team ID to join <input type="text" id="teamid"></p>'+
			'<button onClick="requestJoin()">Request Join Join</button>'
			);
}


