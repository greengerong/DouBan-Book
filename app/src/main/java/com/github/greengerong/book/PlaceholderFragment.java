package com.github.greengerong.book;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.greengerong.book.service.GetBooksAsyncTask;
import com.github.greengerong.book.utils.ViewHelper;

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
public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Activity context = getActivity();
        final ListView bookList = new ViewHelper(rootView).findViewById(R.id.bookList);
        new GetBooksAsyncTask(bookList, context).execute("https://api.douban.com/v2/book/search?q=%E7%BC%96%E7%A8%8B");
        return rootView;
    }

}
