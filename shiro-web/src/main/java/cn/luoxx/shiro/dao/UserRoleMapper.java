package cn.luoxx.shiro.dao;

import cn.luoxx.shiro.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}