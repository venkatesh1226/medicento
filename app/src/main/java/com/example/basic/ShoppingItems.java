package com.example.basic;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class ShoppingItems implements Parcelable {
    String shopName, shopId, vaccine, address, id;
    Boolean availability;
    int availabilityNo, price;

    public ShoppingItems() {
    }


    public ShoppingItems(String shopName, String shopId, String vaccine, String address, Boolean availability, int availabilityNo, int price, String id) {
        this.shopName = shopName;
        this.shopId = shopId;
        this.vaccine = vaccine;
        this.address = address;
        this.availability = availability;
        this.availabilityNo = availabilityNo;
        this.price = price;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public int getAvailabilityNo() {
        return availabilityNo;
    }

    public void setAvailabilityNo(int availabilityNo) {
        this.availabilityNo = availabilityNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Override
    public String toString() {
        return "ShoppingItems{" +
                "shopName='" + shopName + '\'' +
                ", shopId='" + shopId + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", availability=" + availability +
                ", availabilityNo=" + availabilityNo +
                ", price=" + price +
                '}';
    }
}
