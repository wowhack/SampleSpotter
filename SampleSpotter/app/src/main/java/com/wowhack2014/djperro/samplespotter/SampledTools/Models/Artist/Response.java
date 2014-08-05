package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist;

/**
 * Created by backman on 2014-08-05.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    private Status status;
    private List<Artist> artists = new ArrayList<Artist>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}