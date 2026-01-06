function togglePassword(id, el) {
    const input = document.getElementById(id);
    const icon = el.querySelector('i');
    if (input.type === "password") {
        input.type = "text";
        icon.classList.replace("fa-eye", "fa-eye-slash");
    } else {
        input.type = "password";
        icon.classList.replace("fa-eye-slash", "fa-eye");
    }
}

document.getElementById("registerForm").addEventListener("submit", function (e) {
    const pass = document.getElementById("upassword").value;
    const repass = document.getElementById("repassword").value;
    const error = document.getElementById("passwordError");

    if (pass !== repass) {
        error.style.display = "block";
        error.innerText = "Passwords do not match!";
        e.preventDefault();
    }
});
