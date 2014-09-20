package com.github.greengerong.book.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.greengerong.book.R;
import com.github.greengerong.book.service.ImageLoader;
import com.github.greengerong.book.service.ViewHelper;

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
public class BookListViewAdapter extends BaseAdapter {
    private final JSONObject books;
    private Activity context;
    private Bitmap defaultImageBitmap;

    public BookListViewAdapter(Activity context, JSONObject books) {
        this.books = books;
        this.context = context;
        defaultImageBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_default_cover);
    }

    @Override
    public int getCount() {
        return books.optJSONArray("books").length();
    }

    @Override
    public Object getItem(int i) {
        return books.optJSONArray("books").opt(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return view;
        }

        final View bookItemView = LayoutInflater.from(context).inflate(R.layout.book_item, viewGroup, false);
        final ViewHelper viewHelper = new ViewHelper(bookItemView);
        final ImageView image = viewHelper.findViewById(R.id.image);
        final TextView bookName = viewHelper.findViewById(R.id.bookName);
        final RatingBar rating = viewHelper.findViewById(R.id.rating);
        final TextView info = (TextView) bookItemView.findViewById(R.id.info);
        final JSONObject book = (JSONObject) books.optJSONArray("books").opt(i);
        image.setImageBitmap(defaultImageBitmap);
        image.setTag(book.optJSONObject("images").optString("small"));
        new ImageLoader(image).execute(book.optJSONObject("images").optString("small"));
        bookName.setText(book.optString("title"));
        rating.setRating((float) book.optJSONObject("rating").optDouble("average") / 2);
        info.setText(TextUtils.join(" ", new String[]{
                book.optJSONArray("author").optString(0),
                book.optString("publisher"),
                book.optString("pubdate")
        }));
        return bookItemView;
    }
}
