package com.starfish.test.controller;

import com.starfish.common.storage.minio.MinioService;
import com.starfish.core.annotation.RequireLogin;
import com.starfish.core.model.Result;
import com.starfish.test.entity.UserEntity;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

/**
 * AliveController
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2020-09-13
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    private MinioService minioService;

    @RequireLogin(false)
    @RequestMapping(value = {"/api/test/testXForwardedFor"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> alive(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        HttpHeaders fileHeaders = new HttpHeaders();

        fileHeaders.setContentDispositionFormData("file", "fileName");
        log.info("{}", names);
        return Result.success("alive");
    }

    @RequireLogin(false)
    @RequestMapping(value = {"/api/test/testXForwardedFor2"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> alive2(@RequestBody UserEntity user, HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        log.info("{}", names);
        return Result.success("alive");
    }

    @RequireLogin(false)
    @RequestMapping(value = {"/api/minio/createBucket"}, method = {RequestMethod.POST})
    public Result<String> createBucket() {

        minioService.createBucket("test");

        return Result.success();
    }

}
