package com.github.greengerong.book.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.github.greengerong.book.utils.delegate.Action1;

import java.io.IOException;
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
    private ImageView imageView;
    private Action1<Bitmap> done;

    public ImageLoader(ImageView imageView, Action1<Bitmap> done) {
        this.imageView = imageView;
        this.done = done;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        try {
            return BitmapFactory.decodeStream(new URL(urls[0]).openStream());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            done.apply(bitmap);
        }
    }
}
