function loadData() {
    fetch('data/website_data.json')
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            displayData(data);
        })
        .catch(function(error) {
            console.log('Error loading data: ' + error);
        });
}

function displayData(data) {
    document.getElementById('communalPool').textContent = data.communalPointsPool;

    var greenActionsDiv = document.getElementById('greenActions');
    greenActionsDiv.innerHTML = '';
    for (var i = 0; i < data.greenActions.length; i++) {
        var action = data.greenActions[i];
        var div = document.createElement('div');
        div.innerHTML = '<h3>' + action.title + '</h3><p>' + action.description + '</p>';
        if (action.picture) {
            div.innerHTML += '<img src="' + action.picture + '" width="200">';
        }
        div.innerHTML += '<hr>';
        greenActionsDiv.appendChild(div);
    }

    var goalsDiv = document.getElementById('communalGoals');
    goalsDiv.innerHTML = '';
    for (var i = 0; i < data.communalGoals.length; i++) {
        var goal = data.communalGoals[i];
        var div = document.createElement('div');
        var progress = Math.round((goal.currentPoints / goal.pointsNeeded) * 100);
        div.innerHTML = '<h3>' + goal.title + '</h3>';
        div.innerHTML += '<p>' + goal.description + '</p>';
        div.innerHTML += '<p>Progress: ' + goal.currentPoints + ' / ' + goal.pointsNeeded + ' (' + progress + '%)</p>';
        if (goal.completed) {
            div.innerHTML += '<p><strong>COMPLETED</strong></p>';
        }
        if (goal.tasks.length > 0) {
            div.innerHTML += '<h4>Tasks:</h4><ul>';
            for (var j = 0; j < goal.tasks.length; j++) {
                var task = goal.tasks[j];
                var status = task.completed ? ' (Done)' : '';
                div.innerHTML += '<li>' + task.title + ': ' + task.description + status + '</li>';
            }
            div.innerHTML += '</ul>';
        }
        div.innerHTML += '<hr>';
        goalsDiv.appendChild(div);
    }

    var tradesDiv = document.getElementById('trades');
    tradesDiv.innerHTML = '';
    for (var i = 0; i < data.trades.length; i++) {
        var trade = data.trades[i];
        var div = document.createElement('div');
        div.innerHTML = '<h3>' + trade.title + '</h3>';
        div.innerHTML += '<p>' + trade.description + '</p>';
        div.innerHTML += '<p>Cost: ' + trade.pointsCost + ' points</p>';
        if (trade.picture) {
            div.innerHTML += '<img src="' + trade.picture + '" width="200">';
        }
        div.innerHTML += '<hr>';
        tradesDiv.appendChild(div);
    }
}

loadData();
