package com.wyh.dao;

import com.wyh.pojo.Permission;
import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);
}
