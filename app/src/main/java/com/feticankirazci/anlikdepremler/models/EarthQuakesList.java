package com.feticankirazci.anlikdepremler.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Feti on 15.08.2017.
 */

public class EarthQuakesList {
    @SerializedName("data")
    public ArrayList<Data> data;
}
