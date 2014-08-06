package com.wowhack2014.djperro.samplespotter.SampledTools;

import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist.WhoSampledArtist;

import retrofit.RestAdapter;


/**
 * Created by backman on 2014-08-05.
 */
public class EchoNest {

    private final static String API_KEY = "WA8XCMX5SD37HII8Y";
    private final static String FORMAT = "JSON";

    private EchoNestService service;


    public EchoNest() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://developer.echonest.com/api/v4")
                .build();

        service = restAdapter.create(EchoNestService.class);
    }

    public EchoNestService getService() {
        return service;
    }

}