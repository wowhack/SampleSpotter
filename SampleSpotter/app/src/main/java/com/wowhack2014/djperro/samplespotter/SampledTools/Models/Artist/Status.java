package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist;

/**
 * Created by backman on 2014-08-05.
 */
import java.util.HashMap;
import java.util.Map;

public class Status {

    private String version;
    private int code;
    private String message;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}