package com.github.greengerong.book.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.github.greengerong.book.utils.delegate.Action1;

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
public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = ImageLoader.class.getName();
    private Action1<Bitmap> done;

    public ImageLoader(Action1<Bitmap> done) {
        this.done = done;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        InputStream inputStream = null;
        try {
            inputStream = new URL(urls[0]).openStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (done != null) {
            done.apply(bitmap);
        }
    }
}
