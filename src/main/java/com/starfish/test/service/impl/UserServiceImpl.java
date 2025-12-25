package com.starfish.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starfish.test.context.User;
import com.starfish.test.entity.UserEntity;
import com.starfish.test.enumeration.ResultEnum;
import com.starfish.test.exception.CustomException;
import com.starfish.test.mapper.UserMapper;
import com.starfish.test.service.UserService;
import com.starfish.test.util.JsonWebTokenPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * UserService
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2022-07-19
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long register(UserEntity userModel) {
        // 查询用户是否已经注册过
        UserEntity param = new UserEntity();
        param.setMobile(userModel.getMobile());
        UserEntity result = userMapper.selectOne(new QueryWrapper<>(param));
        if (result != null) {
            log.error("UserServiceImpl register fail,mobile already register,mobile={},userId={}", userModel.getMobile(), result.getId());
            throw new CustomException(ResultEnum.ALREADY_REGISTER);
        }

        userMapper.insert(userModel);
        return userModel.getId();
    }

    @Override
    public User login(UserEntity userModel) {
        UserEntity param = new UserEntity();
        param.setMobile(userModel.getMobile());
        param.setPassword(userModel.getPassword());
        UserEntity result = userMapper.selectOne(new QueryWrapper<>(param));
        if (result == null) {
            log.error("UserServiceImpl login fail.mobile={},password={}", userModel.getMobile(), userModel.getPassword());
            throw new CustomException(ResultEnum.LOGIN_FAIL);
        }
        log.info("UserServiceImpl login success.mobile={},password={}", userModel.getMobile(), userModel.getPassword());

        // 生成token
        User user = new User();
        Long userId = result.getId();
        user.setUserId(userId);
        user.setNickName(result.getNickName());
        user.setLastLoginTime(new Date());
        String token = JsonWebTokenPlus.create(userId, user);
        user.setToken(token);
        return user;
    }

    @Override
    public void logout(UserEntity userModel) {
        Long userId = userModel.getId();
        // 清理登录数据
        log.info("UserServiceImpl logout success.userId={}", userId);
    }

    @Override
    public UserEntity getUser(Long userId) {
        // 查询数据库
        return userMapper.selectById(userId);
    }

    @Override
    public Page<UserEntity> list() {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getId, 1000L);
        long current = 100000;
        long size = 10;
        return userMapper.selectPage(new Page<>(current, size), wrapper);
    }

}
