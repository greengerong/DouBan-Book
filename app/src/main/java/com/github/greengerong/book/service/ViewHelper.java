package com.github.greengerong.book.service;

import android.view.View;

import com.github.greengerong.book.R;

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
public final class ViewHelper {
    private View view;

    public ViewHelper(View view) {
        this.view = view;
    }

    public <T> T findViewById(int id) {
        final View viewById = view.findViewById(id);
        return viewById == null ? null : (T) viewById;
    }

    public <T> T getTag() {
        final Object tag = view.getTag();
        return tag == null ? null : (T) tag;
    }

    public <T> T getTag(int key) {
        final Object tag = view.getTag(key);
        return tag == null ? null : (T) tag;
    }
}
