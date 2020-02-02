package org.lili.cache.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.lili.cache.base.BaseUnitTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author lili
 * @date 2020/2/2 17:13
 * @description
 */

@Log4j2
public class UserServiceTest extends BaseUnitTest {

    @Autowired
    private UserService userService;

    @Test
    public void findById() {
        userService.findById(1);
        // find from cache
        userService.findById(1);
        userService.findById(1);
        userService.findById(1);
        log.info("user is: {}", JSON.toJSONString(userService.findById(1)));
        //update cache
        userService.updateUserById(1);
        log.info("user is: {}", JSON.toJSONString(userService.findById(1)));
        //delete cache read from db
        userService.delteById(1);
        userService.findById(1);
        userService.findById(1);
        log.info("user is: {}", JSON.toJSONString(userService.findById(1)));


    }
}