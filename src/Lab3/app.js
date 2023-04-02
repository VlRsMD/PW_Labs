var taskList = document.getElementsByTagName("LI");
var i;
for (i = 0; i < taskList.length; i++) {
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u2716");
    span.className = "close";
    span.appendChild(txt);
    taskList[i].appendChild(span);
}

var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
        var div = this.parentElement;
        div.style.display = "none";
    }
}

var i;
for (i = 0; i < taskList.length; i++) {
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u2714");
    span.className = "done";
    span.appendChild(txt);
    taskList[i].appendChild(span);
}

var done = document.getElementsByClassName("done");
var i;
for (i = 0; i < done.length; i++) {
    done[i].onclick = function() {
        var div = this.parentElement;
        div.style.display = "none";
    }
}

var list = document.querySelector('ul');
list.addEventListener('click', function(ev) {
    if (ev.target.className === 'done') {
        ev.target.style.display = "none";
        var newLi = document.createElement("li");
        var task = event.target.parentElement.getElementsByClassName("taskTitle");
        var taskTitle = task.item(0).firstChild.nodeValue
        var taskText = document.createTextNode(taskTitle);
        newLi.appendChild(taskText);
        document.getElementById("list2").appendChild(newLi);
        var span = document.createElement("SPAN");
        var txt = document.createTextNode("\u2716");
        span.className = "close";
        span.appendChild(txt);
        newLi.appendChild(span);

        for (i = 0; i < close.length; i++) {
            close[i].onclick = function() {
                var div = this.parentElement;
                div.style.display = "none";
            }
        }
    }
}, false);

function newElement() {
    var li = document.createElement("li");

    var task = document.createElement("h3");
    var taskInput = document.getElementById("taskInput").value;
    var tI = document.createTextNode(taskInput);
    task.appendChild(tI);
    li.appendChild(task)

    var description = document.createElement("h4");
    var descriptionInput = document.getElementById("descriptionInput").value;
    var dI = document.createTextNode(descriptionInput);
    description.appendChild(dI);
    li.appendChild(description)

    var time = document.createElement("div");
    var timeInput = document.getElementById("timeInput").value;
    var timeI = document.createTextNode(timeInput);
    time.appendChild(timeI);
    li.appendChild(time)

    document.getElementById("list1").appendChild(li);

    document.getElementById("taskInput").value = "";
    document.getElementById("descriptionInput").value = "";
    document.getElementById("timeInput").value = "";

    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u2716");
    span.className = "close";
    span.appendChild(txt);
    li.appendChild(span);
    for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
            var div = this.parentElement;
            div.style.display = "none";
        }
    }
    var i;
    for (i = 0; i < taskList.length; i++) {
        if (taskList[i].parentElement.className === 'unorderedListToDo') {
            var span = document.createElement("SPAN");
            var txt = document.createTextNode("\u2714");
            span.className = "done";
            span.appendChild(txt);
            taskList[i].appendChild(span);
        }
    }
    var i;
    for (i = 0; i < done.length; i++) {
        done[i].onclick = function() {
            var div = this.parentElement;
            div.style.display = "none";
        }
    }
}