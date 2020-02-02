package org.lili.cache.guava;

import org.lili.cache.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lili
 * @date 2020/2/2 18:00
 * @description  https://blog.csdn.net/wzl1369248650/article/details/95656093
 */
@Service(value = "guavaUserServcie")
public class UserService {

    private Map<Integer, User> users = new HashMap<Integer, User>();

    {
        users.put(1, new User(1, "w1", 37));
        users.put(2, new User(2, "w2", 34));
    }

    @Cacheable(value = "users", condition = "#user.age < 35")
    public User getUser(User user) {
        System.out.println("User with id " + user.getId() + " requested.");
        return users.get(Integer.valueOf(user.getId()));
    }
}
