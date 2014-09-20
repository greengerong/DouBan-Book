package com.github.greengerong.book.adapter;

import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.github.greengerong.book.R;
import com.github.greengerong.book.service.ImageLoader;

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
            final ImageView image = (ImageView) view.findViewById(R.id.image);
            if (image.getTag() != null && !TextUtils.isEmpty((String) image.getTag())) {
                new ImageLoader(image).execute((String) image.getTag());
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
