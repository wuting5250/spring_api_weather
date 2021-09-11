package com.antra.ting.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)   //ignore fields don't belong to quote
// class to avoid throw an error

// This is what comes back from the api call.
//{
//        type: "success",
//        value: {
//        id: 10,
//        quote: "Really loving Spring Boot, makes stand alone Spring apps easy."
//        }
//}
@Data // create getter and setter aws toString
@NoArgsConstructor // create constructor
public class Quote {
    private String type;
    private Value value;

//    public Quote() {
//
//    }

//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Value getValue() {
//        return value;
//    }
//
//    public void setValue(Value value) {
//        this.value = value;
//    }
//
//    @Override
//    public String toString() {
//        return "Quote{" +
//                "type='" + type + '\'' +
//                ", value=" + value +
//                '}';
//    }
}
