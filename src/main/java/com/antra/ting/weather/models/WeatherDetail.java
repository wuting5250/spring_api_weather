package com.antra.ting.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data // create getter and setter aws toString
@NoArgsConstructor // create constructor
public class WeatherDetail {
    private String applicable_date;
    private String the_temp;

}
