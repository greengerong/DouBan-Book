package com.github.greengerong.book.service;

import android.graphics.BitmapFactory;
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
    private Action1<byte[]> onPostExecuteLister;
    private Action onPreExecuteLister;

    public ImageLoader setonPostExecuteLister(Action1<byte[]> onPostExecuteLister) {
        this.onPostExecuteLister = onPostExecuteLister;
        return this;
    }

    public ImageLoader setonPreExecuteLister(Action onPreExecuteLister) {
        this.onPreExecuteLister = onPreExecuteLister;
        return this;
    }


    @Override
    protected void onPreExecute() {
        if (onPreExecuteLister != null) {
            onPreExecuteLister.apply();
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
        if (onPostExecuteLister != null) {
            onPostExecuteLister.apply(bytes);
        }
    }
}
