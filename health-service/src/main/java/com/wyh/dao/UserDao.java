package com.wyh.dao;

import com.wyh.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
