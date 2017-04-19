package io.podcst.android.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by eve on 19/04/17.
 */

public class PodcastsResponse implements Parcelable {
    public int statusCode;
    public String message;
    public List<Podcast> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.statusCode);
        dest.writeString(this.message);
        dest.writeTypedList(this.data);
    }

    public PodcastsResponse() {
    }

    protected PodcastsResponse(Parcel in) {
        this.statusCode = in.readInt();
        this.message = in.readString();
        this.data = in.createTypedArrayList(Podcast.CREATOR);
    }

    public static final Creator<PodcastsResponse> CREATOR = new Creator<PodcastsResponse>() {
        @Override
        public PodcastsResponse createFromParcel(Parcel source) {
            return new PodcastsResponse(source);
        }

        @Override
        public PodcastsResponse[] newArray(int size) {
            return new PodcastsResponse[size];
        }
    };
}
