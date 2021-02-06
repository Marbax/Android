package com.Marbax.useract;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            String name = in.readString();
            String lastName = in.readString();

            return new User(name, lastName);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public String Name = "";
    public String LastName = "";

    public User(String name, String lastName) {
        Name = name;
        LastName = lastName;
    }

    private User(Parcel name, Parcel lastName) {
        Name = name.readString();
        LastName = lastName.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(LastName);
    }


}
