// Login Page JavaScript

function togglePassword(inputId, eyeId) {
	var input = document.getElementById(inputId);
	var eye = document.getElementById(eyeId);
	if (input.type === "password") {
		input.type = "text";
		eye.classList.remove("fa-eye");
		eye.classList.add("fa-eye-slash");
	} else {
		input.type = "password";
		eye.classList.remove("fa-eye-slash");
		eye.classList.add("fa-eye");
	}
}
