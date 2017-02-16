package cn.luoxx.shiro.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {
	
	protected Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Resource
	protected JdbcTemplate jdbcTemplate;

}
