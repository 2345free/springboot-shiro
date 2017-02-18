package cn.luoxx.shiro.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import cn.luoxx.shiro.dao.UserDao;
import cn.luoxx.shiro.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User findByName(String loginName) {
		User user =jdbcTemplate.query("select * from t_user where username=?", new Object[]{loginName}, new ResultSetExtractor<User>(){

			@Override
			public User extractData(ResultSet row) throws SQLException, DataAccessException {
				User user=null;
				while(row.next()){
					user=new User();
					user.setId(row.getInt("id"));
					user.setUsername(row.getString("username"));
					user.setPassword(row.getString("password"));
					logger.info("user:{}",user);
				}
				return user;
			}
			
		});
		return user;
	}

	@Override
	public List<User> getList() {
		final List<User> users=new ArrayList<User>();
		jdbcTemplate.query("select * from t_user", new ResultSetExtractor<User>(){

			@Override
			public User extractData(ResultSet row) throws SQLException, DataAccessException {
				User user=null;
				while(row.next()){
					user=new User();
					user.setId(row.getInt("id"));
					user.setUsername(row.getString("username"));
					user.setPassword(row.getString("password"));
					users.add(user);
				}
				return user;
			}
			
		});
		return users;
	}

}
