package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song;

/**
 * Created by backman on 2014-08-05.
 */
import java.util.HashMap;
import java.util.Map;

public class WhoSampledTrack {

    private Response response;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}