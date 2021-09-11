package com.antra.ting.weather.dao;

import java.util.List;
import java.util.Optional;

public interface HttpDao {
    public Optional<Object> sendGetRequest(String url, Class clazz);
    public Optional<List<Object>> sendGetReqList(String url,Class clazz);

}
