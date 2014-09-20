package com.github.greengerong.book.service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

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
public class HttpBookDataSource {

    public static final String DOU_BAN_BOOKS_API = "https://api.douban.com/v2/book/search?q=%E8%AE%A1%E7%AE%97%E6%9C%BA";
    private static AsyncHttpClient asyncHttpClient;

    public HttpBookDataSource() {
        asyncHttpClient = new AsyncHttpClient();
    }

    public static void getBooks(JsonHttpResponseHandler responseHandler) {
        asyncHttpClient.get(DOU_BAN_BOOKS_API, responseHandler);
    }
}