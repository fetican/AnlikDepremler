package com.feticankirazci.anlikdepremler.models;

/**
 * Created by Feti on 17.08.2017.
 */

public class Contacts {
    private String mName;
    private String mNumber;

    public Contacts(String mName, String mNumber) {
        this.mName = mName;
        this.mNumber = mNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "mName='" + mName + '\'' +
                ", mNumber='" + mNumber + '\'' +
                '}';
    }
}
