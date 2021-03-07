package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "hero")
public class Hero implements Parcelable {

    /****
     * Global variables
     */
    @NonNull
    @PrimaryKey
    private int id;

    private long userId;

    private String title;
    private String body;

    @Ignore
    public Hero() {
    }

    public Hero(int id, long userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    protected Hero(Parcel in) {
        id = in.readInt();
        userId = in.readLong();
        title = in.readString();
        body = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /***
     * creating a database from the instance of Hero Class attributes
     * @return
     */
    public static Creator<Hero> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(userId);
        dest.writeString(title);
        dest.writeString(body);
    }
}