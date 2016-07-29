package com.testdev.videostorage.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.testdev.videostorage.domain.FBUser;
import com.testdev.videostorage.domain.User;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author oleh.krupenia.
 */
@WebServlet("/facebook")
@MultipartConfig
public class FacebookAuthenticationServlet extends HttpServlet {
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.6/me";
    private static final String CLIENT_ID = "1212991205417742";
    private static final String CLIENT_SECRET = "0ca173a6d49685a213cde48f635f48e4";
    private final OAuth20Service service = new ServiceBuilder()
            .apiKey(CLIENT_ID)
            .apiSecret(CLIENT_SECRET)
            .callback("http://localhost:8080/facebook")
            .build(FacebookApi.instance());


    public void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws IOException {
        String oauth = httpRequest.getParameter("code");
        final OAuth2AccessToken accessToken = service.getAccessToken(oauth);
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        FBUser fbUser = parseResponse(response);
        putUserIntoSession(httpRequest, fbUser);

        httpResponse.sendRedirect("videos.html");
    }

    private void putUserIntoSession(HttpServletRequest httpRequest, FBUser fbUser) {
        User user = new User();
        user.setFirstName(fbUser.getName());
        user.setLastName("");
        user.setUserId(fbUser.getId());
        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", user);
    }

    private FBUser parseResponse(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), FBUser.class);
    }
}
