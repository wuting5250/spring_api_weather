package com.antra.ting.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data // create getter and setter aws toString
@NoArgsConstructor // create constructor
public class WeatherResponse {
    private List<WeatherDetail> consolidated_weather;


}