document.getElementById("registerForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    const message = document.getElementById("message");

    if (password !== confirmPassword) {
        message.innerHTML = "Passwords do not match";
        message.style.color = "red";
        return;
    }

    const response = await fetch("/users", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            name: name,
            email: email,
            password: password,
            role: "USER"

        })

    });

    const result = await response.json();

    if (result.success) {

        alert("Registration Successful");

        window.location.href = "login.html";

    } else {

        message.innerHTML = result.message;
        message.style.color = "red";

    }

});