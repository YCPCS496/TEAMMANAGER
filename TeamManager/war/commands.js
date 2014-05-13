function createuser(){
	var usernameVal = $("#username").val();

	var password1Val = $("#pword1").val();
	var password2Val = $("#pword2").val();

	var firstNameVal = $("#firstName").val();
	var lastNameVal = $("#lastName").val();
	var emailVal = $("#email").val();


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

function login(){
	var usernameVal = $("#username").val();
	var password = $("#password").val();
	var parm = "/teammanager?action=login";
	$.ajax({
		type: "POST",
		url: parm,
		data: {
			username: usernameVal,
			password: password
		},
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			window.location.replace("/landingpage.html");
			console.log(data);
		},
		dataType: 'text'
	});
}

function createteam(teamname){

	var parm = "/teammanager?action=newTeam";
	$.ajax({
		type: "POST",
		url: parm,
		data: {
			TeamName: teamname
		},
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			console.log(data);
		},
		dataType: 'text'
	});
}
function requestjoin(teamid){

	var parm = "/teammanager?action=requestJoin";
	$.ajax({
		type: "POST",
		url: parm,
		data: {
			TeamID: teamid
		},
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			//alert(data);
			console.log(data);
		},
		dataType: 'text'
	});
}
function confirmjoin(teamid, userconfirm){

	var parm = "/teammanager?action=confirmJoin";
	$.ajax({
		type: "POST",
		url: parm,
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
function viewannouncement(annid){

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
}

function addworkout(){
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
			alert("Workout created");
			console.log(data);
		},
		contentType: "application/json",
		dataType: "json"
	});
}

function addannouncement(){
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
function viewteams(){

	$.ajax({
		type: "GET",
		url: "/teammanager/teams",
		success: function(data, textStatus, jqXHR) {
			// do something to let the user know what happened
			for (var team in data) { 
			   $("body").append(data[team].id+ "<br />");
			}
			alert(data);
			//console.log(data);
		},
		dataType: 'text'
	});
}