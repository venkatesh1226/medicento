package com.example.basic;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

    private String id, name, image, address, email;

    public Item(){

    }

    protected Item(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
        address = in.readString();
        email = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Item(String id, String name, String image, String address, String email) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(address);
        dest.writeString(email);
    }
}
