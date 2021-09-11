package com.antra.ting.weather.controller;

import com.antra.ting.weather.WeatherApplication;
import com.antra.ting.weather.configuration.Config;
import com.antra.ting.weather.models.Quote;
import com.antra.ting.weather.models.Value;
import com.antra.ting.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import java.util.concurrent.ExecutorService;

// The controller sets up the path, validates the data, calls a service method
@RestController
public class WeatherController {
    @Autowired
    private String hello;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherService weatherService;

//    @GetMapping("checkTemp/{zipcode}") //set path
//    public String getTemp(@PathVariable String zipcode, HttpServletResponse response) {
//        System.out.println("The weather temp api was called for " + zipcode);
//        validateCode(zipcode, response);
//        return "The weather temp api was called for " + zipcode;
//    }
    private boolean isInteger(String city){

        try {
            Integer.parseInt(city); // only check numeric string
            return true;
        } catch (Exception e)
        {   return false;
        }
    }
    private void validateCity(String city) {

        if (city == null || city.isEmpty() || city.length() == 1 || isInteger(city)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid city");
        }

    }
    @GetMapping("checkTemp")
    public String getTemp(@RequestParam String city) {
                System.out.println("The weather temp api was called for " + city);
        validateCity(city);
//        System.out.println("Injecting dependency.");
//        System.out.println(hello);
//        Quote quote = restTemplate.getForObject(
//                "https://quoters.apps.pcfone.io/api/random", Quote.class); //get the results
//        // and convert class
//        System.out.println(quote);
//        Quote quote2 = new Quote();
//        quote2.setType("success");
//        Value value = new Value();
//        value.setId((long) 5);
//        value.setQuote("Hello let us set quote values");
//        quote2.setValue(value);
//        System.out.println(quote2);
        String temp = weatherService.getCurrentTemp(city);
        return  temp;


    }

    @GetMapping("checkTemp2")
    public String getTemp2(@RequestParam String city) throws InterruptedException {
        validateCity(city);
        return weatherService.getCurrentTemps(city);
    }

}
