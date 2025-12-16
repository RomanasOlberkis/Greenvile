/* -------------------------------------------------------
   LOAD DATA FROM JSON FILE
-------------------------------------------------------- */
function loadData() {

    // Temporary loading messages
    if (document.getElementById("greenActions"))
        document.getElementById("greenActions").innerHTML = "<p>Loading actions...</p>";

    if (document.getElementById("communalGoals"))
        document.getElementById("communalGoals").innerHTML = "<p>Loading goals...</p>";

    if (document.getElementById("trades"))
        document.getElementById("trades").innerHTML = "<p>Loading trades...</p>";

    // Fetch JSON file
    fetch('data/cloverville_data.json')
        .then(response => response.json())
        .then(data => displayData(data))
        .catch(error => console.log("Error loading JSON:", error));
}

/* -------------------------------------------------------
   DISPLAY ALL DATA IN THE DASHBOARD
-------------------------------------------------------- */
function displayData(data) {

    /* Update communal points */
    var poolSpan = document.getElementById('communalPool');
    if (poolSpan) poolSpan.textContent = data.communalPointsPool;

    /* =====================================================
       GREEN ACTIONS (with View more)
    ====================================================== */
    var actionsDiv = document.getElementById('greenActions');
    if (actionsDiv) {
        actionsDiv.innerHTML = "";

        if (data.greenActions && data.greenActions.length > 0) {
            data.greenActions.forEach(action => {
                var div = document.createElement('div');
                div.classList.add('card');

                div.innerHTML = `
                    ${action.picture ? `<img src="${action.picture}" alt="${action.title}">` : ""}
                    <h3>${action.title}</h3>
                `;

                const description = document.createElement("p");
                description.textContent = action.description;
                description.classList.add("description");
                div.appendChild(description);

                if (action.description.length > 120) {
                    const viewMore = document.createElement("span");
                    viewMore.classList.add("view-more");
                    viewMore.textContent = "View more";

                    viewMore.addEventListener("click", () => {
                        description.classList.toggle("expanded");
                        viewMore.textContent =
                            description.classList.contains("expanded")
                                ? "View less"
                                : "View more";
                    });

                    div.appendChild(viewMore);
                }

                actionsDiv.appendChild(div);
            });
        } else {
            actionsDiv.innerHTML = "<p>No green actions to display.</p>";
        }
    }

    /* =====================================================
       COMMUNAL GOALS
    ====================================================== */
    var goalsDiv = document.getElementById('communalGoals');
    if (goalsDiv) {
        goalsDiv.innerHTML = "";

        if (data.communalGoals && data.communalGoals.length > 0) {
            data.communalGoals.forEach(goal => {
                var divGoal = document.createElement('div');
                divGoal.classList.add('card');

                var progressPercent = goal.pointsNeeded
                    ? Math.round((goal.currentPoints / goal.pointsNeeded) * 100)
                    : 0;

                divGoal.innerHTML = `
                    <h3>${goal.title}</h3>
                    <p>${goal.description}</p>
                    <p><strong>Progress:</strong> ${goal.currentPoints} / ${goal.pointsNeeded} (${progressPercent}%)</p>
                    <progress value="${goal.currentPoints}" max="${goal.pointsNeeded}"></progress>
                `;

                goalsDiv.appendChild(divGoal);
            });
        } else {
            goalsDiv.innerHTML = "<p>No communal goals to display.</p>";
        }
    }

    /* =====================================================
       TRADES
    ====================================================== */
    var tradesDiv = document.getElementById('trades');
    if (tradesDiv) {
        tradesDiv.innerHTML = "";

        if (data.trades && data.trades.length > 0) {
            data.trades.forEach(trade => {
                var divTrade = document.createElement('div');
                divTrade.classList.add('card');

                divTrade.innerHTML = `
                    ${trade.picture ? `<img src="${trade.picture}" alt="${trade.title}">` : ""}
                    <h3>${trade.title}</h3>
                    <p>${trade.description}</p>
                    <p><strong>Cost:</strong> ${trade.pointsCost} points</p>
                `;

                tradesDiv.appendChild(divTrade);
            });
        } else {
            tradesDiv.innerHTML = "<p>No trades available.</p>";
        }
    }

    /* =====================================================
       COMMUNAL TASKS
    ====================================================== */
    var tasksDiv = document.getElementById('communalTasks');
    if (tasksDiv) {
    tasksDiv.innerHTML = "";
    var hasTasks = false;
    if (data.communalGoals && data.communalGoals.length > 0) {
        data.communalGoals.forEach(goal => {
            if (goal.tasks && goal.tasks.length > 0) {
                goal.tasks.forEach(task => {
                    hasTasks = true;
                    var divTask = document.createElement('div');
                    divTask.classList.add('card');

                    divTask.innerHTML = `
                        <h3>${task.title}</h3>
                        <p>${task.description}</p>
                        <p><strong>Goal:</strong> ${goal.title}</p>
                        ${task.deadline ? `<p><strong>Deadline:</strong> ${task.deadline}</p>` : ""}
                        ${task.points ? `<p><strong>Points:</strong> ${task.points}</p>` : ""}
                    `;

                    tasksDiv.appendChild(divTask);
                });
            }
        });
    }
    if (!hasTasks) {
        tasksDiv.innerHTML = "<p>No communal tasks to display.</p>";
    }
}
}

/* -------------------------------------------------------
   TAB SWITCHING
-------------------------------------------------------- */
function showTab(tabId, button) {
    document.querySelectorAll(".tab-content").forEach(tab => {
        tab.style.display = "none";
    });

    var activeTab = document.getElementById(tabId);
    if (activeTab) activeTab.style.display = "block";

    document.querySelectorAll(".tab-btn").forEach(btn => {
        btn.classList.remove("active");
    });

    if (button) button.classList.add("active");

    document.querySelector(".sidebar").style.display = "none";
}

/* -------------------------------------------------------
   HAMBURGER MENU TOGGLE
-------------------------------------------------------- */
const menuToggle = document.querySelector(".menu-toggle");
const sidebar = document.querySelector(".sidebar");

if (menuToggle) {
    menuToggle.addEventListener("click", () => {
        sidebar.style.display =
            (sidebar.style.display === "block") ? "none" : "block";
    });
}

/* Close sidebar if clicking outside */
document.addEventListener("click", function (e) {
    if (!e.target.closest(".sidebar") && !e.target.closest(".menu-toggle")) {
        sidebar.style.display = "none";
    }
});

/* -------------------------------------------------------
   START PROGRAM
-------------------------------------------------------- */
loadData();
showTab('overview');