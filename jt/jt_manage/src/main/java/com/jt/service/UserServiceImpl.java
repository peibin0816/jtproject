package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public String findUserByUP(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1==null || user.getPassword()==null) return null;
        String reqPwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if(StringUtils.pathEquals(user1.getPassword(),reqPwd))
            return DigestUtils.md5DigestAsHex("success".getBytes());
        return null;
    }
}
