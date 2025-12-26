package com.starfish.test.controller;

import com.starfish.core.annotation.RequireLogin;
import com.starfish.core.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * AliveController
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2020-09-13
 */
@RestController
public class AliveController {

    @RequireLogin(false)
    @RequestMapping(value = "/api/alive", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> alive() {
        return Result.success("alive");
    }

}
