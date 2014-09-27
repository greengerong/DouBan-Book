package com.github.greengerong.book.service;

import android.util.Log;

import com.github.greengerong.book.domain.BookSearchResult;
import com.github.greengerong.book.utils.LogUtils;
import com.github.greengerong.book.utils.delegate.Action;
import com.github.greengerong.book.utils.delegate.Action1;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
public class BookService {
    private static final String BOOK_API_URL = "https://api.douban.com/v2/book/search?q=%s&start=%s&count=%s";
    private static final String BOOK_TAG = "大数据";
    private static final int PAGE_SIZE = 20;
    private static final String TAG = BookService.class.getName();

    public static String getBookApiUrl(int start) {
        try {
            return String.format(BOOK_API_URL, URLEncoder.encode(BOOK_TAG, "UTF-8"), start, PAGE_SIZE);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, LogUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    public void getBookList(Action onPreExecuteListener, Action1<BookSearchResult> onPostExecuteListener, int start) {
        new GetBooksAsyncTask()
                .setOnPreExecuteListener(onPreExecuteListener)
                .setOnPostExecuteListener(onPostExecuteListener).execute(getBookApiUrl(start));
    }

    public void getBookList(Action onPreExecuteListener, Action1<BookSearchResult> onPostExecuteListener) {
        getBookList(onPreExecuteListener, onPostExecuteListener, 0);
    }
}
