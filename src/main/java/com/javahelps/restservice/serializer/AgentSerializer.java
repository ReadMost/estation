package com.javahelps.restservice.serializer;




import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "workfrom",
        "workto"
})
public class AgentSerializer {

    @JsonProperty("workfrom")
    private Time workfrom;
    @JsonProperty("workto")
    private Time workto;


    @JsonProperty("workfrom")
    public Time getFrom() {
        return workfrom;
    }

    @JsonProperty("workfrom")
    public void setFrom(Time workfrom) {
        this.workfrom= workfrom;
    }

    @JsonProperty("workto")
    public Time getTo() {
        return workto;
    }

    @JsonProperty("workto")
    public void setTo(Time workto) {
        this.workto = workto;
    }



}