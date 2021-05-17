package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

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

        String reqPwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(reqPwd);
        QueryWrapper<User> queryWrapper = new QueryWrapper(user);
        User userDB = userMapper.selectOne(queryWrapper);
        return userDB==null?null: UUID.randomUUID().toString().replace("-","");
    }

    @Override
    public PageResult findByQuery(PageResult pageResult) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(pageResult.getQuery()),"username",pageResult.getQuery());

//        System.out.printf(pageResult.getQuery());
        IPage<User> page  = new Page<>(pageResult.getPageNum(),pageResult.getPageSize());
        page= userMapper.selectPage(page,queryWrapper);

        pageResult.setRows(page.getRecords());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public Integer updateStatusById(Integer id, Boolean status) {
        return userMapper.updateById(new User().setId(id).setStatus(!status));

    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

}
