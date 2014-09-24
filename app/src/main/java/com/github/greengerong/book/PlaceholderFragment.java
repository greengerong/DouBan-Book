package com.github.greengerong.book;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.greengerong.book.adapter.BookListViewAdapter;
import com.github.greengerong.book.service.GetBooksAsyncTask;
import com.github.greengerong.book.utils.ViewHelper;
import com.github.greengerong.book.utils.delegate.Action1;

import org.json.JSONObject;

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

    private BookListViewAdapter bookListViewAdapter;
    private ListView bookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Activity context = getActivity();
        bookList = new ViewHelper(rootView).findViewById(R.id.bookList);

        final Action1<JSONObject> onPostExecuteListener = new Action1<JSONObject>() {
            @Override
            public void apply(JSONObject books) {
                if (books != null) {
                    bookListViewAdapter = new BookListViewAdapter(context)
                            .setDataSource(books);

                    bookList.setAdapter(bookListViewAdapter);
                }
            }
        };
        getBooks(onPostExecuteListener);

        return rootView;
    }

    private void getBooks(Action1<JSONObject> onPostExecuteListener) {
        getBooks(onPostExecuteListener, 0, 20);
    }

    private void getBooks(Action1<JSONObject> onPostExecuteListener, int start, int count) {
        final String api = "https://api.douban.com/v2/book/search?q=%%E7%%BC%%96%%E7%%A8%%8B&start=%s&count=%s";
        final String url = String.format(api, start, count);
        new GetBooksAsyncTask().setOnPostExecuteListener(onPostExecuteListener).execute(url);
    }

    private void updateBooks(JSONObject dataSource) {
        bookListViewAdapter.setDataSource(dataSource).notifyDataSetChanged();
        bookList.invalidateViews();
        bookList.refreshDrawableState();

    }

}
