// login.js - handles login page

document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const messageBox = document.getElementById("loginMessage");

    try {
        const response = await fetch(API_BASE_URL + "/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email: email, password: password })
        });

        if (!response.ok) {
            const errorText = await response.text();
            messageBox.innerHTML = '<div class="message error">Invalid email or password</div>';
            return;
        }

        const result = await response.json();

        // Save user details locally so other pages know user is logged in
        localStorage.setItem("smartdocai_user", JSON.stringify(result.data));

        window.location.href = "dashboard.html";

    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong. Please try again.</div>';
    }
});
