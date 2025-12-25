package com.starfish.test.context;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2015-05-08
 */
@Data
public class User implements Serializable {

    /**
     * 主键
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 最后登录成功时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 登录token
     */
    private String token;

}
