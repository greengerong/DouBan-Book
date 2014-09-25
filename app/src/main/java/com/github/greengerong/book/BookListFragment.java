package com.github.greengerong.book;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.github.greengerong.book.adapter.BookListViewAdapter;
import com.github.greengerong.book.domain.Book;
import com.github.greengerong.book.domain.BookSearchResult;
import com.github.greengerong.book.service.BookService;
import com.github.greengerong.book.utils.ViewHelper;
import com.github.greengerong.book.utils.delegate.Action;
import com.github.greengerong.book.utils.delegate.Action1;

import java.io.Serializable;
import java.security.KeyStore;
import java.util.ArrayList;

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

    public static final String DATA_SOURCE = "dataSource";
    public static final String FIRST_VISIBLE_POSITION = "firstVisiblePosition";
    public static final String LOAD_COMPLETED = "loadCompleted";
    private final BookService bookService;
    private BookListViewAdapter bookListViewAdapter;
    private AbsListView bookList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BookListOnScrollListener onScrollListener;

    public BookListFragment() {
        bookService = new BookService();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ViewHelper viewHelper = new ViewHelper(rootView);
        swipeRefreshLayout = viewHelper.findViewById(R.id.bookListPanel);
        bookList = viewHelper.findViewById(R.id.bookList);
        final View loadMoreFooter = viewHelper.findViewById(R.id.loadMoreFooter);
        loadMoreFooter.setVisibility(View.GONE);
        setupBookList(loadMoreFooter);
        setupSwipeRefreshLayout();

        if (savedInstanceState != null) {
            onRestoredBundle(savedInstanceState);
        } else {
            doRefreshBookList();
        }
        return rootView;
    }

    private void onRestoredBundle(Bundle savedInstanceState) {
        final ArrayList<Book> books = (ArrayList<Book>) savedInstanceState.getSerializable(DATA_SOURCE);
        bookListViewAdapter.addAll(books);
        bookList.smoothScrollToPosition(savedInstanceState.getInt(FIRST_VISIBLE_POSITION));
        onScrollListener.setLoadCompleted(savedInstanceState.getBoolean(LOAD_COMPLETED));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DATA_SOURCE, bookListViewAdapter.getAll());
        outState.putInt(FIRST_VISIBLE_POSITION, bookList.getFirstVisiblePosition());
        outState.putBoolean(LOAD_COMPLETED, onScrollListener.isLoadCompleted());
    }

    private void setupBookList(View loadMoreFooter) {
        bookListViewAdapter = new BookListViewAdapter(bookList.getContext());
        bookList.setAdapter(bookListViewAdapter);
        onScrollListener = new BookListOnScrollListener(bookList, bookListViewAdapter, loadMoreFooter);
        bookList.setOnScrollListener(onScrollListener);
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
