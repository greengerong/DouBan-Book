package com.github.greengerong.book;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.greengerong.book.adapter.BookListViewAdapter;
import com.github.greengerong.book.domain.BookSearchResult;
import com.github.greengerong.book.service.GetBooksAsyncTask;
import com.github.greengerong.book.utils.ViewHelper;
import com.github.greengerong.book.utils.delegate.Action;
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
public class BookListFragment extends Fragment {

    public static final int INIT_START_INDEX = 0;
    public static final int PAGE_SIZE = 20;
    private BookListViewAdapter bookListViewAdapter;
    private ListView bookList;
    public static final String BOOK_API_URL = "https://api.douban.com/v2/book/search?q=%%E7%%BC%%96%%E7%%A8%%8B&start=%s&count=%s";
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Activity context = getActivity();
        final ViewHelper viewHelper = new ViewHelper(rootView);
        bookList = viewHelper.findViewById(R.id.bookList);
        bookListViewAdapter = new BookListViewAdapter(context);
        bookList.setAdapter(bookListViewAdapter);
        swipeRefreshLayout = viewHelper.findViewById(R.id.bookListPanel);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new BookListOnRefreshListener());
        doRefreshBookList();
        return rootView;
    }

    private void doRefreshBookList() {
        loadBooks(INIT_START_INDEX);
    }

    private void loadBooks(int start) {
        new GetBooksAsyncTask()
                .setOnPreExecuteListener(new Action() {
                    @Override
                    public void apply() {
                        if (!swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(true);
                        }
                    }
                })
                .setOnPostExecuteListener(new Action1<BookSearchResult>() {
                    @Override
                    public void apply(BookSearchResult bookSearchResult) {
                        if (bookSearchResult != null) {
                            if (swipeRefreshLayout.isRefreshing()) {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                            bookListViewAdapter.clear();
                            bookListViewAdapter.addAll(bookSearchResult.getBooks());
                        }
                    }
                }).execute(String.format(BOOK_API_URL, start, PAGE_SIZE));
    }

    private class BookListOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            doRefreshBookList();
        }
    }
}
