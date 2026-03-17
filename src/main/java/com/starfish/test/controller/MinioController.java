package com.starfish.test.controller;

import com.starfish.common.storage.minio.MinioService;
import com.starfish.core.annotation.RequireLogin;
import com.starfish.core.model.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * MinioController
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2020-09-13
 */
@Slf4j
@RestController
public class MinioController {

    @Resource
    private MinioService minioService;

    @RequireLogin(false)
    @RequestMapping(value = {"/api/minio/createBucket"}, method = {RequestMethod.POST})
    public Result<String> createBucket() {

        minioService.createBucket("test");

        return Result.success();
    }

}
