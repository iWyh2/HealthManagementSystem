package com.wyh.service;

import com.wyh.pojo.User;
/**
 * 用户服务接口
 */
public interface UserService {
    public User findByUsername(String username);
}
