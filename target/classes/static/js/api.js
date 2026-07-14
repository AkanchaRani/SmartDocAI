// api.js - handles API Documentation CRUD page

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

    loadApis();
}

async function loadApis() {

    const projectId = document.getElementById("projectSelect").value;
    const tbody = document.getElementById("apiBody");

    tbody.innerHTML = "";

    if (!projectId) {
        tbody.innerHTML =
            '<tr><td colspan="5">Select a project to view its APIs.</td></tr>';
        return;
    }

    const result = await apiGet("/apis/project/" + projectId);

    if (!result.success || result.data.length === 0) {
        tbody.innerHTML =
            '<tr><td colspan="5">No APIs added yet for this project.</td></tr>';
        return;
    }

    result.data.forEach(function (api) {

        const row = document.createElement("tr");

        row.innerHTML =
            "<td>" + api.apiName + "</td>" +
            "<td>" + api.method + "</td>" +
            "<td>" + api.endpoint + "</td>" +
            "<td>" + (api.description || "-") + "</td>" +
            '<td class="action-links">' +
            '<button class="btn btn-small" onclick="editApi(' + api.id + ')">Edit</button>' +
            '<button class="btn btn-small btn-danger" onclick="deleteApi(' + api.id + ')">Delete</button>' +
            "</td>";

        tbody.appendChild(row);
    });
}

async function saveApi() {

    const projectId = document.getElementById("projectSelect").value;

    if (!projectId) {
        alert("Please select a project first.");
        return;
    }

    const id = document.getElementById("apiId").value;

    const request = {
        apiName: document.getElementById("apiName").value,
        method: document.getElementById("method").value,
        endpoint: document.getElementById("endpoint").value,
        description: document.getElementById("description").value,
        requestExample: document.getElementById("requestExample").value,
        responseExample: document.getElementById("responseExample").value,
        projectId: Number(projectId)
    };

    let result;

    if (id) {
        result = await apiPut("/apis/" + id, request);
    } else {
        result = await apiPost("/apis", request);
    }

    const messageBox = document.getElementById("formMessage");

    if (result.success) {

        messageBox.innerHTML =
            '<div class="message success">' + result.message + '</div>';

        resetForm();
        loadApis();

    } else {

        messageBox.innerHTML =
            '<div class="message error">Something went wrong.</div>';

    }
}

async function editApi(id) {

    const result = await apiGet("/apis/" + id);

    if (!result.success) return;

    const api = result.data;

    document.getElementById("apiId").value = api.id;
    document.getElementById("apiName").value = api.apiName;
    document.getElementById("method").value = api.method;
    document.getElementById("endpoint").value = api.endpoint;
    document.getElementById("description").value = api.description || "";
    document.getElementById("requestExample").value = api.requestExample || "";
    document.getElementById("responseExample").value = api.responseExample || "";

    document.getElementById("formHeading").textContent = "Edit API";
}

async function deleteApi(id) {

    if (!confirm("Are you sure you want to delete this API?")) {
        return;
    }

    const result = await apiDelete("/apis/" + id);

    if (result.success) {
        loadApis();
    }
}

function resetForm() {

    document.getElementById("apiId").value = "";
    document.getElementById("apiName").value = "";
    document.getElementById("method").value = "GET";
    document.getElementById("endpoint").value = "";
    document.getElementById("description").value = "";
    document.getElementById("requestExample").value = "";
    document.getElementById("responseExample").value = "";

    document.getElementById("formHeading").textContent = "Add New API";
}

loadProjectsIntoDropdown();