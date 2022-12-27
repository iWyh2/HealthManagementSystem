package com.wyh.dao;

import com.wyh.pojo.Role;
import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(int id);
}
