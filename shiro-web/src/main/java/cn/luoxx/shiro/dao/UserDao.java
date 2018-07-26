package cn.luoxx.shiro.dao;

import cn.luoxx.shiro.model.User;

public interface UserDao {

    User findByName(String loginName);

    Object getList();

}
