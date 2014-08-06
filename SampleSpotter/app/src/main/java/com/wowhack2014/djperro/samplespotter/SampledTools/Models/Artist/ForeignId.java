package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist;

/**
 * Created by backman on 2014-08-05.
 */

import java.util.HashMap;
import java.util.Map;

public class ForeignId {

    private String catalog;
    private String foreignId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}