package com.starfish.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starfish.test.context.User;
import com.starfish.test.entity.UserEntity;

/**
 * UserService
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2022-07-19
 */
public interface UserService {

    /**
     * 注册
     *
     * @param userModel 用户
     */
    Long register(UserEntity userModel);

    /**
     * 登录
     *
     * @param userModel 用户
     */
    User login(UserEntity userModel);

    /**
     * 登录
     *
     * @param userModel 用户
     */
    void logout(UserEntity userModel);

    /**
     * 查询用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    UserEntity getUser(Long userId);

    /**
     * 查询用户列表
     *
     * @return 结果
     */
    Page<UserEntity> list();

}
