package com.github.greengerong.book.service;

import android.os.AsyncTask;
import android.util.Log;

import com.github.greengerong.book.domain.BookSearchResult;
import com.github.greengerong.book.utils.delegate.Action;
import com.github.greengerong.book.utils.delegate.Action1;
import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

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
public class GetBooksAsyncTask extends AsyncTask<String, Void, BookSearchResult> {
    private static final String TAG = GetBooksAsyncTask.class.getName();
    private Action1<BookSearchResult> onPostExecuteListener;
    private Action onPreExecuteListener;

    public GetBooksAsyncTask setOnPostExecuteListener(Action1<BookSearchResult> onPostExecuteListener) {
        this.onPostExecuteListener = onPostExecuteListener;
        return this;
    }

    public GetBooksAsyncTask setOnPreExecuteListener(Action onPreExecuteListener) {
        this.onPreExecuteListener = onPreExecuteListener;
        return this;
    }

    @Override
    protected BookSearchResult doInBackground(String... urls) {
        try {
            if (urls.length > 0) {
                final String url = urls[0];
                String jsonResult = IOUtils.toString(new URL(url).openStream());
                return new Gson().fromJson(jsonResult, BookSearchResult.class);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(BookSearchResult books) {
        if (onPostExecuteListener != null) {
            onPostExecuteListener.apply(books);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onPreExecuteListener != null) {
            onPreExecuteListener.apply();
        }
    }
}
