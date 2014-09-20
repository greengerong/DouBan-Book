package com.github.greengerong.book.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

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
    private ImageView imageView;

    public ImageLoader(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        if (urls.length > 0 && !TextUtils.isEmpty(urls[0])) {
            try {
                return BitmapFactory.decodeStream(new URL(urls[0]).openStream());
            } catch (IOException e) {
                Log.e(ImageLoader.class.getName(), e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
