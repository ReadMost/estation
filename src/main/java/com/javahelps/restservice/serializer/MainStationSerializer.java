package com.javahelps.restservice.serializer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.javahelps.restservice.entity.MainStation;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "longitude",
        "latitude"
})
public class MainStationSerializer {
    @JsonProperty("name")
    private String name;
    @JsonProperty("longitude")
    private Long longitude;
    @JsonProperty("latitude")
    private Long latitude;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("longitude")
    public Long getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public Long getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public MainStation createMainStation(){
        return new MainStation(this.name, this.longitude, this.latitude);
    }
}
