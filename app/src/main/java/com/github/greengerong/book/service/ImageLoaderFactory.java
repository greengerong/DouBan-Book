package com.github.greengerong.book.service;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.github.greengerong.book.utils.cache.CacheManageFact;
import com.github.greengerong.book.utils.delegate.Action1;
import com.github.greengerong.book.utils.cache.CacheManage;

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
//    private

    public ImageLoaderFactory() {
        cacheManage = CacheManageFact.DEFAULT_CACHE;
    }


    public void load(final ImageView image, final String... urls) {
        if (urls.length > 0 && !TextUtils.isEmpty(urls[0])) {
            final Bitmap bitmap = cacheManage.get(urls[0]);
            if (bitmap == null) {
                final Action1<Bitmap> done = new Action1<Bitmap>() {
                    @Override
                    public void apply(Bitmap bitmap) {
                        if (bitmap != null && image.getTag().equals(image.hashCode())) {
                            image.setImageBitmap(bitmap);
                            cacheManage.put(urls[0], bitmap);
                        }
                    }
                };

                image.setTag(image.hashCode());
                new ImageLoader(done).execute(urls);
                return;
            }

            image.setImageBitmap(bitmap);
        }
    }
}
