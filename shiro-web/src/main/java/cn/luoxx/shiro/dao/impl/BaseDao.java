package cn.luoxx.shiro.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {
	
	@Resource
	protected JdbcTemplate jdbcTemplate;

}
