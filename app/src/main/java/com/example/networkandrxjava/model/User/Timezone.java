
package com.example.networkandrxjava.model.User;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timezone implements Parcelable
{

    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<Timezone> CREATOR = new Creator<Timezone>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Timezone createFromParcel(Parcel in) {
            return new Timezone(in);
        }

        public Timezone[] newArray(int size) {
            return (new Timezone[size]);
        }

    }
    ;

    protected Timezone(Parcel in) {
        this.offset = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Timezone() {
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(offset);
        dest.writeValue(description);
    }

    public int describeContents() {
        return  0;
    }

}
