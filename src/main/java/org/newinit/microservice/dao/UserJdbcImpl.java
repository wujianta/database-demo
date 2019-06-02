package org.newinit.microservice.dao;

import org.newinit.microservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public class UserJdbcImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int insertUser(User user){
        return jdbcTemplate.update("insert into user(name, password,age, createdTime) values(?, ?, ?, now())", user.getName(), user.getPassword(), user.getAge());
    }

    public int insertUserReturnId(User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", user.getName());
        parameterSource.addValue("password", user.getPassword());
        parameterSource.addValue("age", user.getAge());
        namedParameterJdbcTemplate.update(
                "insert into user(name, password,age, createdTime) values(:name, :password, :age, now())",
                parameterSource, keyHolder, new String[]{"id"});

        int userId = keyHolder.getKey().intValue();
        return userId;
    }

    public User selectById(int id){
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select id, name, createdTime from user where id = " + id);
        User user = new User();
        user.setId((Integer) resultMap.get("id"));
        user.setName((String) resultMap.get("name"));
        user.setCreatedTime((Date) resultMap.get("createdTime"));
        return user;
    }
}
