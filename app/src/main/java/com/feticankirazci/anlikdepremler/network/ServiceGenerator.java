package com.feticankirazci.anlikdepremler.network;

import com.feticankirazci.anlikdepremler.models.EarthQuakesList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Feti on 15.08.2017.
 */

public interface ServiceGenerator {
    @GET("/depremler")
    Call<EarthQuakesList> getEarthQuakesList();
}
