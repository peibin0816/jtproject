package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.PageResult;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
public interface UserService {
    List<User> findAll();
    String findUserByUP(User user);
    PageResult findByQuery(PageResult pageResult);

    Integer updateStatusById(Integer id, Boolean status);

    Integer deleteUserById(Integer id);

    Integer addUser(User user);

    Integer updateUser(User user);
}
