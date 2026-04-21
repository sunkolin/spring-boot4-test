package com.starfish.test.controller;

import com.starfish.core.annotation.RequireLogin;
import com.starfish.core.model.Result;
import com.starfish.test.entity.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RedisTestController
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2022-07-14
 */
@Slf4j
@Tag(name = "RedisTestController", description = "RedisTestController")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequireLogin(false)
    @PostMapping("/api/redis/delete")
    public Result<UserEntity> redisDelete(@RequestBody RedisDeleteRequestBody requestBody) {
        stringRedisTemplate.delete(requestBody.getKey());
        return Result.success();
    }

    @Data
    static class RedisDeleteRequestBody {

        private String key;

    }

}
