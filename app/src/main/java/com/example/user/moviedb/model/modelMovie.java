package com.example.user.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class modelMovie implements Parcelable
{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Result> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public List<Result> getMovies() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void setMovies(List<Result> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeTypedList(this.results);
        dest.writeInt(this.totalResults);
        dest.writeInt(this.totalPages);
    }

    public modelMovie() {
    }

    protected modelMovie(Parcel in) {
        this.page = in.readInt();
        this.results = in.createTypedArrayList(Result.CREATOR);
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
    }

    public static final Parcelable.Creator<modelMovie> CREATOR = new Parcelable.Creator<modelMovie>() {
        @Override
        public modelMovie createFromParcel(Parcel source) {
            return new modelMovie(source);
        }

        @Override
        public modelMovie[] newArray(int size) {
            return new modelMovie[size];
        }
    };
}