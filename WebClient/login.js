var count = 3;

//simple function to validate information
function validate(objForm) {
var emailAddr = document.getElementById('username').value;
var pw = document.getElementById('pword').value;

var valid = false;
var emailAddrArray = ["test"];  // as many as you like - no comma after final entry
var pwArray = ["test"];  // the corresponding passwords;
// servlet calls and such

	for (var i=0; i < emailAddrArray.length; i++) {
		if ((emailAddr == emailAddrArray[i]) && (pw == pwArray[i])) {
		valid = true;
		break;
	}
}
	//if input was valid 
	
	if(objForm..value.length==0){
			alert("Please enter Username");
			objForm.username.focus();
			return false;
	}
	
	if(objForm.pword.value.length==0){
			alert("Please enter Username");
			objForm.pword.focus();
			return false;
		}
		
	if (valid) {
		alert ("Login was successful");
		window.location = "http://www.ycp.edu";
		$("body").empty(); // clear all existing elements from document
	
		return false;
	}

var t = " tries";
	if (count == 1) {t = " try"}
		if (count >= 1) {
			alert ("Invalid email and/or password.  You have " + count + t + " left.");
			document.email = "";
			document.pword = "";
			count --;
		}
		
		if(count <= 0){
			window.location = "http://www.ycp.edu";
		}
}

function tester(){
	//window.alert("test click");
	$("body").empty(); // clear all existing elements from document
	$("body").append("<p>Hey, this is the new user interface!</p>");
}