package com.github.greengerong.book.domain;

import android.os.Parcel;
import android.os.Parcelable;

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

public class Rating implements Parcelable {

    private Integer max;

    private Integer numRaters;

    private float average;

    private Integer min;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(Integer numRaters) {
        this.numRaters = numRaters;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.max);
        dest.writeValue(this.numRaters);
        dest.writeFloat(this.average);
        dest.writeValue(this.min);
    }

    public Rating() {
    }

    private Rating(Parcel in) {
        this.max = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numRaters = (Integer) in.readValue(Integer.class.getClassLoader());
        this.average = in.readFloat();
        this.min = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}
