// common.js
// Shared helper functions used across all pages of SmartDocAI frontend.

const API_BASE_URL = "http://localhost:8080";

// Checks if a user is logged in (stored in localStorage after successful login).
// If not logged in, redirects to login page.
function requireLogin() {
    const user = localStorage.getItem("smartdocai_user");
    if (!user) {
        window.location.href = "login.html";
        return null;
    }
    return JSON.parse(user);
}

// Shows the logged in user's name in the navbar (if the element exists on the page)
function showLoggedInUser() {
    const user = localStorage.getItem("smartdocai_user");
    const nameEl = document.getElementById("loggedInUserName");
    if (user && nameEl) {
        const userObj = JSON.parse(user);
        nameEl.textContent = userObj.name;
    }
}

// Logs the user out - calls backend logout API and clears local storage
async function logout() {
    try {
        await fetch(API_BASE_URL + "/auth/logout", { method: "POST" });
    } catch (err) {
        console.log("Logout API call failed, clearing session locally anyway.");
    }
    localStorage.removeItem("smartdocai_user");
    window.location.href = "login.html";
}

// Some error responses from the backend (like 404, 500) are returned as
// plain text and not as ApiResponse JSON. This helper reads the response
// safely either way and always returns an object with a "success" field.
async function readResponse(response) {
    const text = await response.text();

    try {
        const json = JSON.parse(text);
        // If backend already sent an ApiResponse shaped object, use it as is
        if (typeof json.success !== "undefined") {
            return json;
        }
        return { success: response.ok, message: text, data: json };
    } catch (e) {
        // Response was plain text (usually an error message)
        return { success: response.ok, message: text, data: null };
    }
}

// Generic GET request, returns parsed JSON body (ApiResponse<T>)
async function apiGet(path) {
    const response = await fetch(API_BASE_URL + path);
    return readResponse(response);
}

// Generic POST request
async function apiPost(path, data) {
    const response = await fetch(API_BASE_URL + path, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });
    return readResponse(response);
}

// Generic PUT request
async function apiPut(path, data) {
    const response = await fetch(API_BASE_URL + path, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });
    return readResponse(response);
}

// Generic DELETE request
async function apiDelete(path) {
    const response = await fetch(API_BASE_URL + path, { method: "DELETE" });
    return readResponse(response);
}

// Fills a <select> element with a list of projects (used on several pages)
function fillProjectDropdown(selectEl, projects) {
    selectEl.innerHTML = '<option value="">-- Select Project --</option>';
    projects.forEach(function (project) {
        const option = document.createElement("option");
        option.value = project.id;
        option.textContent = project.projectName;
        selectEl.appendChild(option);
    });
}
