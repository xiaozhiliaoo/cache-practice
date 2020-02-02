package org.lili.cache.service.impl;

import lombok.extern.log4j.Log4j2;
import org.lili.cache.domain.User;
import org.lili.cache.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lili
 * @date 2020/2/2 17:09
 * @description
 */

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(cacheNames = "users")
    public User findById(int id) {
        User user = new User(1,"lili",27);
        log.info("findById: userId:{}", id);
        return user;
    }


    @Override
    @CachePut(cacheNames = "users")
    public User updateUserById(int id) {
        User user = new User(1,"lili2",28);
        log.info("findById: userId:{}", id);
        return user;
    }

    @Override
    @CacheEvict(cacheNames = "users")
    public void delteById(int id) {

    }
}
