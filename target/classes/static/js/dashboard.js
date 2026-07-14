requireLogin();
showLoggedInUser();

async function loadDashboard() {

    const user = JSON.parse(localStorage.getItem("smartdocai_user"));

    const result = await apiGet("/dashboard/summary/" + user.id);

    if (!result.success) {
        alert("Failed to load dashboard");
        return;
    }

    const data = result.data;

    document.getElementById("totalProjects").textContent = data.totalProjects;
    document.getElementById("totalDocuments").textContent = data.totalDocuments;

    const tbody = document.getElementById("recentProjectsBody");
    tbody.innerHTML = "";

    if (data.recentProjects.length === 0) {

        tbody.innerHTML =
            '<tr><td colspan="4">No projects found.</td></tr>';

        return;
    }

    data.recentProjects.forEach(function (project) {

        const row = document.createElement("tr");

        row.innerHTML =
            "<td>" + project.projectName + "</td>" +
            "<td>" + (project.technology || "-") + "</td>" +
            "<td>" + project.status + "</td>" +
            "<td>" + project.createdDate + "</td>";

        tbody.appendChild(row);

    });

}

loadDashboard();