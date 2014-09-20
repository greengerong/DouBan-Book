package com.github.greengerong.book.service;

import android.app.Activity;
import android.util.Log;

import com.github.greengerong.book.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

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
public class BookDataSource {
    public static JSONObject getBooks(Activity activity) {
        try {
            final String json = IOUtils.toString(activity.getResources().openRawResource(R.raw.data));
            Log.d(BookDataSource.class.getName(), json);
            return new JSONObject(json);
        } catch (Exception e) {
            Log.e(BookDataSource.class.getName(), e.getMessage());
        }
        return null;
    }
}
