package com.wowhack2014.djperro.samplespotter.SampledTools;

import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist.WhoSampledArtist;
import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song.WhoSampledTrack;

import retrofit.http.GET;

/**
 * Created by backman on 2014-08-05.
 */


public interface EchoNestService {
    @GET("/artist/search?api_key=WA8XCMX5SD37HII8Y&format=json&results=1&name=radiohead&bucket=id:whosampled")
    WhoSampledArtist whosampledArtistId();


    @GET("/song/search?api_key=WA8XCMX5SD37HII8Y&format=json&results=1&artist=radiohead&title=karma%20police&bucket=id:whosampled&limit=true&bucket=tracks")
    WhoSampledTrack whosampledSongId();
}
