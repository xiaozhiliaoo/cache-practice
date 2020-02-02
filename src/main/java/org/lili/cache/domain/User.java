package org.lili.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lili
 * @date 2020/2/2 17:08
 * @description
 */
@Data
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private int age;
}
