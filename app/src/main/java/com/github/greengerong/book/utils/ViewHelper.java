package com.github.greengerong.book.utils;

import android.view.View;

import java.lang.ref.WeakReference;

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
    private WeakReference<View> view;

    public ViewHelper(View view) {
        this.view = new WeakReference<View>(view);
    }

    public <T> T findViewById(int id) {
        final View viewById = checkIsActive(view).get().findViewById(id);
        return viewById == null ? null : (T) viewById;
    }

    public <T> T getTag() {
        final Object tag = checkIsActive(view).get().getTag();
        return tag == null ? null : (T) tag;
    }

    public <T> T getTag(int key) {
        final Object tag = checkIsActive(view).get().getTag(key);
        return tag == null ? null : (T) tag;
    }

    private WeakReference<View> checkIsActive(WeakReference<View> weakReference) {
        if (weakReference.isEnqueued()) {
            throw new RuntimeException("This reference was enqueued!");
        }

        return weakReference;
    }
}
