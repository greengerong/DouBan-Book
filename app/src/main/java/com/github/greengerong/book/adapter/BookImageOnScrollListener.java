package com.github.greengerong.book.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.github.greengerong.book.R;
import com.github.greengerong.book.service.ImageLoader;
import com.github.greengerong.book.service.ViewHelper;
import com.github.greengerong.book.utils.delegate.Action1;

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
public class BookImageOnScrollListener implements AbsListView.OnScrollListener {
    private int startIndex;
    private int endIndex;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            final ImageView image = new ViewHelper(view).findViewById(R.id.image);
            if (image.getTag() != null && !TextUtils.isEmpty((String) image.getTag())) {
                new ImageLoader(image, new Action1<Bitmap>() {
                    @Override
                    public void apply(Bitmap input) {
                    }
                }).execute((String) image.getTag());
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.startIndex = firstVisibleItem;
        this.endIndex = (firstVisibleItem + visibleItemCount) >
                totalItemCount ? totalItemCount - 1 : (firstVisibleItem + visibleItemCount);
    }
}
