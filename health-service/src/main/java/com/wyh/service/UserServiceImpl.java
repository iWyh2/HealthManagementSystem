package com.wyh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyh.dao.PermissionDao;
import com.wyh.dao.RoleDao;
import com.wyh.dao.UserDao;
import com.wyh.pojo.Permission;
import com.wyh.pojo.Role;
import com.wyh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    //查询用户信息和关联的角色信息和权限信息
    public User findByUsername(String username) {
        //查询用户基本信息
        User user = userDao.findByUsername(username);
        if(user == null){
            return null;
        }
        //根据id查询角色信息和权限信息
        Integer userId = user.getId();
        Set<Role> roles = roleDao.findByUserId(userId);
        if(roles != null && roles.size() > 0){
            for(Role role : roles){
                //根据角色id查询权限信息
                Integer roleId = role.getId();
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if(permissions != null && permissions.size() > 0){
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }
}
