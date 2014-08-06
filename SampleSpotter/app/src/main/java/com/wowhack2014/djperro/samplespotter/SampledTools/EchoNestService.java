package com.wowhack2014.djperro.samplespotter.SampledTools;

import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist.WhoSampledArtist;
import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song.WhoSampledTrack;

import retrofit.http.EncodedQuery;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by backman on 2014-08-05.
 */


public interface EchoNestService {
    @GET("/artist/search?api_key=WA8XCMX5SD37HII8Y&format=json&results=1&name=radiohead&bucket=id:whosampled")
    WhoSampledArtist whosampledArtistId();

    @GET("/song/search")
    WhoSampledTrack whosampledSongId(@EncodedQuery("api_key") String api_key);
}
