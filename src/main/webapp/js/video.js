/**
 * Created by oleh.krupenia on 7/27/2016.
 */
function loadUser() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var user = JSON.parse(xhttp.responseText);
            document.getElementById('username').innerHTML = user.firstName + ' ' + user.lastName;
            loadVideoForUser();
        }
    };
    xhttp.open("GET", "/resources/user/getUser", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();
}
function loadVideoForUser() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var array = eval(xhttp.responseText);
            if (xhttp.responseText) {
                makeUL(array);
            } else {
                document.getElementById('videoContainer').innerHTML = 'Video will be here...';
            }
        }
    };
    xhttp.open("GET", "/resources/video/getVideosForUser", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();
}
function logout() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var posLogin = window.location.href.indexOf('videos');
            var url = window.location.href.substring(0, posLogin);
            window.location.href = url + 'login.html';
        }
    };
    xhttp.open("POST", "/resources/user/logout", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();
}
function redirectToUploadPage() {
    var posLogin = window.location.href.indexOf('videos');
    window.location.href = window.location.href.substring(0, posLogin);
}

function makeUL(array) {
    var list = document.createElement('ul');
    for (var i = 0; i < array.length; i++) {
        var item = document.createElement('li');
        var aTag = document.createElement('a');
        var posLogin = window.location.href.indexOf('videos');
        var url = window.location.href.substring(0, posLogin);
        url = url + '?videoId=' + array[i].videoId;
        aTag.setAttribute("href", url);
        aTag.innerHTML = array[i].title;
        item.appendChild(aTag);
        var checkbox = document.createElement('input');
        checkbox.type = "checkbox";
        checkbox.name = "videoRecord" + array[i].videoId;
        item.appendChild(checkbox);
        list.appendChild(item);
    }
    document.getElementById("videoContainer").appendChild(list);
    return list;
}
function removeVideos() {
    var inputs = document.getElementsByTagName("input");
    var idsToRemove = [];
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].name.indexOf('videoRecord') == 0 && inputs[i].checked) {
            idsToRemove.push({videoId: inputs[i].name.substring(11)});
        }
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('videoContainer').innerHTML = "";
            loadVideoForUser();
        }
    };
    xhttp.open("DELETE", "/resources/video/removeVideos", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    var data = JSON.stringify(idsToRemove);
    xhttp.send(data);
}
