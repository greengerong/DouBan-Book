package com.github.greengerong.book;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import com.github.greengerong.book.adapter.BookListViewAdapter;
import com.github.greengerong.book.domain.BookSearchResult;
import com.github.greengerong.book.service.BookService;
import com.github.greengerong.book.utils.ToastUtils;
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
class BookListOnScrollListener implements AbsListView.OnScrollListener {
    private static final String TAG = BookListOnScrollListener.class.getName();
    private final BookService bookService;
    public boolean loadCompleted;
    public boolean isLoading;
    private AbsListView bookList;
    private BookListViewAdapter bookListViewAdapter;
    private View loadMoreFooter;

    public BookListOnScrollListener(AbsListView bookList, BookListViewAdapter bookListViewAdapter, View loadMoreFooter) {
        this.loadMoreFooter = loadMoreFooter;
        this.bookList = bookList;
        this.bookListViewAdapter = bookListViewAdapter;
        bookService = new BookService();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstItem, int visibleItemCount, int totalItemCount) {
        if (shouldLoadMoreBook(firstItem, visibleItemCount, totalItemCount)) {
            loadMoreBook();
        }
    }

    private boolean shouldLoadMoreBook(int firstItem, int visibleItemCount, int totalItemCount) {
        return totalItemCount > 0 && !isLoading && !loadCompleted && isScrollToBottom(firstItem, visibleItemCount, totalItemCount);
    }

    private boolean isScrollToBottom(int firstItem, int visibleItemCount, int totalItemCount) {
        return firstItem + visibleItemCount == totalItemCount;
    }

    private void loadMoreBook() {
        bookService.getBookList(new Action() {
            @Override
            public void apply() {
                isLoading = true;
                loadMoreFooter.setVisibility(View.VISIBLE);
            }

        }, new Action1<BookSearchResult>() {
            @Override
            public void apply(BookSearchResult bookSearchResult) {
                isLoading = false;
                loadMoreFooter.setVisibility(View.GONE);
                if (bookSearchResult != null) {
                    loadCompleted = bookSearchResult.isLoadCompleted();
                    bookListViewAdapter.addAll(bookSearchResult.getBooks());
                    if (loadCompleted) {
                        ToastUtils.showToast(bookList.getContext(), String.format("Book load completed(%d)!", bookSearchResult.getTotal()));
                    }
                    Log.d(TAG, String.format("loaded book(%d)", bookSearchResult.getStart() + bookSearchResult.getCount()));
                }
            }
        }, bookList.getCount());
    }
}
