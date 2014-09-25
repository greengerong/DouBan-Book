package com.github.greengerong.book.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.greengerong.book.R;
import com.github.greengerong.book.domain.Book;
import com.github.greengerong.book.domain.BookSearchResult;
import com.github.greengerong.book.service.ImageLoaderFactory;
import com.github.greengerong.book.utils.ViewHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
    private Context context;
    private ArrayList<Book> books;

    public BookListViewAdapter(Context context) {
        this.context = context;
        books = new ArrayList<Book>();
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
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

        final Book book = getItem(i);
//        imageLoaderFactory.load(viewHolder.image, book.getImages().getSmall());
        viewHolder.bookName.setText(book.getTitle());
        viewHolder.rating.setRating(book.getRating().getAverage() / 2);
        viewHolder.info.setText(book.getInformation());
        Picasso.with(context).load(book.getImages().getSmall()).into(viewHolder.image);
        return bookItemView;
    }

    public void clear() {
        books.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Book> books) {
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    public ArrayList<Book> getAll() {
        return books;
    }

    private static class ViewHolder {
        TextView bookName;
        RatingBar rating;
        ImageView image;
        TextView info;
    }
}

