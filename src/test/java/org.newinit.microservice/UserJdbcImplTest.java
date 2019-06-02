package org.newinit.microservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.newinit.microservice.dao.UserJdbcImpl;
import org.newinit.microservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserJdbcImplTest {
    @Autowired
    private UserJdbcImpl userJdbcImpl;
    @Test
    public void insertuser(){
        User user = new User();
        user.setName("from test");
        user.setPassword("123");
        user.setAge(9);
        int result = userJdbcImpl.insertUser(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testSelectById(){
        User user = new User();
        user.setName("from test2");
        user.setPassword("456");
        int userReturnId = userJdbcImpl.insertUserReturnId(user);
        Assert.assertTrue(userReturnId > 0);

        User userFromDB = userJdbcImpl.selectById(userReturnId);
        Assert.assertEquals("from test2", userFromDB.getName());
    }
}
