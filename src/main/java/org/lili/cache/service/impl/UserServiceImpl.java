package org.lili.cache.service.impl;

import org.lili.cache.domain.User;
import org.lili.cache.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lili
 * @date 2020/2/2 17:09
 * @description
 */

@Service
public class UserServiceImpl implements UserService {


    @Override
    public User findById(int id) {
        User user = new User();
        user.setId(1);
        user.setAge(12);
        user.setPhoneNumber("1378992444");
        return user;
    }
}
