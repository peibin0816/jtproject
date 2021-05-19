package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
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

    @GetMapping("/list")
    public SysResult getUserList(PageResult pageResult){

        pageResult = userService.findByQuery(pageResult);
        return SysResult.success("用户列表获取成功",pageResult);
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

    /**
     * 1.url地址：、user/addUser
     * 2.请求的参数：表单对象Json串
     * 3.返回值的结果SysResult
     */
    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user){
        Integer res= userService.addUser(user);
        if(res>0)
            return SysResult.success();
        return SysResult.fail();
    }

    /**
     * 1.url地址：、user/updateUser
     * 2.请求的参数：表单对象Json串
     * 3.返回值的结果SysResult
     */
    @PostMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user){
        Integer res= userService.updateUser(user);
        if(res>0)
            return SysResult.success();
        return SysResult.fail();
    }


    /**
     * 更新status
     * 1.url地址：、user/status
     * 2.请求的参数：{id}/{status}
     * 3.返回值的结果SysResult
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(@PathVariable Integer id,@PathVariable Boolean status){
        Integer rst = userService.updateStatusById(id, status);
        if(rst>0)
            return SysResult.success();
        return SysResult.fail();
    }


    /**
     * 删除
     * 1.url地址：/user/delete
     * 2.请求的参数：{id}
     * 3.返回值的结果SysResult
     */
    @PutMapping("/delete/{id}")
    public SysResult deleteUser(@PathVariable Integer id){
        Integer rst = userService.deleteUserById(id);
        if(rst>0)
            return SysResult.success();
        return SysResult.fail();
    }


}
