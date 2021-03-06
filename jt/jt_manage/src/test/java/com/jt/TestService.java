package com.jt;

import com.jt.pojo.Rights;
import com.jt.pojo.User;
import com.jt.service.RightsService;
import com.jt.service.UserService;
import com.jt.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = SpringBootRun.class)
public class TestService {

    @Autowired
    private UserService userService;

    @Autowired
    private RightsService rightsService;

    @Test
    public void loginService(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin123456");
        System.out.printf(String.valueOf(userService==null));
        String userByUP = userService.findUserByUP(user);
        System.out.println(userByUP);
    }

    @Test
    public void rightsList(){
        List<Rights> findall = rightsService.findall();
        for(Rights r:findall){
            System.out.println(r);
        }
    }
}
