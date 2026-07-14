// technical.js - handles Technical Information CRUD page

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

    loadTechnicalInfo();
}

async function loadTechnicalInfo() {

    const projectId = document.getElementById("projectSelect").value;
    const tbody = document.getElementById("infoBody");

    tbody.innerHTML = "";

    if (!projectId) {
        tbody.innerHTML =
            '<tr><td colspan="4">Select a project to view its technical info.</td></tr>';
        return;
    }

    const result = await apiGet("/technical-info/project/" + projectId);

    if (!result.success || result.data.length === 0) {
        tbody.innerHTML =
            '<tr><td colspan="4">No technical info added yet for this project.</td></tr>';
        return;
    }

    result.data.forEach(function (info) {

        const row = document.createElement("tr");

        row.innerHTML =
            "<td>" + (info.features || "-") + "</td>" +
            "<td>" + (info.requirements || "-") + "</td>" +
            "<td>" + (info.technologyStack || "-") + "</td>" +
            '<td class="action-links">' +
            '<button class="btn btn-small" onclick="editInfo(' + info.id + ')">Edit</button>' +
            '<button class="btn btn-small btn-danger" onclick="deleteInfo(' + info.id + ')">Delete</button>' +
            "</td>";

        tbody.appendChild(row);
    });
}

async function saveTechnicalInfo() {

    const projectId = document.getElementById("projectSelect").value;

    if (!projectId) {
        alert("Please select a project first.");
        return;
    }

    const id = document.getElementById("infoId").value;

    const request = {
        features: document.getElementById("features").value,
        requirements: document.getElementById("requirements").value,
        installationSteps: document.getElementById("installationSteps").value,
        architecture: document.getElementById("architecture").value,
        technologyStack: document.getElementById("technologyStack").value,
        projectId: Number(projectId)
    };

    let result;

    if (id) {
        result = await apiPut("/technical-info/" + id, request);
    } else {
        result = await apiPost("/technical-info", request);
    }

    const messageBox = document.getElementById("formMessage");

    if (result.success) {

        messageBox.innerHTML =
            '<div class="message success">' + result.message + '</div>';

        resetForm();
        loadTechnicalInfo();

    } else {

        messageBox.innerHTML =
            '<div class="message error">Something went wrong.</div>';

    }
}

async function editInfo(id) {

    const result = await apiGet("/technical-info/" + id);

    if (!result.success) return;

    const info = result.data;

    document.getElementById("infoId").value = info.id;
    document.getElementById("features").value = info.features || "";
    document.getElementById("requirements").value = info.requirements || "";
    document.getElementById("installationSteps").value = info.installationSteps || "";
    document.getElementById("architecture").value = info.architecture || "";
    document.getElementById("technologyStack").value = info.technologyStack || "";

    document.getElementById("formHeading").textContent = "Edit Technical Info";
}

async function deleteInfo(id) {

    if (!confirm("Are you sure you want to delete this entry?")) {
        return;
    }

    const result = await apiDelete("/technical-info/" + id);

    if (result.success) {
        loadTechnicalInfo();
    }
}

function resetForm() {

    document.getElementById("infoId").value = "";
    document.getElementById("features").value = "";
    document.getElementById("requirements").value = "";
    document.getElementById("installationSteps").value = "";
    document.getElementById("architecture").value = "";
    document.getElementById("technologyStack").value = "";

    document.getElementById("formHeading").textContent = "Add Technical Info";
}

loadProjectsIntoDropdown();