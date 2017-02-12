package cn.luoxx.shiro.dao;

import cn.luoxx.shiro.entity.Permission;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}