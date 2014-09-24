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
import com.github.greengerong.book.service.BookService;
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
public class BookListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private final BookService bookService;
    private BookListViewAdapter bookListViewAdapter;
    private ListView bookList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public BookListFragment() {
        bookService = new BookService();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Activity context = getActivity();
        final ViewHelper viewHelper = new ViewHelper(rootView);
        swipeRefreshLayout = viewHelper.findViewById(R.id.bookListPanel);
        bookList = viewHelper.findViewById(R.id.bookList);

        setupBookList();
        setupSwipeRefreshLayout();
        
        doRefreshBookList();
        return rootView;
    }

    private void setupBookList() {
        bookListViewAdapter = new BookListViewAdapter(bookList.getContext());
        bookList.setAdapter(bookListViewAdapter);
        bookList.setOnScrollListener(new BookListOnScrollListener(bookList, bookListViewAdapter));
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        doRefreshBookList();
    }

    private void doRefreshBookList() {
        bookService.getBookList(new Action() {
            @Override
            public void apply() {
                if (!swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }
        }, new Action1<BookSearchResult>() {
            @Override
            public void apply(BookSearchResult bookSearchResult) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }

                if (bookSearchResult != null) {
                    bookListViewAdapter.clear();
                    bookListViewAdapter.addAll(bookSearchResult.getBooks());
                }
            }
        });
    }
}
