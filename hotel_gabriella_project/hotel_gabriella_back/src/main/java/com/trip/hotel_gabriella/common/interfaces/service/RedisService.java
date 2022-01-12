package com.trip.hotel_gabriella.common.interfaces.service;

public interface RedisService {
    void setData(String key, Object value, long expirationTime);

    Object getData(String key);

    Boolean exist(String key);

    void deleteData(String key);

}
