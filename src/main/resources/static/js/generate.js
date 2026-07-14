// generate.js - handles AI Documentation Generator page

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

async function generateDocumentation() {

    const projectId = document.getElementById("projectSelect").value;
    const messageBox = document.getElementById("generateMessage");

    if (!projectId) {
        messageBox.innerHTML =
            '<div class="message error">Please select a project first.</div>';
        return;
    }

    messageBox.innerHTML =
        '<div class="message success">Generating documentation, please wait...</div>';

    const result = await apiPost("/generate/" + projectId, {});

    if (!result.success) {

        messageBox.innerHTML =
            '<div class="message error">Failed to generate documentation.</div>';

        return;
    }

    messageBox.innerHTML =
        '<div class="message success">Documentation generated successfully.</div>';

    document.getElementById("docOutput").textContent = result.data.content;

    document.getElementById("resultBox").classList.remove("hidden");

    document.getElementById("downloadBtn").onclick = function () {
        window.location.href = API_BASE_URL + "/generate/pdf/" + projectId;
    };
}

loadProjectsIntoDropdown();