package com.example.user.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class modelMovie implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("dates")
    @Expose
    private Date dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Creator<modelMovie> CREATOR = new Creator<modelMovie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public modelMovie createFromParcel(Parcel in) {
            return new modelMovie(in);
        }

        public modelMovie[] newArray(int size) {
            return (new modelMovie[size]);
        }

    }
            ;

    protected modelMovie(Parcel in) {
        in.readList(this.results, (Result.class.getClassLoader()));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dates = ((Date) in.readValue((Date.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public modelMovie() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(dates);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return  0;
    }


}