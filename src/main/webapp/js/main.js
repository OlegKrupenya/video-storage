/**
 * Created by oleh.krupenia on 7/27/2016.
 */
function loadUser() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var user = JSON.parse(xhttp.responseText);
            document.getElementById('username').innerHTML = user.firstName + ' ' + user.lastName;
            var player = document.getElementById('videoPlayer');
            var mp4Vid = document.getElementById('videoSrc');
            player.pause();
            var posVideo = window.location.href.indexOf('=');
            if (posVideo >= 0) {
                var videoId = window.location.href.substring(posVideo + 1);
                mp4Vid.src = window.location.href.split('?')[0] + "/video?videoId=" + videoId;
                player.load();
                player.play();
            } else {
                player.style.display = 'none';
                mp4Vid.style.display = 'none';
            }
        }
    };
    xhttp.open("GET", "/resources/user/getUser", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();
}
function logout() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var paramPos = window.location.href.indexOf('?');
            if (paramPos < 0) {
                window.location.href = window.location.href + '/login.html';
            } else {
                var url = window.location.href.substring(0, paramPos);
                window.location.href = url + 'login.html';
            }
        }
    };
    xhttp.open("POST", "/resources/user/logout", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send();
}