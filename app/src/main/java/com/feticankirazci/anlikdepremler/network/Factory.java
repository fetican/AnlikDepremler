package com.feticankirazci.anlikdepremler.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.feticankirazci.anlikdepremler.utils.Constants.BASE_URL;

/**
 * Created by Feti on 15.08.2017.
 */

public class Factory {
    public static ServiceGenerator serviceGenerator;

    public static ServiceGenerator getInstance() {
        if (serviceGenerator == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            serviceGenerator = retrofit.create(ServiceGenerator.class);
            return serviceGenerator;

        } else {
            return serviceGenerator;
        }
    }
}
