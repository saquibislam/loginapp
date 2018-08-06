function encryptLogin() {
	var fUsername = document.getElementById("username");
	var fPassword = document.getElementById("password");
	var strUsername = fUsername.value;
	var strPassword = fPassword.value;
	var strPassPhrase = document.getElementById("PassKey").value;

	if(strUsername.length <= 0 || strPassword.length <= 0) {
		alert("Please enter username and password.");
		return false;
	}

	try {
		var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
		var salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
		var aesUtil = new AesUtil(128, 1000);
		var ciphertext = aesUtil.encrypt(salt, iv, strPassPhrase, strPassword);
		var aesPassword = (iv + "::" + salt + "::" + ciphertext);    
		fPassword.value = btoa(aesPassword);
	} catch(e) {
		alert("Some error occurred. Please contact your system administrator.");
		fUsername.value = "";
		fPassword.value = "";
		return false;
	}

	return true;
}

function validateRegistration() {
	if(encryptLogin()) {
		return true;
	}
	return false;
}

function showRegisteration() {
	var form = document.forms["redirect-form"];
	form.action = "welcome1";
	form.submit();
}

function showLogin() {
	var form = document.forms["redirect-form"];
	form.action = "welcome";
	form.submit();
}

function onLoadRegistrationPage() {
	var eSalary = document.getElementById("salary");
	var ePinCode = document.getElementById("pinCode");
	if(eSalary.value == "0.0") {
		eSalary.value = "";
	}
	if(ePinCode.value == "0") {
		ePinCode.value = "";
	}
}

function toggleForms() {
	$('.login-form').animate({height: "toggle", opacity: "toggle"}, "slow");
	$('.register-form').animate({height: "toggle", opacity: "toggle"}, "slow");
}