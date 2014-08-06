package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song;

/**
 * Created by backman on 2014-08-05.
 */
import java.util.HashMap;
import java.util.Map;

public class Track {

    private String catalog;
    private String foreign_id;
    private String id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(String foreign_id) {
        this.foreign_id = foreign_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}