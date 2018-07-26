package cn.luoxx.shiro.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public class BaseDao {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected JdbcTemplate jdbcTemplate;

}
