package com.feticankirazci.anlikdepremler.services;

import com.feticankirazci.anlikdepremler.models.EarthQuakesList;

import retrofit2.Response;

/**
 * Created by Feti on 15.08.2017.
 */

public class ResponseServiceEvent extends ServiceEvent{

    public Response<EarthQuakesList> mResponse;

    public ResponseServiceEvent(Exception exception, Response<EarthQuakesList> response) {
        super(exception);
        this.mResponse = response;
    }

    public ResponseServiceEvent(Exception exception) {
        super(exception);
    }
}
