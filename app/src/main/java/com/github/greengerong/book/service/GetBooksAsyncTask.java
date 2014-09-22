package com.github.greengerong.book.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ListView;

import com.github.greengerong.book.adapter.BookListViewAdapter;

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
    private final ListView bookList;
    private final Activity context;

    public GetBooksAsyncTask(ListView bookList, Activity context) {
        this.bookList = bookList;
        this.context = context;
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
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject books) {
        if (books != null) {
            bookList.setAdapter(new BookListViewAdapter(context, books));
        }
    }
}
