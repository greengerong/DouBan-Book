package com.github.greengerong.book.service;

import android.os.AsyncTask;
import android.util.Log;

import com.github.greengerong.book.utils.delegate.Action;
import com.github.greengerong.book.utils.delegate.Action1;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
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
public class ImageLoader extends AsyncTask<String, Void, byte[]> {
    private static final String TAG = ImageLoader.class.getName();
    private Action1<byte[]> onPostExecuteListener;
    private Action onPreExecuteListener;

    public ImageLoader setOnPostExecuteListener(Action1<byte[]> onPostExecuteListener) {
        this.onPostExecuteListener = onPostExecuteListener;
        return this;
    }

    public ImageLoader setOnPreExecuteListener(Action onPreExecuteListener) {
        this.onPreExecuteListener = onPreExecuteListener;
        return this;
    }


    @Override
    protected void onPreExecute() {
        if (onPreExecuteListener != null) {
            onPreExecuteListener.apply();
        }
    }

    @Override
    protected byte[] doInBackground(String... urls) {
        InputStream inputStream = null;
        try {
            inputStream = new URL(urls[0]).openStream();
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        if (onPostExecuteListener != null) {
            onPostExecuteListener.apply(bytes);
        }
    }
}
