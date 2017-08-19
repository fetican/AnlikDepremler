package com.feticankirazci.anlikdepremler.services;

import com.feticankirazci.anlikdepremler.models.ImportantEarthQuakesList;

import retrofit2.Response;

/**
 * Created by Feti on 17.08.2017.
 */

public class ImportantServiceEvent extends ServiceEvent{

    public Response<ImportantEarthQuakesList> mImportantResponse;

    public ImportantServiceEvent(Exception exception, Response<ImportantEarthQuakesList> response) {
        super(exception);
        this.mImportantResponse = response;
    }

    public ImportantServiceEvent(Exception exception) {
        super(exception);
    }
}
