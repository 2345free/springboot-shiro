package cn.luoxx.shiro.dao;

import cn.luoxx.shiro.entity.User;

public interface UserDao {

	User findByName(String loginName);

	Object getList();

}