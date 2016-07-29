/**
 * Created by oleh.krupenia on 7/27/2016.
 */
function redirectToMainPage() {
    var posLogin = window.location.href.indexOf('login');
    var url = window.location.href.substring(0, posLogin) + 'videos.html';
    window.location.href = url;
}
function signIn() {
    document.getElementById('btnSignIp').disabled = "disabled";
    document.getElementById('btnSignUp').disabled = "disabled";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            redirectToMainPage();
        }
    };
    xhttp.open("POST", "/resources/user/signin", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify({
        email: document.getElementById('emailSignIn').value,
        password: document.getElementById('passwordSignIn').value
    }));
}
function signUp() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            redirectToMainPage();
        }
    };
    xhttp.open("POST", "/resources/user/signup", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify({
        firstName: document.getElementById('firstNameSignUp').value,
        lastName: document.getElementById('lastNameSignUp').value,
        email: document.getElementById('emailSignUp').value,
        password: document.getElementById('passwordSignUp').value
    }));
}
function signInFacebook() {
    window.location.href = "https://www.facebook.com/v2.6/dialog/oauth?response_type=code&client_id=1212991205417742&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ffacebook";
}
function onKeyDownHandler(event) {
    if (event.keyCode == 13) {
        signIn();
    }
}