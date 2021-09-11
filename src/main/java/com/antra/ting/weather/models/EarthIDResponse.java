package com.antra.ting.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data // create getter and setter aws toString
@NoArgsConstructor // create constructor
public class EarthIDResponse {
    private String title;
    private String woeid;

}
