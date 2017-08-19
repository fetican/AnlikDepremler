package com.feticankirazci.anlikdepremler.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Feti on 15.08.2017.
 */

public class Data {

    @SerializedName("id")
    public int id;

    @SerializedName("tarih2")
    public String tarih2;

    @SerializedName("lokasyon")
    public String lokasyon;

    @SerializedName("lat")
    public Double lat;

    @SerializedName("lng")
    public Double lng;

    @SerializedName("derinlik")
    public Double derinlik;

    @SerializedName("siddeti")
    public Double siddeti;
}
