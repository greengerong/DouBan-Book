package com.github.greengerong.book.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
public class ActivityBase extends Activity {

    private ObjectGraph objectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BookApplication app = (BookApplication) getApplication();
//        objectGraph = app.getGraph().plus(getInjectModules().toArray());
//        objectGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        objectGraph = null;
        super.onDestroy();
    }

    protected <T> T inject(T obj) {
        return objectGraph.inject(obj);
    }

    private List<Object> getInjectModules() {
        return Arrays.asList();
    }
}
