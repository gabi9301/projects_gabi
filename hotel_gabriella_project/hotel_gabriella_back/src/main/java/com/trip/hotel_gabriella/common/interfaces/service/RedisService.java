package com.trip.hotel_gabriella.common.interfaces.service;

public interface RedisService {
    public void setData(String key, Object value,long expirationTime);

    public Object getData(String key);

    public Boolean exist(String key);

}
