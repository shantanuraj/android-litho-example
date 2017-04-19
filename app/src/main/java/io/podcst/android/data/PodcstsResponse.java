package io.podcst.android.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by eve on 19/04/17.
 */

public class PodcstsResponse implements Parcelable {
    public int statusCode;
    public String message;
    public List<Podcst> data;

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

    public PodcstsResponse() {
    }

    protected PodcstsResponse(Parcel in) {
        this.statusCode = in.readInt();
        this.message = in.readString();
        this.data = in.createTypedArrayList(Podcst.CREATOR);
    }

    public static final Creator<PodcstsResponse> CREATOR = new Creator<PodcstsResponse>() {
        @Override
        public PodcstsResponse createFromParcel(Parcel source) {
            return new PodcstsResponse(source);
        }

        @Override
        public PodcstsResponse[] newArray(int size) {
            return new PodcstsResponse[size];
        }
    };
}
