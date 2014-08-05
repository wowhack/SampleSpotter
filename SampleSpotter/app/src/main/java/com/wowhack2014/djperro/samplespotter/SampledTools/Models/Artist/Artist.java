package com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist;

/**
 * Created by backman on 2014-08-05.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Artist {

    private List<ForeignId> foreignIds = new ArrayList<ForeignId>();
    private String id;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<ForeignId> getForeignIds() {
        return foreignIds;
    }

    public void setForeignIds(List<ForeignId> foreignIds) {
        this.foreignIds = foreignIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
