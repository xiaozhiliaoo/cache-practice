package org.lili.cache.service;

import org.lili.cache.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author lili
 * @date 2020/2/2 17:04
 * @description
 * @notes
 */

public interface UserService {
    User findById(int id);
}
