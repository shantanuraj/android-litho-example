package io.podcst.android.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eve on 19/04/17.
 */

public class Podcst implements Parcelable {
    public String id;
    public String url;
    public String title;
    public String description;
    public String cover;
    public String category;
    public String media;
    public String language;
    public String author;
    public String thumbnail;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.cover);
        dest.writeString(this.category);
        dest.writeString(this.media);
        dest.writeString(this.language);
        dest.writeString(this.author);
        dest.writeString(this.thumbnail);
    }

    public Podcst() {
    }

    protected Podcst(Parcel in) {
        this.id = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.cover = in.readString();
        this.category = in.readString();
        this.media = in.readString();
        this.language = in.readString();
        this.author = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<Podcst> CREATOR = new Parcelable.Creator<Podcst>() {
        @Override
        public Podcst createFromParcel(Parcel source) {
            return new Podcst(source);
        }

        @Override
        public Podcst[] newArray(int size) {
            return new Podcst[size];
        }
    };
}
