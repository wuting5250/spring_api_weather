package com.antra.ting.weather.dao;

import com.antra.ting.weather.models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// The dao layer communicates with the data source.
@Component
public class HttpDaoImpl implements HttpDao {

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Optional<Object> sendGetRequest(String url, Class clazz) {

        try{
            Object obj = restTemplate.getForObject(url, clazz);
            return Optional.of(obj);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public Optional<List<Object>> sendGetReqList(String url, Class clazz) {
        try {
            ResponseEntity<Object[]> response =
                    restTemplate.getForEntity(
                            url,
                            clazz);
            Object[] resp = response.getBody();
            return Optional.of(Arrays.asList(resp));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
