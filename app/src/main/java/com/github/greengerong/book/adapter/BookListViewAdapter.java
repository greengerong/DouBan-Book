package com.github.greengerong.book.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.greengerong.book.R;
import com.github.greengerong.book.service.ImageLoaderFactory;
import com.github.greengerong.book.utils.ViewHelper;

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
    private JSONObject dataSource;
    private final ImageLoaderFactory imageLoaderFactory;
    private Activity context;

    public BookListViewAdapter(Activity context) {
        this.context = context;
        imageLoaderFactory = new ImageLoaderFactory();
    }

    @Override
    public int getCount() {
        return dataSource.optJSONArray("books").length();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.optJSONArray("books").opt(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View bookItemView, ViewGroup viewGroup) {

        if (bookItemView == null) {
            bookItemView = LayoutInflater.from(context).inflate(R.layout.book_item, viewGroup, false);
            final ViewHelper viewHelper = new ViewHelper(bookItemView);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.bookName = viewHelper.findViewById(R.id.bookName);
            viewHolder.rating = viewHelper.findViewById(R.id.rating);
            viewHolder.image = viewHelper.findViewById(R.id.image);
            viewHolder.info = viewHelper.findViewById(R.id.info);

            bookItemView.setTag(viewHolder);
        }

        ViewHolder viewHolder = new ViewHelper(bookItemView).getTag();

        final JSONObject book = (JSONObject) dataSource.optJSONArray("books").opt(i);
        imageLoaderFactory.load(viewHolder.image, book.optJSONObject("images").optString("small"));
        viewHolder.bookName.setText(book.optString("title"));
        viewHolder.rating.setRating((float) book.optJSONObject("rating").optDouble("average") / 2);
        viewHolder.info.setText(TextUtils.join(" ", new String[]{
                book.optJSONArray("author").optString(0),
                book.optString("publisher"),
                book.optString("pubdate")
        }));

        return bookItemView;
    }

    public BookListViewAdapter setDataSource(JSONObject dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    private static class ViewHolder {
        TextView bookName;
        RatingBar rating;
        ImageView image;
        TextView info;
    }
}

