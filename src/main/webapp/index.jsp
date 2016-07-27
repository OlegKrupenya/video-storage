<%--
  Created by IntelliJ IDEA.
  User: oleh.krupenia
  Date: 6/2/2016
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploading Form</title>
    <script type="application/javascript">
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
        }
        ;
        xhttp.open("GET", "/resources/user/getUser", true);
        xhttp.setRequestHeader("Content-Type", "application/json");
        xhttp.send();
        }
        function logout() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    window.location.href = window.location.href + '/login.html';
                }
            };
            xhttp.open("POST", "/resources/user/logout", true);
            xhttp.setRequestHeader("Content-Type", "application/json");
            xhttp.send();
        }
    </script>
</head>
<body onload="loadUser()">
<span>Welcome,
        <span id="username"></span>
    </span>
<h3>File Upload:</h3>
Select a file to upload: <br/>
<form action="upload" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>
<video id="videoPlayer" width="800" height="600" controls>
    <source id="videoSrc" type="video/ogg">
    Your browser does not support the video tag.
</video>
<div style="position: absolute; right: 30px; top: 5px;">
    <a href="javascript:void(0)" onclick="logout()">Logout</a>
</div>
</body>
</html>
