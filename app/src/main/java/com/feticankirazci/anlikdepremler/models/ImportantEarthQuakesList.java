package com.feticankirazci.anlikdepremler.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Feti on 17.08.2017.
 */

public class ImportantEarthQuakesList {
    @SerializedName("data")
    public ArrayList<ImportantData> data;
}
