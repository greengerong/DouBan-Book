package com.github.greengerong.book;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.github.greengerong.book.adapter.BookImageOnScrollListener;
import com.github.greengerong.book.service.GetBooksAsyncTask;
import com.github.greengerong.book.service.ViewHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private final AbsListView.OnScrollListener onScrollListener;

    public PlaceholderFragment() {
        onScrollListener = new BookImageOnScrollListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Activity context = getActivity();
        final ListView bookList = new ViewHelper(rootView).findViewById(R.id.bookList);
        new GetBooksAsyncTask(bookList, context).execute("https://api.douban.com/v2/book/search?q=%E7%BC%96%E7%A8%8B");
//        bookList.setOnScrollListener(onScrollListener);
        return rootView;
    }

}
