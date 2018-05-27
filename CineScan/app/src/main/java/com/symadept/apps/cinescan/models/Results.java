package com.symadept.apps.cinescan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Results implements Parcelable {
    public int vote_count;
    public int id;
    public boolean video;
    public double vote_average;
    public String title;
    public double popularity;
    public String poster_path;
    public String original_language;
    public String original_title;
    public List<Integer> genre_ids;
    public String backdrop_path;
    public boolean adult;
    public String overview;
    public String release_date;

    public static final Creator<Results> CREATOR = new Creator<Results> () {

        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    public Results(Parcel parcel) {
        vote_count = parcel.readInt();
        id = parcel.readInt();
        video = parcel.readByte() == 1;
        vote_average = parcel.readDouble();
        title = parcel.readString();
        popularity = parcel.readDouble();
        poster_path = parcel.readString();
        original_language = parcel.readString();
        original_title = parcel.readString();
        genre_ids = new ArrayList<>();
        parcel.readList(genre_ids, List.class.getClassLoader());
        backdrop_path = parcel.readString();
        adult = parcel.readByte() == 1;
        overview = parcel.readString();
        release_date = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(vote_count);
        dest.writeInt(id);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(vote_average);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeList(genre_ids);
        dest.writeString(backdrop_path);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(release_date);
    }



}
