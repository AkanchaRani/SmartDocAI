// projects.js - handles Project Management CRUD page

requireLogin();
showLoggedInUser();

async function loadProjects() {

    const loggedInUser = JSON.parse(localStorage.getItem("smartdocai_user"));

    if (!loggedInUser) {
        window.location.href = "login.html";
        return;
    }

    const result = await apiGet("/projects/user/" + loggedInUser.id);

    const tbody = document.getElementById("projectsBody");
    tbody.innerHTML = "";

    if (!result.success || result.data.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7">No projects found.</td></tr>';
        return;
    }

    result.data.forEach(function(project) {

        const row = document.createElement("tr");

        row.innerHTML =
            "<td>" + project.id + "</td>" +
            "<td>" + project.projectName + "</td>" +
            "<td>" + (project.technology || "-") + "</td>" +
            "<td>" + (project.version || "-") + "</td>" +
            "<td>" + project.status + "</td>" +
            "<td>" + (project.createdDate || "-") + "</td>" +
            '<td class="action-links">' +
            '<button class="btn btn-small" onclick="editProject(' + project.id + ')">Edit</button> ' +
            '<button class="btn btn-small btn-danger" onclick="deleteProject(' + project.id + ')">Delete</button>' +
            "</td>";

        tbody.appendChild(row);
    });
}

async function saveProject() {

    const loggedInUser = JSON.parse(localStorage.getItem("smartdocai_user"));

    if (!loggedInUser) {
        alert("Please login first.");
        return;
    }

    const id = document.getElementById("projectId").value;

    const request = {
        projectName: document.getElementById("projectName").value,
        description: document.getElementById("description").value,
        technology: document.getElementById("technology").value,
        version: document.getElementById("version").value,
        status: document.getElementById("status").value,
        userId: loggedInUser.id
    };

    let result;

    if (id) {
        result = await apiPut("/projects/" + id, request);
    } else {
        result = await apiPost("/projects", request);
    }

    const messageBox = document.getElementById("formMessage");

    if (result.success) {

        messageBox.innerHTML =
            '<div class="message success">' + result.message + '</div>';

        resetForm();
        loadProjects();

    } else {

        messageBox.innerHTML =
            '<div class="message error">' + result.message + '</div>';
    }
}

async function editProject(id) {

    const result = await apiGet("/projects/" + id);

    if (!result.success) return;

    const project = result.data;

    document.getElementById("projectId").value = project.id;
    document.getElementById("projectName").value = project.projectName;
    document.getElementById("description").value = project.description || "";
    document.getElementById("technology").value = project.technology || "";
    document.getElementById("version").value = project.version || "";
    document.getElementById("status").value = project.status;

    document.getElementById("formHeading").textContent = "Edit Project";
}

async function deleteProject(id) {

    if (!confirm("Are you sure you want to delete this project?")) {
        return;
    }

    const result = await apiDelete("/projects/" + id);

    if (result.success) {
        loadProjects();
    }
}

function resetForm() {

    document.getElementById("projectId").value = "";
    document.getElementById("projectName").value = "";
    document.getElementById("description").value = "";
    document.getElementById("technology").value = "";
    document.getElementById("version").value = "";
    document.getElementById("status").value = "Planned";

    document.getElementById("formHeading").textContent = "Add New Project";
}

loadProjects();