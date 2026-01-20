package com.starfish.test.controller;

import com.starfish.core.annotation.RequireLogin;
import com.starfish.core.model.Result;
import com.starfish.test.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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

    @RequireLogin(false)
    @RequestMapping(value = {"/api/test/testXForwardedFor"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> alive(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
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

}
