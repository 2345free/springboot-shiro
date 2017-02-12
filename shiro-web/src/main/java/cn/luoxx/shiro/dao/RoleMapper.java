package cn.luoxx.shiro.dao;

import cn.luoxx.shiro.entity.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}