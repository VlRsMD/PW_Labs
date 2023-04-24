const taskList = document.getElementsByTagName("LI");
var i;
for (i = 0; i < taskList.length; i++) {
    const span = document.createElement("SPAN");
    const txt = document.createTextNode("\u2716");
    span.className = "close";
    span.appendChild(txt);
    taskList[i].appendChild(span);
}
const close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
        const div = this.parentElement;
        div.style.display = "none";
    }
}

var i;
for (i = 0; i < taskList.length; i++) {
    const span = document.createElement("SPAN");
    const txt = document.createTextNode("\u2714");
    span.className = "done";
    span.appendChild(txt);
    taskList[i].appendChild(span);
}

const done = document.getElementsByClassName("done");
var i;
for (i = 0; i < done.length; i++) {
    done[i].onclick = function() {
        const div = this.parentElement;
        div.style.display = "none";
    }
}

const list = document.querySelector('ul');
list.addEventListener('click', function(ev) {
    if (ev.target.className === 'done') {
        ev.target.style.display = "none";
        const newLi = document.createElement("li");
        const task = event.target.parentElement.getElementsByClassName("taskTitle");
        const taskTitle = task.item(0).firstChild.nodeValue
        const taskText = document.createTextNode(taskTitle);
        newLi.appendChild(taskText);
        document.getElementById("list2").appendChild(newLi);
        const span = document.createElement("SPAN");
        const txt = document.createTextNode("\u2716");
        span.className = "close";
        span.appendChild(txt);
        newLi.appendChild(span);

        for (i = 0; i < close.length; i++) {
            close[i].onclick = function() {
                const div = this.parentElement;
                div.style.display = "none";
            }
        }
    }
}, false);

function newElement() {
    const li = document.createElement("li");
    const task = document.createElement("h3");
    const taskInput = document.getElementById("taskInput").value;
    const tI = document.createTextNode(taskInput);
    task.appendChild(tI);
    li.appendChild(task)

    const description = document.createElement("h4");
    const descriptionInput = document.getElementById("descriptionInput").value;
    const dI = document.createTextNode(descriptionInput);
    description.appendChild(dI);
    li.appendChild(description)

    const time = document.createElement("div");
    const timeInput = document.getElementById("timeInput").value;
    const timeI = document.createTextNode(timeInput);
    time.appendChild(timeI);
    li.appendChild(time)

    const image = document.createElement("div");
    const imageInputSrc = document.getElementById("imageInput").value
    var img = new Image()
    img.src = imageInputSrc
    img.style.length = 300+"px"
    img.style.width = 300+"px"
    image.appendChild(img)
    li.appendChild(image)

    document.getElementById("list1").appendChild(li);
    document.getElementById("taskInput").value = "";
    document.getElementById("descriptionInput").value = "";
    document.getElementById("timeInput").value = "";
    document.getElementById("imageInput").value = "";

    const spanClose = document.createElement("SPAN");
    const txtClose = document.createTextNode("\u2716");
    spanClose.className = "close";
    spanClose.appendChild(txtClose);
    li.appendChild(spanClose);
    for (i = 0; i < close.length; i++) {
        close[i].onclick = function () {
            var div = this.parentElement;
            div.style.display = "none";
        }
    }
    var i;
    for (i = 0; i < taskList.length; i++) {
        if (taskList[i].parentElement.className === 'unorderedListToDo') {
            const spanDone = document.createElement("SPAN");
            const txtDone = document.createTextNode("\u2714");
            spanDone.className = "done";
            spanDone.appendChild(txtDone);
            taskList[i].appendChild(spanDone);
        }
    }
    var i;
    for (i = 0; i < done.length; i++) {
        done[i].onclick = function () {
            var div = this.parentElement;
            div.style.display = "none";
        }
    }
}
