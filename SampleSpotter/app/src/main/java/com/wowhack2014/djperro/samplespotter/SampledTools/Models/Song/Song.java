package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song;

/**
 * Created by backman on 2014-08-05.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Song {

    private String title;
    private String artistName;
    private List<ArtistForeignId> artistForeignIds = new ArrayList<ArtistForeignId>();
    private List<Track> tracks = new ArrayList<Track>();
    private String artistId;
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<ArtistForeignId> getArtistForeignIds() {
        return artistForeignIds;
    }

    public void setArtistForeignIds(List<ArtistForeignId> artistForeignIds) {
        this.artistForeignIds = artistForeignIds;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}