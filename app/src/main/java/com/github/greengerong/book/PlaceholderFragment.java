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
import com.github.greengerong.book.service.BookDataSource;
import com.github.greengerong.book.adapter.BookListViewAdapter;

import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private final BookDataSource bookDataSource;
    private final AbsListView.OnScrollListener onScrollListener;

    public PlaceholderFragment() {
        bookDataSource = new BookDataSource();
        onScrollListener = new BookImageOnScrollListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Activity context = getActivity();
        final JSONObject books = bookDataSource.getBooks(context);
        final ListView bookList = (ListView) rootView.findViewById(R.id.bookList);
        bookList.setAdapter(new BookListViewAdapter(context, books));
//        bookList.setOnScrollListener(onScrollListener);
        return rootView;
    }

}
