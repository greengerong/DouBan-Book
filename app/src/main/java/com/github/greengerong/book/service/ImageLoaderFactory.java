package com.github.greengerong.book.service;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.github.greengerong.book.utils.delegate.Action1;
import com.github.greengerong.book.utils.cache.CacheManage;
import com.github.greengerong.book.utils.cache.DefaultCacheManage;

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

    private final CacheManage cacheManage;

    public ImageLoaderFactory() {
        cacheManage = DefaultCacheManage.getInstance();
    }

    public void load(final ImageView image, final String... urls) {
        if (urls.length > 0 && !TextUtils.isEmpty(urls[0])) {
            final Bitmap bitmap = cacheManage.get(urls[0]);
            if (bitmap == null) {
                final Action1<Bitmap> done = new Action1<Bitmap>() {
                    @Override
                    public void apply(Bitmap bitmap) {
                        image.setImageBitmap(bitmap);
                        cacheManage.put(urls[0], bitmap);
                    }
                };
                new ImageLoader(image, done).execute(urls);
                return;
            }

            image.setImageBitmap(bitmap);
        }
    }
}
