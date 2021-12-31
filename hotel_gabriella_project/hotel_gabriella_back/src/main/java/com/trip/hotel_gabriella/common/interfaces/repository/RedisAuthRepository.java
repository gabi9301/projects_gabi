package com.trip.hotel_gabriella.common.interfaces.repository;

import com.trip.hotel_gabriella.common.domain.RedisUser;
import org.springframework.data.repository.CrudRepository;


public interface RedisAuthRepository extends CrudRepository<RedisUser,String> {


}
