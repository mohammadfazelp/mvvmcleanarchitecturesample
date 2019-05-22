package com.mvvmcleanarchitecturesample.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SourceEntity implements Parcelable {

    @SerializedName("name")
    private String name;

    protected SourceEntity(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<SourceEntity> CREATOR = new Parcelable.Creator<SourceEntity>() {
        @Override
        public SourceEntity createFromParcel(Parcel in) {
            return new SourceEntity(in);
        }

        @Override
        public SourceEntity[] newArray(int size) {
            return new SourceEntity[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

