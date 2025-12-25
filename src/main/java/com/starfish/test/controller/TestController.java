package com.starfish.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starfish.test.interceptor.NoLogin;
import com.starfish.test.model.Result;
import com.starfish.test.model.UserModel;
import com.starfish.test.service.UserService;
import jakarta.annotation.Resource;
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

    @Resource
    private UserService userService;

    @GetMapping("/api/user/list")
    public Result<Page<UserModel>> getUser() {
        Page<UserModel> list = userService.list();
        return Result.success(list);
    }

    @NoLogin
    @RequestMapping(value = {"/api/test/testXForwardedFor"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> alive(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        log.info("{}", names);
        return Result.success("alive");
    }

    @NoLogin
    @RequestMapping(value = {"/api/test/testXForwardedFor2"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> alive2(@RequestBody UserModel user, HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        log.info("{}", names);
        return Result.success("alive");
    }

}
