package com.feticankirazci.anlikdepremler.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Feti on 17.08.2017.
 */

public class ImportantData {
    @SerializedName("id")
    public int id;

    @SerializedName("tarih")
    public String tarih;

    @SerializedName("lokasyon")
    public String lokasyon;

    @SerializedName("lat")
    public Double lat;

    @SerializedName("lng")
    public Double lng;

    @SerializedName("hissedilensiddedi")
    public String hissedilensiddedi;

    @SerializedName("siddeti")
    public Double siddeti;

    @SerializedName("cankaybi")
    public String cankaybi;

    @SerializedName("hasarlibina")
    public String hasarlibina;
}
