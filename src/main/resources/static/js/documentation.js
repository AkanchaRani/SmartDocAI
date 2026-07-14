// documentation.js - shows the latest generated documentation for a project

requireLogin();
showLoggedInUser();

async function loadProjectsIntoDropdown() {

    const user = JSON.parse(localStorage.getItem("smartdocai_user"));

    if (!user) {
        window.location.href = "login.html";
        return;
    }

    const result = await apiGet("/projects/user/" + user.id);

    const select = document.getElementById("projectSelect");

    select.innerHTML = '<option value="">-- Select Project --</option>';

    if (result.success) {
        fillProjectDropdown(select, result.data);
    }
}

async function loadDocumentation() {

    const projectId = document.getElementById("projectSelect").value;
    const messageBox = document.getElementById("docMessage");
    const resultBox = document.getElementById("resultBox");

    resultBox.classList.add("hidden");
    messageBox.innerHTML = "";

    if (!projectId) {
        return;
    }

    const result = await apiGet("/generate/project/" + projectId);

    if (!result.success) {

        messageBox.innerHTML =
            '<div class="message error">No documentation generated yet for this project. Go to the Generate Docs page first.</div>';

        return;
    }

    document.getElementById("docOutput").textContent = result.data.content;

    resultBox.classList.remove("hidden");

    document.getElementById("downloadBtn").onclick = function () {
        window.location.href = API_BASE_URL + "/generate/pdf/" + projectId;
    };
}

loadProjectsIntoDropdown();