package org.newinit.microservice.controller;

import org.newinit.microservice.model.User;
import org.newinit.microservice.dao.UserJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserJdbcImpl userJdbcImpl;

    @RequestMapping("/insertUser")
    @ResponseBody
    public int insertUser(@RequestParam("name") String name){
        User user = new User();
        System.out.println(name);
        user.setName(name);
        return userJdbcImpl.insertUser(user);
    }

    @RequestMapping("/user/{userId}")
    @ResponseBody
    public User getUser(@PathVariable("userId") int userId){
        return userJdbcImpl.selectById(userId);
    }

}
