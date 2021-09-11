package com.antra.ting.weather.service;
import com.antra.ting.weather.Singleton;
import com.antra.ting.weather.StaticExample;
import com.antra.ting.weather.dao.HttpDaoImpl;
import com.antra.ting.weather.models.EarthIDResponse;
import com.antra.ting.weather.models.WeatherDetail;
import com.antra.ting.weather.models.WeatherResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

// The service does the business logic, calls the dao layer for data
@Service
@NoArgsConstructor
public class WeatherService {
    
    @Autowired
    private ExecutorService executorService;
    
    @Autowired
    private HttpDaoImpl httpDao;
    
    
    private Random random = new Random();

    public String getCurrentTemp(String city){

        // TODO: Call metweather api to get temperature;
        System.out.println(findEarthID(city));
        String woeid = findEarthID(city);
        String temp = null;
        temp = findTemp(woeid);

        return "Current temp for " + city + " is: " + temp;

    }

    public String getCurrentTemps(String city) throws InterruptedException {

        // TODO: Call metweather api to get temperature;
        int range = 50;
        int min = 30;
        List<Callable<Integer>> calls = new ArrayList<>();
        for (int i = 0 ; i < 20; i++ ){
            calls.add(() -> random.nextInt(range) + min);
        }

        List<Future<Integer>> futures = executorService.invokeAll(calls);
        // future objective holds the results of the tasks
        // get all results from futures

        List<Integer> results = futures.stream().map(future-> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        return results.toString();

    }
    
    private String findEarthID(String  city){
        Optional<List<Object>> response = httpDao.sendGetReqList(
                "https://www.metaweather.com/api/location/search/?query=" + city,
                EarthIDResponse[].class);

        if (response.isPresent() && response.get().size() == 1){
            System.out.println("This API was successfully called");
            System.out.println(response.get());
        } else if (response.isPresent() && response.get().size() == 0) {
            System.out.println("invalid city");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid city");
        } else if (response.isPresent() && response.get().size() > 1)  {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable city");
        } else {
            System.out.println("This api call failed.");
        }

        List<Object> woeidList = response.get();
        EarthIDResponse woeidResp = (EarthIDResponse) woeidList.get(0);
        return woeidResp.getWoeid();
    }

    private String findTemp(String Woeid){
        Optional<Object> response = httpDao.sendGetRequest( "https://www.metaweather.com/api/location/" + Woeid,
                WeatherResponse.class);
        if (response.isPresent()){
            System.out.println("This API was successfully called");
            System.out.println(response.get());
        } else {
            System.out.println("This api call failed.");
        }

        String test1 = StaticExample.staticString; // static attributes can be obtained from the class
        StaticExample nonStaticObject = new StaticExample();
        // nonStaticObject is NOT a class. It is an object of type StaticExample.
        String test2 = nonStaticObject.nonstaticString;

        // Singleton singleton = new Singleton(); This won't work because the constructor is private.
        Singleton singleton = Singleton.getInstance();


        Object weatherList = response.get();
        WeatherResponse weatherResp = (WeatherResponse) weatherList;

        return weatherResp.getConsolidated_weather().get(0).getThe_temp();
    }


}
