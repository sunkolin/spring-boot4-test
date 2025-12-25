package com.starfish.test.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starfish.test.mapper.UserMapper;
import com.starfish.test.model.UserModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserCheckTask
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2016-01-13
 */
@Slf4j
@Component
public class CheckUserEmailTask implements ApplicationRunner {

    @Resource
    private UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) {
        QueryWrapper<UserModel> queryWrapper = new QueryWrapper<>();
        List<UserModel> userList = userMapper.selectList(queryWrapper);
        for (UserModel user : userList) {
            String email = user.getEmail();
            if (!check(email)) {
                log.info("'" + email + "',");
            }
        }
    }

    public boolean check(String email) {
        if (Strings.isNullOrEmpty(email)) {
            return true;
        }
        boolean flag = false;
        for (char c : email.toCharArray()) {
            if (c == '@' || c == '.' || Character.isDigit(c) || Character.isLetter(c)) {
                flag = true;
            } else {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

}
