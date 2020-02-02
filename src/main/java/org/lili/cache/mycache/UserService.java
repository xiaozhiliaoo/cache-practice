package org.lili.cache.mycache;


import org.lili.cache.domain.User;

public class UserService {

    private CacheManager<User> cacheManager;

    public UserService() {
        cacheManager = new CacheManager<User>();
    }

    public User getUserById(String userId) {
        User result = cacheManager.getValue(userId);
        if (result != null) {
            System.out.println("从缓存中查询..." + userId);
            return result;
        }
        result = getFromDB(userId);
        if (result != null) {
            cacheManager.addOrUpdateCache(userId, result);
        }
        return result;
    }

    public void reload() {
        cacheManager.evictCache();
    }

    private User getFromDB(String userId) {
        System.out.println("从数据库中查询..." + userId);
        return new User(Integer.valueOf(userId), userId, 1);
    }

}
