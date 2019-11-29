package com.javahelps.restservice.serializer;


import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.javahelps.restservice.entity.Station;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "station",
        "name"
})
public class Temp {

    @JsonProperty("name")
    private String name;

    @JsonProperty("depTime")
    private Time depTime;

    @JsonProperty("arrTime")
    private Time arrTime;

    @JsonProperty("dayNum")
    private int dayNum;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("depTime")
    public Time getDepTime() {
        return depTime;
    }

    @JsonProperty("depTime")
    public void setDepTime(Time depTime) {
        this.depTime = depTime;
    }

    @JsonProperty("arrTime")
    public Time getArrTime() {
        return arrTime;
    }

    @JsonProperty("arrTime")
    public void setArrTime(Time arrTime) {
        this.arrTime = arrTime;
    }

    @JsonProperty("dayNum")
    public int getDayNum() {
        return dayNum;
    }

    @JsonProperty("dayNum")
    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
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