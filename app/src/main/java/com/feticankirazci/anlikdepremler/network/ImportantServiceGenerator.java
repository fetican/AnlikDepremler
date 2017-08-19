package com.feticankirazci.anlikdepremler.network;

import com.feticankirazci.anlikdepremler.models.ImportantEarthQuakesList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Feti on 17.08.2017.
 */

public interface ImportantServiceGenerator {
    @GET("/onemliDepremler")
    Call<ImportantEarthQuakesList> getImportantEarthQuakesList();
}
