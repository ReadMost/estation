package com.javahelps.restservice.serializer;


import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
        "user",
        "document_id",
        "lName",
        "fName",
        "carriage",
        "seat",
        "from",
        "to",
        "train",
        "price"
})
public class TicketSerializer {

    @JsonProperty("user")
    private Long user;
    @JsonProperty("document_id")
    private Integer documentId;
    @JsonProperty("lName")
    private String lName;
    @JsonProperty("fName")
    private String fName;
    @JsonProperty("carriage")
    private Integer carriage;
    @JsonProperty("seat")
    private Integer seat;
    @JsonProperty("from")
    private Integer from;
    @JsonProperty("to")
    private Integer to;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("train")
    private Integer train;
    @JsonProperty("price")
    private Integer price;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("user")
    public Long getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(Long user) {
        this.user = user;
    }

    @JsonProperty("document_id")
    public Integer getDocumentId() {
        return documentId;
    }

    @JsonProperty("document_id")
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    @JsonProperty("lName")
    public String getLName() {
        return lName;
    }

    @JsonProperty("lName")
    public void setLName(String lName) {
        this.lName = lName;
    }

    @JsonProperty("fName")
    public String getFName() {
        return fName;
    }

    @JsonProperty("fName")
    public void setFName(String fName) {
        this.fName = fName;
    }

    @JsonProperty("carriage")
    public Integer getCarriage() {
        return carriage;
    }

    @JsonProperty("carriage")
    public void setCarriage(Integer carriage) {
        this.carriage = carriage;
    }

    @JsonProperty("seat")
    public Integer getSeat() {
        return seat;
    }

    @JsonProperty("seat")
    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @JsonProperty("from")
    public Integer getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(Integer from) {
        this.from = from;
    }

    @JsonProperty("to")
    public Integer getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(Integer to) {
        this.to = to;
    }

    @JsonProperty("train")
    public Integer getTrain() {
        return train;
    }

    @JsonProperty("train")
    public void setTrain(Integer train) {
        this.train = train;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("date")
    public Date getDate() { return date; }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date=date;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Document_id=" + documentId +
                ", firstName='" + fName + '\'' +
                ", lastName='" + lName + '\'' +
                ", train='" + train + '\'' +
                ", carriage='" + carriage + '\'' +
                ", seat='" + seat + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", price='" + price +'\'' +
                '}';
    }
//    public Ticket createTicket((int document_id, String lName, String fName, Carriage carriage, Seats seat, Station from, Station to, Train train, User user, Integer price){
//
//    }
}