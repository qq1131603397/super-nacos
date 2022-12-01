package com.hz.shrioUser.service;

import com.hz.shrioUser.entity.User;
import com.hz.shrioUser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author： pt
 * @date： 2022/12/1 10:22
 * @discription
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}
