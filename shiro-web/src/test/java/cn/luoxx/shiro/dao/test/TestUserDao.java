package cn.luoxx.shiro.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.luoxx.shiro.dao.IUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserDao {
	
	@Autowired  
    private IUserDao userDao;
	
    @Test  
    public void testFindByName() {
        System.out.println(userDao.findByName("tom"));
    }  

    @Test  
    public void testGetList() {
    	System.out.println(userDao.getList());
    }  
}
