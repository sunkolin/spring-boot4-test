package com.starfish.test.controller;

import com.starfish.core.annotation.RequireLogin;
import com.starfish.core.model.Result;
import com.starfish.test.entity.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2022-07-14
 */
@Slf4j
@Tag(name = "AdminController", description = "管理接口")
@RestController
public class AdminController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequireLogin(false)
    @PostMapping("/api/admin/redis")
    public Result<UserEntity> redisDelete(@RequestBody RedisDeleteRequestBody requestBody) {
        redisTemplate.delete(requestBody.getKey());
        return Result.success();
    }

    @Data
    static class RedisDeleteRequestBody {

        private String key;

    }

}
