// database.js - handles Database Schema CRUD page

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

    loadTables();
}

async function loadTables() {

    const projectId = document.getElementById("projectSelect").value;
    const tbody = document.getElementById("tableBody");

    tbody.innerHTML = "";

    if (!projectId) {
        tbody.innerHTML =
            '<tr><td colspan="5">Select a project to view its tables.</td></tr>';
        return;
    }

    const result = await apiGet("/database-tables/project/" + projectId);

    if (!result.success || result.data.length === 0) {
        tbody.innerHTML =
            '<tr><td colspan="5">No tables added yet for this project.</td></tr>';
        return;
    }

    result.data.forEach(function (table) {

        const row = document.createElement("tr");

        row.innerHTML =
            "<td>" + table.tableName + "</td>" +
            "<td>" + (table.columns || "-") + "</td>" +
            "<td>" + (table.primaryKey || "-") + "</td>" +
            "<td>" + (table.foreignKey || "-") + "</td>" +
            '<td class="action-links">' +
            '<button class="btn btn-small" onclick="editTable(' + table.id + ')">Edit</button>' +
            '<button class="btn btn-small btn-danger" onclick="deleteTable(' + table.id + ')">Delete</button>' +
            "</td>";

        tbody.appendChild(row);
    });
}

async function saveTable() {

    const projectId = document.getElementById("projectSelect").value;

    if (!projectId) {
        alert("Please select a project first.");
        return;
    }

    const id = document.getElementById("tableId").value;

    const request = {
        tableName: document.getElementById("tableName").value,
        columns: document.getElementById("columns").value,
        primaryKey: document.getElementById("primaryKey").value,
        foreignKey: document.getElementById("foreignKey").value,
        datatype: document.getElementById("datatype").value,
        projectId: Number(projectId)
    };

    let result;

    if (id) {
        result = await apiPut("/database-tables/" + id, request);
    } else {
        result = await apiPost("/database-tables", request);
    }

    const messageBox = document.getElementById("formMessage");

    if (result.success) {

        messageBox.innerHTML =
            '<div class="message success">' + result.message + '</div>';

        resetForm();
        loadTables();

    } else {

        messageBox.innerHTML =
            '<div class="message error">Something went wrong.</div>';

    }
}

async function editTable(id) {

    const result = await apiGet("/database-tables/" + id);

    if (!result.success) return;

    const table = result.data;

    document.getElementById("tableId").value = table.id;
    document.getElementById("tableName").value = table.tableName;
    document.getElementById("columns").value = table.columns || "";
    document.getElementById("primaryKey").value = table.primaryKey || "";
    document.getElementById("foreignKey").value = table.foreignKey || "";
    document.getElementById("datatype").value = table.datatype || "";

    document.getElementById("formHeading").textContent = "Edit Table";
}

async function deleteTable(id) {

    if (!confirm("Are you sure you want to delete this table?")) {
        return;
    }

    const result = await apiDelete("/database-tables/" + id);

    if (result.success) {
        loadTables();
    }
}

function resetForm() {

    document.getElementById("tableId").value = "";
    document.getElementById("tableName").value = "";
    document.getElementById("columns").value = "";
    document.getElementById("primaryKey").value = "";
    document.getElementById("foreignKey").value = "";
    document.getElementById("datatype").value = "";

    document.getElementById("formHeading").textContent = "Add New Table";
}

loadProjectsIntoDropdown();