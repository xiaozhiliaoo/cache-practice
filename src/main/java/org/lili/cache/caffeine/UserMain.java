package org.lili.cache.caffeine;

import org.lili.cache.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lili
 * @date 2020/2/2 18:03
 * @description
 */
public class UserMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-caffeine.xml");
        UserService userService = (UserService) context.getBean("caffeineUserServcie");

        //小于34岁才进缓存
        User user1 = new User(2, "w2", 34);
        User userFetch1 = userService.getUser(user1);
        System.out.println(userFetch1);
        //find from cache
        User userFetch2 = userService.getUser(user1);
        System.out.println(userFetch2);

        //37岁不进缓存
        User user2 = new User(1, "w1", 37);
        User userFetch3 = userService.getUser(user2);
        System.out.println(userFetch3);
        User userFetch4 = userService.getUser(user2);
        System.out.println(userFetch4);
    }
}
