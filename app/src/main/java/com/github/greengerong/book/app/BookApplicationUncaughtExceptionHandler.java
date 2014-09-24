package com.github.greengerong.book.app;

import android.util.Log;

import com.github.greengerong.book.utils.LogUtils;

import java.io.StringWriter;

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
class BookApplicationUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = BookApplicationUncaughtExceptionHandler.class.getName();

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.e(TAG, LogUtils.getStackTrace(throwable));
    }

}
