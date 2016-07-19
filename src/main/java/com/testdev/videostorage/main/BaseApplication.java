package com.testdev.videostorage.main;

import com.testdev.videostorage.web.UserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleh.krupenia.
 */
@ApplicationPath("resources")
public class BaseApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(UserController.class);
        return s;
    }
}