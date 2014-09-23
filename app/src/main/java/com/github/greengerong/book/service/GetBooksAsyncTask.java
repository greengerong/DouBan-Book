package com.github.greengerong.book.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.github.greengerong.book.adapter.BookListViewAdapter;
import com.github.greengerong.book.utils.delegate.Action1;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;

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
public class GetBooksAsyncTask extends AsyncTask<String, Void, JSONObject> {
    private static final String TAG = GetBooksAsyncTask.class.getName();
    private Action1<JSONObject> onPostExecuteListener;

    public GetBooksAsyncTask setOnPostExecuteListener(Action1<JSONObject> onPostExecuteListener) {
        this.onPostExecuteListener = onPostExecuteListener;
        return this;
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        try {
            if (urls.length > 0) {
                final String url = urls[0];
                String responseText = IOUtils.toString(new URL(url).openStream());
                return new JSONObject(responseText);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject books) {
        if (onPostExecuteListener != null) {
            onPostExecuteListener.apply(books);
        }
    }
}
