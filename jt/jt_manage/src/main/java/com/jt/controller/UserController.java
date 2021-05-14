package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    public SysResult getUserList(){

        List<User> all = userService.findAll();
        if(all!=null) return SysResult.success("用户列表获取成功",all);
        else return SysResult.fail();
    }
    /**
     * 1.url地址：、user/login
     * 2.请求的参数：表单对象Json串
     * 3.返回值的结果SysResult
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user){
        String token = userService.findUserByUP(user);
        if(StringUtils.hasLength(token))
            return SysResult.success(token);
        return SysResult.fail();
    }
}
