<script type= "text/javascript">
//Not secure, just a test

<html>
<head>
<script>
var count = 2;
function validate() {
var emailAddr = document.myform.email.value;
var pw = document.myform.pword.value;

var valid = false;
var emailAddrArray = ["Test@gmail.com"];  // as many as you like - no comma after final entry
var pwArray = ["Test1"];  // the corresponding passwords;

	for (var i=0; i <emailAddrArray.length; i++) {
		if ((emailAddr == emailAddrArray[i]) && (pw == pwArray[i])) {
		valid = true;
		break;
	}
}

	if (valid) {
		alert ("Login was successful");
		window.location = "http://www.google.com";
		return false;
	}

var t = " tries";
	if (count == 1) {t = " try"}
		if (count >= 1) {
			alert ("Invalid email and/or password.  You have " + count + t + " left.");
			document.myform.email.value = "";
			document.myform.pword.value = "";
			setTimeout("document.myform.email.focus()", 25);
			setTimeout("document.myform.email.select()", 25);
			count --;
		}
		else {
			alert ("Still incorrect! You have no more tries left!");
			document.myform.email.value = "No more tries allowed!";
			document.myform.pword.value = "";
			document.myform.email.disabled = true;
			document.myform.pword.disabled = true;
			return false;
		}

}

function tester(){
	window.alert("test click");
}
</script>
</head>
<body>
</body>
</html>



	