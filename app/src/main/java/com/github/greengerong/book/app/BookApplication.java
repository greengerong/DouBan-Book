package com.github.greengerong.book.app;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2014                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class BookApplication extends Application {
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
//        graph = ObjectGraph.create(getModules().toArray());
    }

    private List<Object> getModules() {
        return Arrays.asList(

        );
    }

    protected void inject(Object object) {
        graph.inject(object);
    }

    public ObjectGraph getGraph() {
        return graph;
    }
}
