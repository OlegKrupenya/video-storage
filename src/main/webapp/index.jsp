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
    <script type="application/javascript" src="js/main.js"></script>
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
