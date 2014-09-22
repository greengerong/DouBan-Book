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
    public View getView(int i, View bookItemView, ViewGroup viewGroup) {

        if (bookItemView == null) {
            bookItemView = LayoutInflater.from(context).inflate(R.layout.book_item, viewGroup, false);
            final ViewHelper viewHelper = new ViewHelper(bookItemView);
            ViewHolder viewHolder = new ViewHolder(viewHelper.<TextView>findViewById(R.id.bookName),
                    viewHelper.<RatingBar>findViewById(R.id.rating),
                    viewHelper.<ImageView>findViewById(R.id.image),
                    viewHelper.<TextView>findViewById(R.id.info));
            bookItemView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) bookItemView.getTag();

        final JSONObject book = (JSONObject) books.optJSONArray("books").opt(i);
        viewHolder.getImage().setImageBitmap(defaultImageBitmap);
        viewHolder.getImage().setTag(book.optJSONObject("images").optString("small"));
        new ImageLoader(viewHolder.getImage()).execute(book.optJSONObject("images").optString("small"));
        viewHolder.getBookName().setText(book.optString("title"));
        viewHolder.getRating().setRating((float) book.optJSONObject("rating").optDouble("average") / 2);
        viewHolder.getInfo().setText(TextUtils.join(" ", new String[]{
                book.optJSONArray("author").optString(0),
                book.optString("publisher"),
                book.optString("pubdate")
        }));

        return bookItemView;
    }

    private static class ViewHolder {
        private TextView bookName;
        private RatingBar rating;
        private ImageView image;
        private TextView info;

        public ViewHolder(TextView bookName, RatingBar rating, ImageView image, TextView info) {

            this.bookName = bookName;
            this.rating = rating;
            this.image = image;
            this.info = info;
        }

        public TextView getBookName() {
            return bookName;
        }

        public RatingBar getRating() {
            return rating;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getInfo() {
            return info;
        }
    }
}

