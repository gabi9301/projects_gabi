package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.domain.RedisUser;
import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTemplateService implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setData(String key, Object value,long refreshTokenValidMilliSeconds) {

        redisTemplate.opsForValue().set("RT_"+ key, value, Duration.ofMillis(refreshTokenValidMilliSeconds));
    }

    @Override
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }


    //--이하 Repository 버전-------------------------------------
//    private final RedisAuthRepository redisAuthRepository;
//
//    public void setData(String account, String refreshToken){
//        RedisUser redisUser = new RedisUser(account,refreshToken);
//        redisAuthRepository.save(redisUser);
//    }
//
//    public Optional<RedisUser> getData(String id){
//        return redisAuthRepository.findById(id);
//    }
}
