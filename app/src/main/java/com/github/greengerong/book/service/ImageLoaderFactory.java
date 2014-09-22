package com.github.greengerong.book.service;

import android.widget.ImageView;

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
public class ImageLoaderFactory {

    public ImageLoader getImageLoader(ImageView image) {
        return new ImageLoader(image);
    }
}
