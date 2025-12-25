package com.starfish.test.controller;

import com.google.common.base.Strings;
import com.starfish.test.context.User;
import com.starfish.test.context.UserContext;
import com.starfish.test.entity.UserEntity;
import com.starfish.test.enumeration.ResultEnum;
import com.starfish.test.exception.CustomException;
import com.starfish.test.interceptor.NoLogin;
import com.starfish.test.result.Result;
import com.starfish.test.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2022-07-14
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @NoLogin
    @PostMapping("/api/user/register")
    public Result<UserEntity> register(@RequestBody UserEntity userModel) {
        // 验证参数，手机号，密码必传
        String mobile = userModel.getMobile();
        String password = userModel.getPassword();
        if (Strings.isNullOrEmpty(mobile)) {
            throw new CustomException(ResultEnum.PARAM_ERROR);
        }
        if (Strings.isNullOrEmpty(userModel.getPassword())) {
            throw new CustomException(ResultEnum.PARAM_ERROR);
        }

        // 登录
        log.info("UserController register start.mobile={},password={}", mobile, password);
        Long userId = userService.register(userModel);
        userModel.setId(userId);
        log.info("UserController register success.mobile={},password={}", mobile, password);
        return Result.success(userModel);
    }

    @NoLogin
    @PostMapping("/api/user/login")
    public Result<User> login(@RequestBody UserEntity userModel) {
        // 验证参数，手机号，密码必传
        String mobile = userModel.getMobile();
        String password = userModel.getPassword();
        if (Strings.isNullOrEmpty(mobile)) {
            throw new CustomException(ResultEnum.PARAM_ERROR);
        }
        if (Strings.isNullOrEmpty(userModel.getPassword())) {
            throw new CustomException(ResultEnum.PARAM_ERROR);
        }

        // 登录
        log.info("UserController login start.mobile={},password={}", mobile, password);
        User user = userService.login(userModel);
        log.info("UserController login success.mobile={},password={}", mobile, password);
        return Result.success(user);
    }

    @PostMapping("/api/user/logout")
    public Result<String> logout() {
        // 退出
        UserEntity userModel = new UserEntity();
        Long userId = UserContext.getUserId();
        userModel.setId(userId);
        log.info("UserController logout start.userId={}", userId);
        userService.logout(userModel);
        log.info("UserController logout success.userId={}", userId);
        return Result.success();
    }

    @GetMapping("/api/user/getUser")
    public Result<UserEntity> getUser() {
        Long userId = UserContext.getUserId();
        log.info("UserController getUser.userId={}", userId);
        UserEntity userModel = userService.getUser(userId);
        return Result.success(userModel);
    }

}
