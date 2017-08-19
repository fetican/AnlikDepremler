package com.feticankirazci.anlikdepremler.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.feticankirazci.anlikdepremler.utils.Constants.BASE_URL;

/**
 * Created by Feti on 17.08.2017.
 */

public class ImportantFactory {

    public static ImportantServiceGenerator importantServiceGenerator;

    public static ImportantServiceGenerator getInstance(){
        if (importantServiceGenerator == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            importantServiceGenerator = retrofit.create(ImportantServiceGenerator.class);
            return importantServiceGenerator;
        }else {
            return importantServiceGenerator;
        }
    }
}
