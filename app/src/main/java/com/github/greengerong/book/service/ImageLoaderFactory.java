package com.github.greengerong.book.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.github.greengerong.book.R;
import com.github.greengerong.book.utils.cache.ImageCacheManage;
import com.github.greengerong.book.utils.delegate.Action;
import com.github.greengerong.book.utils.delegate.Action1;

import java.io.ByteArrayInputStream;

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

    private final ImageCacheManage imageCacheManage;

    public ImageLoaderFactory() {
        imageCacheManage = new ImageCacheManage();
    }


    public void load(final ImageView image, final String... urls) {
        if (urls.length > 0 && !TextUtils.isEmpty(urls[0])) {
            final byte[] cacheBytes = imageCacheManage.get(urls[0]);

            if (cacheBytes == null) {
                new ImageLoader().setOnPreExecuteListener(new Action() {
                    @Override
                    public void apply() {
                        image.setTag(R.string.image_view_tag_key, urls[0]);
                    }
                }).setOnPostExecuteListener(new Action1<byte[]>() {
                    @Override
                    public void apply(byte[] bytes) {
                        if (bytes != null) {
//                            Log.i("----", urls[0] + "--" + image.getTag(R.string.image_view_tag_key) + " ========" + image.hashCode());
                            if (image.getTag(R.string.image_view_tag_key).equals(urls[0])) {
                                image.setImageBitmap(toBitmap(bytes));
                            }
                            imageCacheManage.put(urls[0], bytes);
                        }
                    }
                }).execute(urls);
                return;
            }

            image.setImageBitmap(toBitmap(cacheBytes));
        }
    }

    private Bitmap toBitmap(byte[] bytes) {
        return BitmapFactory.decodeStream(new ByteArrayInputStream(bytes));
    }
}
