package com.javahelps.restservice.serializer;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fName",
        "lName"
})
public class User {

    @JsonProperty("fName")
    private String fName;
    @JsonProperty("lName")
    private String lName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fName")
    public String getFName() {
        return fName;
    }

    @JsonProperty("fName")
    public void setFName(String fName) {
        this.fName = fName;
    }

    @JsonProperty("lName")
    public String getLName() {
        return lName;
    }

    @JsonProperty("lName")
    public void setLName(String lName) {
        this.lName = lName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
